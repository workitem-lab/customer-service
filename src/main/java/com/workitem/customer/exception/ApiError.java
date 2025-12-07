package com.workitem.customer.exception;

public record ApiError(
        String timestamp, //ISO time as string.
        int status,
        String error,
        String code, // internal business/technical code (CUSTOMER_NOT_FOUND, VALIDATION_FAILED, etc.).
        String message,
        String path,
        String traceId // later we’ll link this to tracing (Micrometer/OTel).
) {}
