package com.bookmenow.booking.feign.clients;
import com.bookmenow.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//this class is to request access directly to user and catalog through gateway
@FeignClient( name = "gateway", path = "/users", contextId = "userClient", configuration = com.bookmenow.booking.feign.config.FeignClientInterceptorConfig.class)
public interface UserClient {

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

    @GetMapping("/email/{email}")
    UserDTO getUserByEmail(@PathVariable("email") String email);

}
