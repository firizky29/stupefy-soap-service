package com.stupefy.stupefysubscription.service;

import java.util.List;

import com.stupefy.stupefysubscription.models.Subscription;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService(name="SubscriptionService", targetNamespace = "http://stupefysubscription.com/")
public interface SubscriptionServiceInterface {
    @WebMethod(operationName = "requestSubscribe")
    @WebResult(name = "rowChange")
    public int requestSubscribe(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber") int subscriber, @WebParam(name = "apiKey") String apiKey);

    @WebMethod(operationName = "getRequests")
    @WebResult(name = "Request")
    public List<Subscription> getRequests(@WebParam(name = "offset") int offset,@WebParam(name = "limit") int limit,@WebParam(name = "apiKey") String apiKey);

    @WebMethod(operationName = "responseRequestSubs")
    @WebResult(name = "rowChange")
    public int responseRequestSubs(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber") int subscriber, @WebParam(name = "isAccepted") boolean isAccepted, @WebParam(name = "apiKey") String apiKey);

    @WebMethod
    public boolean checkStatus(@WebParam int creator_id, @WebParam int subscriber, @WebParam String apiKey);
}
