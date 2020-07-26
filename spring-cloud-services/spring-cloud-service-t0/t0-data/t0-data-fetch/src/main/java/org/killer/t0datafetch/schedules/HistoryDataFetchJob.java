package org.killer.t0datafetch.schedules;

import lombok.extern.slf4j.Slf4j;
import org.killer.t0datafetch.entity.CommonResponse;
import org.killer.t0datafetch.entity.TuShareApi;
import org.killer.t0datafetch.repository.ListedCompanyRepository;
import org.killer.t0listedcompany.entity.listedCompany.ListedCompany;
import org.killer.t0sharedata.entity.ShareDataIndexResponse;
import org.killer.t0sharedata.entity.ShareDataReqParams;
import org.killer.t0sharedata.entity.ShareDataResponse;
import org.killer.t0sharedata.repository.ShareDataRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
import java.util.Optional;

/**
 *
 * 获取5年内历史数据, 先获取了吧
 *
 * @author killer
 * @date 2020/05/19 - 14:52
 */
@Slf4j
public class HistoryDataFetchJob extends QuartzJobBean {

    private RestTemplate restTemplate;

    private ShareDataRepository shareDataRepository;

    private ListedCompanyRepository listedCompanyRepository;

    public HistoryDataFetchJob(RestTemplate restTemplate, ShareDataRepository shareDataRepository, ListedCompanyRepository listedCompanyRepository) {
        this.restTemplate = restTemplate;
        this.shareDataRepository = shareDataRepository;
        this.listedCompanyRepository = listedCompanyRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 获取历史数据用于分析

        // 问题在于如何停止， 其实比较简单的就是在运行的那一天，设置一个日期，

        // 历史数据需要模型评审，不然获取了也没有什么意义，历史数据校验有bigquant

        List<ListedCompany> listedCompanies = listedCompanyRepository.findAll();

        for (ListedCompany notFetchedDataCompany : listedCompanies) {

            RequestEntity<TuShareApi> shareDataRequestEntity = null;
            RequestEntity<TuShareApi> shareDataIndexRequestEntity = null;

            DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

            // 获取3年内数据
            ShareDataReqParams shareDataReqParams = new ShareDataReqParams();
            shareDataReqParams.setTs_code(notFetchedDataCompany.getTsCode());
            shareDataReqParams.setStart_date("20170603");
            shareDataReqParams.setEnd_date("20170603");

            // 我猜这个优先级大于开始截止日期，晚上验证一下，验证通过 确是如此
            // shareDataReqParams.setTrade_date(LocalDate.now().format(yyyyMMdd));

            TuShareApi.SHARE_NEW_API.setParams(shareDataReqParams);
            try {
                shareDataRequestEntity = new RequestEntity<>(TuShareApi.SHARE_NEW_API, HttpMethod.POST, new URI("http://api.tushare.pro/"));
                shareDataIndexRequestEntity = new RequestEntity<>(TuShareApi.SHARE_INDEX_API, HttpMethod.POST, new URI("http://api.tushare.pro/"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
                log.error("此次调用获取上市公司信息失败！");
            }

            ResponseEntity<CommonResponse<ShareDataResponse>> shareDataExchange = restTemplate.exchange("http://api.tushare.pro/", HttpMethod.POST, shareDataRequestEntity, new ParameterizedTypeReference<CommonResponse<ShareDataResponse>>() {});

            ResponseEntity<CommonResponse<ShareDataIndexResponse>> shareDataIndexExchange = restTemplate.exchange("http://api.tushare.pro/", HttpMethod.POST, shareDataIndexRequestEntity, new ParameterizedTypeReference<CommonResponse<ShareDataIndexResponse>>() {});

            Optional<CommonResponse<ShareDataResponse>> shareDataResult = Optional.ofNullable(shareDataExchange.getBody());
            Optional<CommonResponse<ShareDataIndexResponse>> shareDataIndexResult = Optional.ofNullable(shareDataIndexExchange.getBody());

            if (shareDataResult.isPresent() && shareDataIndexResult.isPresent()) {

                ShareDataResponse shareData = shareDataResult.get().getData();
                // 插入或者更新数据, 我在一条sql语句中写会有问题吗，应该没有，属于同一个事务中, 这样过滤两遍真的好吗？？
                // jpa 没有insert方法， 只有save方法， 他把insert和update合并到了一起， 还是很不错的
                // 将获取到的消息发送到RocketMQ之中
                shareData.transform().forEach(shareDataRepository::save);

                ShareDataIndexResponse shareDataIndex = shareDataIndexResult.get().getData();

                shareDataIndex.transform().forEach(shareDataRepository::save);

            }

        }

    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().minusYears(3).toString().replace("-", ""));
    }

}
