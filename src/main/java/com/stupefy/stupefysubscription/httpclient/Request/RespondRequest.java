package com.stupefy.stupefysubscription.httpclient.Request;

public class RespondRequest {
    private final String status;
    private final String apiKey;

    public RespondRequest(String status, String apiKey) {
        this.status = status;
        this.apiKey = apiKey;
    }
}
