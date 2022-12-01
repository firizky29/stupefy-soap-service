package com.stupefy.stupefysubscription.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stupefy.stupefysubscription.models.*;

import jakarta.annotation.Resource;
import jakarta.jws.WebService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;

@WebService(portName = "SubscriptionServicePort", serviceName = "SubscriptionService", targetNamespace = "http://stupefysubscription.com/", endpointInterface = "com.stupefy.stupefysubscription.service.SubscriptionServiceInterface")
public class SubscriptionServiceImpl implements SubscriptionServiceInterface {
    @Resource
    WebServiceContext context;

    @Override
    public SubscribeResponse requestSubscribe(int creator_id, int subscriber, String apiKey) {
        if(apiKey.equals("REST")){
            HttpServletRequest request = (HttpServletRequest) context.getMessageContext().get(MessageContext.SERVLET_REQUEST);
            int res = Subscription.addPendingSubs(creator_id, subscriber);
            return new SubscribeResponse(200, "OK", res);
        } else{
            return new SubscribeResponse(401, "Unauthorized", -1);
        }
    }

    @Override
    public RequestsResponse getRequests(int offset, int limit, String apiKey) {
        if(apiKey.equals("REST")){
            HttpServletRequest request = (HttpServletRequest)context.getMessageContext().get(MessageContext.SERVLET_REQUEST);
            List<Subscription> res = Subscription.getPendingSubs(offset, limit);
            return new RequestsResponse(200, "NOT OK", res);
        } else{
            return new RequestsResponse(401, "Unauthorized", Collections.<Subscription>emptyList());
        }
    }

    @Override
    public RequestSubsResponse respondRequestSubs(int creator_id, int subscriber, boolean isAccepted, String apiKey) {
        if(apiKey.equals("REST")){
            HttpServletRequest request = (HttpServletRequest)context.getMessageContext().get(MessageContext.SERVLET_REQUEST);
            int res = Subscription.respondPendingSubs(creator_id, subscriber, isAccepted);
            return new RequestSubsResponse(200, "OK", res);
        } else{
            return new RequestSubsResponse(401, "Unauthorized", -1);
        }
    }

    @Override
    public StatusResponse checkStatus(int creator_id, int subscriber, String apiKey) {
        if(apiKey.equals("REST")){
            HttpServletRequest request = (HttpServletRequest)context.getMessageContext().get(MessageContext.SERVLET_REQUEST);
            return new StatusResponse(200, "OK", false);
        } else{
            return new StatusResponse(401, "Unauthorized", false);
        }
    }
}
