package org.killer.t0datafetch.unit.data;

import org.junit.jupiter.api.Test;
import org.killer.t0datafetch.entity.CommonReqParams;
import org.killer.t0datafetch.entity.CommonResponse;
import org.killer.t0sharedata.entity.ShareDataReqParams;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author killer
 * @date 2020/05/26 - 20:28
 */
class HistoryDataFetchDemo {

    @Test
    void test1() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();

        CommonReqParams reqParams = new CommonReqParams().setApi_name("daily").setToken("2b28c98d0396fbb6c3fe1c5904352a405da36d8fc1d4ac415ed13d30").setFields("open,high,low, close");

        // CommonReqParams reqParams = new CommonReqParams();
        ShareDataReqParams shareDataReqParams = new ShareDataReqParams().setTs_code("603000.Sh").setStart_date("20200528").setEnd_date("20200531");
        reqParams.setParams(shareDataReqParams);
        // reqParams.setParams(new HashMap<String, Object>());
        RequestEntity<CommonReqParams> requestEntity = new RequestEntity<>(reqParams, HttpMethod.POST, new URI("http://api.tushare.pro/"));

        // ResponseEntity<CommonResponse<List<ListedCompany>>> exchange = restTemplate.exchange("http://api.tushare.pro/", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<CommonResponse<List<ListedCompany>>>(){});
        ResponseEntity<CommonResponse> exchange = restTemplate.exchange("http://api.tushare.pro/", HttpMethod.POST, requestEntity, CommonResponse.class);

        // exchange交换信息
        Object data = exchange.getBody().getData();

        System.out.println(exchange.getBody());

    }

}
