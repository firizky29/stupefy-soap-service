package com.stupefy.stupefysubscription.models;


import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "Response Object")
@XmlType(propOrder = { "status", "statusText", "data" })
public class StatusResponse {
    private int status;
    private String statusText;
    private boolean data;

    public StatusResponse(int status, String statusText, boolean data) {
        this.status = status;
        this.statusText = statusText;
        this.data = data;
    }

    public StatusResponse() {
        this.status = 404;
        this.statusText = "Not found";
        this.data = false;
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

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }


}
