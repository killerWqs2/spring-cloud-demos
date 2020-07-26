package org.killer.t0datafetch.unit.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.killer.t0datafetch.entity.CommonResponse;
import org.killer.t0datafetch.entity.TuShareApi;
import org.killer.t0listedcompany.entity.listedCompany.ListedCompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author killer
 * @date 2020/06/04 - 20:04
 */
// @SpringBootTest
@Slf4j
class ListedCompanyDataFetchDemo {

    // @Autowired
    // private RestTemplate restTemplate;

    @Test
    void test() {
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<TuShareApi> requestEntity = null;
        try {
            TuShareApi.LISTED_COMPANY_API.setParams(new HashMap());
            requestEntity = new RequestEntity<>(TuShareApi.LISTED_COMPANY_API, HttpMethod.POST, new URI("http://api.tushare.pro/"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            log.error("此次调用获取上市公司信息失败！");
        }

        ResponseEntity<CommonResponse<ListedCompanyResponse>> exchange = restTemplate.exchange("http://api.tushare.pro/", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<CommonResponse<ListedCompanyResponse>>() {
        });

        Optional<CommonResponse<ListedCompanyResponse>> result = Optional.ofNullable(exchange.getBody());


        if (result.isPresent()) {

            System.out.println(result.get().getCode());
            System.out.println(result.get().getMsg());
            ListedCompanyResponse data = result.get().getData();
            // 插入或者更新数据, 我在一条sql语句中写会有问题吗，应该没有，属于同一个事务中, 这样过滤两遍真的好吗？？
            // jpa 没有insert方法， 只有save方法， 他把insert和update合并到了一起， 还是很不错的
            data.transform().forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            System.out.println(objectMapper.writeValueAsString(TuShareApi.LISTED_COMPANY_API));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
