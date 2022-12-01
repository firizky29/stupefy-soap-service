package com.stupefy.stupefysubscription.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.stupefy.stupefysubscription.database.Database;
import com.stupefy.stupefysubscription.enums.Status;

public class Logging {
    private static Connection conn = Database.getConnection();
    private int id;
    private String description;
    private String IP;
    private String endpoint;

    public Logging(String description, String IP, String endpoint) {
        this.description = description;
        this.IP = IP;
        this.endpoint = endpoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}