package com.stupefy.stupefysubscription.service;

import jakarta.jws.WebService;

@WebService(portName = "SubscriptionServicePort", serviceName = "SubscriptionService", targetNamespace = "http://stupefysubscription.com/", endpointInterface = "com.stupefy.stupefysubscription.service.SubscriptionServiceInterface")
public class SubscriptionServiceImpl implements SubscriptionServiceInterface {
    @Override
    public boolean requestSubscribe(int creator_id, int subscriber, String apiKey) {
        return true;
    }
    
    // @Override
    // public Subscription[] getRequests(String apiKey) {}

    @Override
    public boolean responseRequestSubs(int creator_id, int subscriber, boolean isAccepted, String apiKey) {
        return true;
    }

    @Override
    public boolean checkStatus(int creator_id, int subscriber, String apiKey) {
        return true;
    }
}
