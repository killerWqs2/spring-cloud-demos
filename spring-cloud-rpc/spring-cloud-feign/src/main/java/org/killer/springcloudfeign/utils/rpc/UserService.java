package org.killer.springcloudfeign.utils.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080")
//没有办法通过微服务名获取到地址，，这个时很关键的
@FeignClient(value = "user-service")
public interface UserService {

    @GetMapping("/user/{uid}")
    Integer user(@PathVariable("uid") Integer uid);

    /** feign也可以实现服务降级 */

}
