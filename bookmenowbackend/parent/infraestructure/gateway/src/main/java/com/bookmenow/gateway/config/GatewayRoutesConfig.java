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
                .path("/auth/**")//controller name
                .uri("lb://user") //microservice name
                )

                //authorization path for user (validation)--------------------------------------------------------------
                .route(p -> p
                        .path("/users/**")
                        .filters(f -> f.addRequestHeader("user-service", "Request"))
                        .uri("lb://user")) //microservice name

                //Catalog service --------------------------------------------------------------------------------------
                .route("catalog", r -> r
                        .path("/catalogs/**")//controller name
                        .uri("lb://catalog")//microservice name
                )

                //Booking service --------------------------------------------------------------------------------------
                .route("booking", r -> r
                        .path("/bookings/**") //controller name
                        .uri("lb://booking")//microservice name
                )

                //Payment service --------------------------------------------------------------------------------------
                .route("payment", r -> r
                        .path("/payments/**")//controller name
                        .uri("lb://payment")//microservice name
                )

                .build();
    }
}
