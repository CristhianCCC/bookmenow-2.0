package com.bookmenow.booking.feign.clients;
import com.bookmenow.catalog.dto.CatalogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gateway", path = "/catalogs", contextId = "catalogClient", configuration = com.bookmenow.booking.feign.config.FeignClientInterceptorConfig.class)
public interface CatalogClient {

    @GetMapping("/id/{id}")
    CatalogDTO getCatalogById(@PathVariable ("id") Long id);
}
