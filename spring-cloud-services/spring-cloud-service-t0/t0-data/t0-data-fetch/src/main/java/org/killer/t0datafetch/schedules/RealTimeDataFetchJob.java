package org.killer.t0datafetch.schedules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.killer.t0datafetch.entity.CommonResponse;
import org.killer.t0datafetch.entity.TuShareApi;
import org.killer.t0listedcompany.entity.listedCompany.ListedCompany;
import org.killer.t0sharedata.entity.ShareDataReqParams;
import org.killer.t0sharedata.entity.ShareDataResponse;
import org.killer.t0datafetch.repository.ListedCompanyRepository;
import org.killer.t0sharedata.repository.ShareDataRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 获取股票交易的实时数据，那么问题来了，每一个股票都要获取吗，还是应该设置我关注的几个股份，后者比较好, 每个都获取
 * 获取数据需要通知，短信通知？
 * <p>
 * 获取所有的股票信息，一般6点之后获取吧， 每分钟500次， 需要20分钟。我猜是单个接口
 *
 * @author killer
 * @date 2020/05/19 - 17:03
 */
@Slf4j
public class RealTimeDataFetchJob extends QuartzJobBean {

    private RestTemplate restTemplate;

    private ShareDataRepository shareDataRepository;

    private ListedCompanyRepository listedCompanyRepository;

    private MQProducer mqProducer;

    @Autowired
    private ObjectMapper objectMapper;

    public RealTimeDataFetchJob(RestTemplate restTemplate, ShareDataRepository shareDataRepository, ListedCompanyRepository listedCompanyRepository, MQProducer mqProducer) {
        this.restTemplate = restTemplate;
        this.shareDataRepository = shareDataRepository;
        this.listedCompanyRepository = listedCompanyRepository;
        this.mqProducer = mqProducer;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 需要进行一个处理， 就按一万只股票算，也要一万个请求才对
        // 两步 1. 保存数据， 2. 进行判断 如果全部由这台服务器来操作不会很慢，也可以用队列来做

        // 这里该怎么实现呢？？，一次取出400个，每隔3分钟， 可以， 从5.30 开始
        List<ListedCompany> notFetchedDataCompanies = listedCompanyRepository.getNotFetchedDataCompany();

        for (ListedCompany notFetchedDataCompany : notFetchedDataCompanies) {

            RequestEntity<TuShareApi> requestEntity = null;

            DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
            ShareDataReqParams shareDataReqParams = new ShareDataReqParams();
            shareDataReqParams.setTs_code(notFetchedDataCompany.getTsCode());
            shareDataReqParams.setStart_date(LocalDate.now().format(yyyyMMdd));
            shareDataReqParams.setEnd_date(LocalDate.now().format(yyyyMMdd));
            shareDataReqParams.setTrade_date(LocalDate.now().format(yyyyMMdd));

            TuShareApi.SHARE_NEW_API.setParams(shareDataReqParams);
            try {
                requestEntity = new RequestEntity<>(TuShareApi.SHARE_NEW_API, HttpMethod.POST, new URI("http://api.tushare.pro/"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
                log.error("此次调用获取上市公司信息失败！");
            }

            ResponseEntity<CommonResponse<ShareDataResponse>> exchange = restTemplate.exchange("http://api.tushare.pro/", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<CommonResponse<ShareDataResponse>>() {
            });

            Optional<CommonResponse<ShareDataResponse>> result = Optional.ofNullable(exchange.getBody());

            if (result.isPresent()) {

                ShareDataResponse data = result.get().getData();
                // 插入或者更新数据, 我在一条sql语句中写会有问题吗，应该没有，属于同一个事务中, 这样过滤两遍真的好吗？？
                // jpa 没有insert方法， 只有save方法， 他把insert和update合并到了一起， 还是很不错的
                // 将获取到的消息发送到RocketMQ之中
                data.transform().stream().peek(shareDataRepository::save).map(shareData -> {
                    try {
                        return new Message("shareDataRealTime", objectMapper.writeValueAsBytes(shareData));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).filter(Objects::nonNull).forEach(message -> {
                    try {
                        // 发送到消息队列中计算出指标，，MACD，，MA，，神奇通道
                        mqProducer.send(message);
                    } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
                        log.error(e.getMessage());
                    }
                });

            }
        }

    }

}
