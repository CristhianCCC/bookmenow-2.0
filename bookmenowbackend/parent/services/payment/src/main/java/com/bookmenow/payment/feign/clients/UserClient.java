package com.bookmenow.payment.feign.clients;
import com.bookmenow.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gateway", path = "/users", contextId = "userClient", configuration = com.bookmenow.payment.feign.config.FeignClientInterceptorConfig.class)
public interface UserClient {

    @GetMapping("/{id}")
    UserDTO getUserById (@PathVariable("id") Long id);

}
