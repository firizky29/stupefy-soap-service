package com.stupefy.stupefysubscription.service;

import java.util.List;

import com.stupefy.stupefysubscription.models.Subscription;

import jakarta.jws.WebService;

@WebService(portName = "SubscriptionServicePort", serviceName = "SubscriptionService", targetNamespace = "http://stupefysubscription.com/", endpointInterface = "com.stupefy.stupefysubscription.service.SubscriptionServiceInterface")
public class SubscriptionServiceImpl implements SubscriptionServiceInterface {

    @Override
    public int requestSubscribe(int creator_id, int subscriber, String apiKey) {
        int res = Subscription.addPendingSubs(creator_id, subscriber);
        return res;
    }
    
    @Override
    public List<Subscription> getRequests(int offset, int limit, String apiKey) {
        List<Subscription> res = Subscription.getPendingSubs(offset, limit);
        return res;
    }

    @Override
    public int respondRequestSubs(int creator_id, int subscriber, boolean isAccepted, String apiKey) {
        int res = Subscription.respondPendingSubs(creator_id, subscriber, isAccepted);
        return res;
    }

    @Override
    public boolean checkStatus(int creator_id, int subscriber, String apiKey) {
        return false;
    }
}
