package com.stupefy.stupefysubscription.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stupefy.stupefysubscription.constants.APIKey;
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
        if(apiKey.equals(APIKey.APP)){
            HttpServletRequest request = (HttpServletRequest) context.getMessageContext().get(MessageContext.SERVLET_REQUEST);

            String ip = request.getRemoteAddr();
            Logging log = new Logging("Request to subscribe from " + ip, ip, "SubscriptionService/requestSubscribe");
            log.pushToDatabase();

            int res = Subscription.addPendingSubs(creator_id, subscriber);
            return new SubscribeResponse(200, "OK", res);
        } else{
            return new SubscribeResponse(401, "Unauthorized", -1);
        }
    }

    @Override
    public RequestsResponse getRequests(int offset, int limit, String apiKey) {
        if(apiKey.equals(APIKey.REST)){
            HttpServletRequest request = (HttpServletRequest)context.getMessageContext().get(MessageContext.SERVLET_REQUEST);

            String ip = request.getRemoteAddr();
            Logging log = new Logging("Request to get requests from " + ip, ip, "SubscriptionService/getRequests");
            log.pushToDatabase();

            List<Subscription> res = Subscription.getPendingSubs(offset, limit);
            return new RequestsResponse(200, "OK", res);
        } else{
            return new RequestsResponse(401, "Unauthorized", Collections.<Subscription>emptyList());
        }
    }

    @Override
    public RequestSubsResponse respondRequestSubs(int creator_id, int subscriber, boolean isAccepted, String apiKey) {
        if(apiKey.equals(APIKey.REST)){
            HttpServletRequest request = (HttpServletRequest)context.getMessageContext().get(MessageContext.SERVLET_REQUEST);

            String ip = request.getRemoteAddr();
            Logging log = new Logging("Request to respond to request from " + ip, ip, "SubscriptionService/respondRequestSubs");
            log.pushToDatabase();

            int res = Subscription.respondPendingSubs(creator_id, subscriber, isAccepted);
            return new RequestSubsResponse(200, "OK", res);
        } else{
            return new RequestSubsResponse(401, "Unauthorized", -1);
        }
    }

    @Override
    public StatusResponse checkStatus(int creator_id, int subscriber, String apiKey) {
        if(apiKey.equals(APIKey.APP)){
            HttpServletRequest request = (HttpServletRequest)context.getMessageContext().get(MessageContext.SERVLET_REQUEST);

            String ip = request.getRemoteAddr();
            Logging log = new Logging("Request to check status from " + ip, ip, "SubscriptionService/checkStatus");
            log.pushToDatabase();

            boolean res = Subscription.checkStatus(creator_id, subscriber);
            return new StatusResponse(200, "OK", res);
        } else{
            return new StatusResponse(401, "Unauthorized", false);
        }
    }
}
