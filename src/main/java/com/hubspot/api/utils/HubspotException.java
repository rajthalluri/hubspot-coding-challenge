package com.hubspot.api.utils;

public class HubspotException extends Exception {

    private int code;
    private String message;

    public HubspotException(String message) {
        super(message);
    }

    public HubspotException(String message, Throwable cause) {
        super(message, cause);
    }

    public HubspotException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
