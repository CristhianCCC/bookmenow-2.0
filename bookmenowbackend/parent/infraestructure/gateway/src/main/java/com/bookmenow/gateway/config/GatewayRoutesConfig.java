package com.bookmenow.gateway.config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//setting up the reoutes
@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                // auth route for login/signup ---------------------------------------------------------------------------------
                .route("auth-route", r -> r
                .path("/auth/**")
                .uri("lb://user") //name of the micro service
                )

                //authorization path for user (validation)--------------------------------------------------------------
                .route(p -> p
                        .path("/users/**")
                        .filters(f -> f.addRequestHeader("user-service", "Request"))
                        .uri("lb://user")) //name of the micro service

                //Catalog service --------------------------------------------------------------------------------------
                .route("catalog", r -> r
                        .path("/catalogs/**")
                        .uri("lb://catalog")
                )

                //Booking service --------------------------------------------------------------------------------------
                .route("booking", r -> r
                        .path("/bookings/**")
                        .uri("lb://booking")
                )

                .build();
    }
}
