package com.stupefy.stupefysubscription.models;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "Response Object")
@XmlType(propOrder = { "status", "statusText", "data" })
public class RequestsResponse {
    private int status;
    String statusText;
    List<Subscription> data;

    public RequestsResponse(int status, String statusText, List<Subscription> data) {
        this.status = status;
        this.statusText = statusText;
        this.data = data;
    }

    public RequestsResponse() {
        this.status = 404;
        this.statusText = "Not found";
        this.data = Collections.<Subscription>emptyList();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public List<Subscription> getData() {
        return data;
    }

    public void setData(List<Subscription> data) {
        this.data = data;
    }
}
