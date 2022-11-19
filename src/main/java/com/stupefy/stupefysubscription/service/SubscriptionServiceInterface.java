package com.stupefy.stupefysubscription.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(name="SubscriptionService", targetNamespace = "http://stupefysubscription.com/")
public interface SubscriptionServiceInterface {
    @WebMethod
    public boolean requestSubscribe(@WebParam int creator_id, @WebParam int subscriber, @WebParam String apiKey);

    // @WebMethod
    // public Subscription[] getRequests(@WebParam String apiKey);

    @WebMethod
    public boolean responseRequestSubs(@WebParam int creator_id, @WebParam int subscriber, @WebParam boolean isAccepted, @WebParam String apiKey);

    @WebMethod
    public boolean checkStatus(@WebParam int creator_id, @WebParam int subscriber, @WebParam String apiKey);
}
