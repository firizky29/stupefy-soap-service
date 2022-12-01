package com.stupefy.stupefysubscription.service;

import com.stupefy.stupefysubscription.enums.Status;
import com.stupefy.stupefysubscription.models.RequestSubsResponse;
import com.stupefy.stupefysubscription.models.RequestsResponse;
import com.stupefy.stupefysubscription.models.StatusResponse;
import com.stupefy.stupefysubscription.models.SubscribeResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService(name="SubscriptionService", targetNamespace = "http://stupefysubscription.com/")
public interface SubscriptionServiceInterface {
    @WebMethod(operationName = "requestSubscribe")
    @WebResult(name = "SubscribeResponse")
    public SubscribeResponse requestSubscribe(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber") int subscriber, @WebParam(name = "apiKey") String apiKey);

    @WebMethod(operationName = "getRequests")
    @WebResult(name = "RequestsResponse")
    public RequestsResponse getRequests(@WebParam(name = "offset") int offset, @WebParam(name = "limit") int limit, @WebParam(name = "apiKey") String apiKey);

    @WebMethod(operationName = "respondRequestSubs")
    @WebResult(name = "RequestResponse")
    public RequestSubsResponse respondRequestSubs(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber") int subscriber, @WebParam(name = "isAccepted") boolean isAccepted, @WebParam(name = "apiKey") String apiKey);

    @WebMethod
    public StatusResponse checkStatus(@WebParam int creator_id, @WebParam int subscriber, @WebParam String apiKey);
}
