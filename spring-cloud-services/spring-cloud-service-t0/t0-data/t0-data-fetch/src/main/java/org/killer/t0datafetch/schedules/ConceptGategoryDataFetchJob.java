package org.killer.t0datafetch.schedules;

import lombok.extern.slf4j.Slf4j;
import org.killer.t0concept.entity.ConceptCategory;
import org.killer.t0concept.entity.ConceptCategoryDTO;
import org.killer.t0concept.entity.ConceptCategoryDetailDTO;
import org.killer.t0concept.repository.ConceptCategoryDetailRepository;
import org.killer.t0datafetch.entity.CommonResponse;
import org.killer.t0datafetch.entity.TuShareApi;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;
import org.killer.t0concept.repository.ConceptGategoryRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Optional;

/**
 * 获取概念股票
 *
 * @author killer
 * @date 2020/06/06 - 11:16
 */
@Slf4j
public class ConceptGategoryDataFetchJob extends QuartzJobBean {

    private RestTemplate restTemplate;

    private ConceptGategoryRepository conceptGategoryRepository;

    private ConceptCategoryDetailRepository conceptCategoryDetailRepository;

    public ConceptGategoryDataFetchJob(RestTemplate restTemplate, ConceptGategoryRepository conceptGategoryRepository) {
        this.restTemplate = restTemplate;
        this.conceptGategoryRepository = conceptGategoryRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        RequestEntity<TuShareApi> requestEntity = null;
        try {
            HashMap<Object, Object> params = new HashMap<>(1);
            params.put("src", "ts");
            TuShareApi.SHARE_CONCEPT_CATEGORY_API.setParams(params);
            requestEntity = new RequestEntity<>(TuShareApi.SHARE_CONCEPT_CATEGORY_API, HttpMethod.POST, new URI("http://api.tushare.pro/"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            log.error("此次调用获取概念分类信息失败！");
        }

        ResponseEntity<CommonResponse<ConceptCategoryDTO>> exchange = restTemplate.exchange("http://api.tushare.pro/", HttpMethod.POST, requestEntity,  new ParameterizedTypeReference<CommonResponse<ConceptCategoryDTO>>(){});

        Optional<CommonResponse<ConceptCategoryDTO>> result = Optional.ofNullable(exchange.getBody());

        if(result.isPresent()) {

            ConceptCategoryDTO data = result.get().getData();
            // 插入或者更新数据, 我在一条sql语句中写会有问题吗，应该没有，属于同一个事务中, 这样过滤两遍真的好吗？？
            // jpa 没有insert方法， 只有save方法， 他把insert和update合并到了一起， 还是很不错的
            data.transform().stream().peek(conceptGategoryRepository::save).map(ConceptCategory::getCode).forEach(code -> {
                HashMap<Object, Object> params = new HashMap<>(1);
                params.put("id", code);
                TuShareApi.SHARE_CONCEPT_CATEGORY_API.setParams(params);
                try {
                    RequestEntity<TuShareApi> requestEntity2 = new RequestEntity<>(TuShareApi.SHARE_CONCEPT_CATEGORY_API, HttpMethod.POST, new URI("http://api.tushare.pro/"));
                    ResponseEntity<CommonResponse<ConceptCategoryDetailDTO>> exchange2 = restTemplate.exchange("http://api.tushare.pro/", HttpMethod.POST, requestEntity2,  new ParameterizedTypeReference<CommonResponse<ConceptCategoryDetailDTO>>(){});

                    Optional<CommonResponse<ConceptCategoryDetailDTO>> result2 = Optional.ofNullable(exchange2.getBody());

                    if(result2.isPresent()) {
                        data.transform().forEach(conceptCategoryDetailRepository::save);
                    }

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    log.error("此次调用获取概念分类明细信息失败！");
                }
            });

        }
    }

}
