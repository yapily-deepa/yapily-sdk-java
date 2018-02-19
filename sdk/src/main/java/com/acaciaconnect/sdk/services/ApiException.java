package com.acaciaconnect.sdk.services;

import com.acaciaconnect.api.client.exceptions.BaseServiceException;

public class ApiException extends BaseServiceException {
    private static final long serialVersionUID = 1L;

    public ApiException(int code, String message) {
        super(ExceptionData.newBuilder()
                           .setCode(code)
                           .setMessage(message)
                           .build());
    }

    public ApiException(int code, String message, Throwable cause) {
        super(ExceptionData.newBuilder()
                           .setCode(code)
                           .setMessage(message)
                           .setCause(cause)
                           .build());
    }

    public ApiException(Exception cause) {
        super(ExceptionData.newBuilder()
                           .setCause(cause)
                           .build());
    }

}
