package org.killer.t0datafetch.schedules;

import lombok.extern.slf4j.Slf4j;
import org.killer.t0datafetch.entity.CommonResponse;
import org.killer.t0datafetch.entity.TuShareApi;
import org.killer.t0listedcompany.entity.listedCompany.ListedCompany;
import org.killer.t0listedcompany.entity.listedCompany.ListedCompanyResponse;
import org.killer.t0datafetch.repository.ListedCompanyRepository;
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
import java.util.HashMap;
import java.util.Optional;

/**
 * 用来获取所有上市公司的股票信息
 *
 * @author killer
 * @date 2020/05/26 - 22:42
 */
@Slf4j
public class ListedCompanyFetchJob extends QuartzJobBean {

    private RestTemplate restTemplate;

    private ListedCompanyRepository<ListedCompany, String> listedCompanyRepository;

    // 不需要加上@Autowired 因为 Bean构造的时候会自动查找依赖
    public ListedCompanyFetchJob(RestTemplate restTemplate, ListedCompanyRepository listedCompanyRepository) {
        this.restTemplate = restTemplate;
        this.listedCompanyRepository = listedCompanyRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        RequestEntity<TuShareApi> requestEntity = null;
        try {
            TuShareApi.LISTED_COMPANY_API.setParams(new HashMap<>(0));
            requestEntity = new RequestEntity<>(TuShareApi.LISTED_COMPANY_API, HttpMethod.POST, new URI("http://api.tushare.pro/"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            log.error("此次调用获取上市公司信息失败！");
        }

        ResponseEntity<CommonResponse<ListedCompanyResponse>> exchange = restTemplate.exchange("http://api.tushare.pro/", HttpMethod.POST, requestEntity,  new ParameterizedTypeReference<CommonResponse<ListedCompanyResponse>>(){});

        Optional<CommonResponse<ListedCompanyResponse>> result = Optional.ofNullable(exchange.getBody());

        if(result.isPresent()) {

            ListedCompanyResponse data = result.get().getData();
            // 插入或者更新数据, 我在一条sql语句中写会有问题吗，应该没有，属于同一个事务中, 这样过滤两遍真的好吗？？
            // jpa 没有insert方法， 只有save方法， 他把insert和update合并到了一起， 还是很不错的
            data.transform().forEach(listedCompanyRepository::save);

        }

    }

}
