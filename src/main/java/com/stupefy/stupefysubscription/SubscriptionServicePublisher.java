package com.stupefy.stupefysubscription;

import com.stupefy.stupefysubscription.service.SubscriptionServiceImpl;

import jakarta.xml.ws.Endpoint;

public class SubscriptionServicePublisher {
    public static void main(String[ ] args) {
        Endpoint.publish("http://localhost:3101/subscriptionservice", new SubscriptionServiceImpl());
    }
}