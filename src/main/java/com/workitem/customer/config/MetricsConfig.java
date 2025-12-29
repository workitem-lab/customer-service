package com.workitem.customer.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public Counter customersCreatedCounter(MeterRegistry registry) {
        return Counter.builder("customer.created.count")
                .description("Number of customers created")
                .register(registry);
    }
}
