package com.stupefy.stupefysubscription.service;

import java.util.List;

import com.stupefy.stupefysubscription.models.Subscription;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(name="SubscriptionService", targetNamespace = "http://stupefysubscription.com/")
public interface SubscriptionServiceInterface {
    @WebMethod
    public int requestSubscribe(@WebParam int creator_id, @WebParam int subscriber, @WebParam String apiKey);

    @WebMethod
    public List<Subscription> getRequests(@WebParam int offset,@WebParam int limit,@WebParam String apiKey);

    @WebMethod
    public int responseRequestSubs(@WebParam int creator_id, @WebParam int subscriber, @WebParam boolean isAccepted, @WebParam String apiKey);

    @WebMethod
    public boolean checkStatus(@WebParam int creator_id, @WebParam int subscriber, @WebParam String apiKey);
}
