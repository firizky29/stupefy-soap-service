package com.stupefy.stupefysubscription.models;


import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Response Object")
@XmlType(propOrder = { "status", "statusText", "data" })
public class RequestSubsResponse {
    private int status;
    private String statusText;
    private int data;

    public RequestSubsResponse() {
        this.status = 404;
        this.statusText = "Not found";
        this.data = -1;
    }

    public RequestSubsResponse(int status, String statusText, int data) {
        this.status = status;
        this.statusText = statusText;
        this.data = data;
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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
