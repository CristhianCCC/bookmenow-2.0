package com.bookmenow.payment.feign.clients;
import com.bookmenow.dto.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gateway", path = "/bookings", contextId = "bookingClient", configuration = com.bookmenow.payment.feign.config.FeignClientInterceptorConfig.class)
public interface BookingClient {

    @GetMapping("/{id}")
    BookingDTO findBookingById (@PathVariable("id") Long id);
}
