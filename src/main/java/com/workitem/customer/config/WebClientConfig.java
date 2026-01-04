package com.workitem.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClient;
@Controller
public class WebClientConfig {
    @Bean
    RestClient paymentWebClient(RestClient.Builder buider){
        return buider
                .baseUrl("http://payment-service")
                .build();
    }
}
