package com.workitem.customer.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Filter;

@Component
public class CorrelationIdFilter implements Filter {

    public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    public static final String CORRELATION_ID_MDC_KEY = "correlationId";

    @Override
    public void doFilter(
            ServetRequest request,
            ServetResponse response,
            FilterChain chain
    ) throws IOException, ServletException{
        try{
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String correlationId = httpRequest.getHeader(CORRELATION_ID_HEADER);
            if(correlationId == null || correlationId.isBlank()){
                correlationId = UUID.randomUUID().toString();
            }

            MDC.put(CORRELATION_ID_MDC_KEY, correlationId);
            chain.doFilter(request, response);

        } finally {
            MDC.remove(CORRELATION_ID_MDC_KEY);
        }
    }
}
