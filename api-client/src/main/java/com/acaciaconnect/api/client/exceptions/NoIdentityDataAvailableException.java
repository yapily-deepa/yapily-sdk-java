package com.acaciaconnect.api.client.exceptions;

public class NoIdentityDataAvailableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    NoIdentityDataAvailableException(String bankId) {
        super("No identity data available for bank id: " + bankId);
    }

}
