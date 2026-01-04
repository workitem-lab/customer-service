package com.workitem.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.okhttp3.OkHttpSender;

@Configuration
public class TracingConfig {

    @Bean
    public OkHttpSender zipkinSender() {
        //Zipkin base URL - adjust to your infra
        return OkHttpSender.create("http://zipkin:9411/api/v2/spans");
    }
    @Bean
    public AsyncReporter<zipkin2.Span> zipkinReporter(OkHttpSender sender) {
        return AsyncReporter.create(sender);
    }



}
