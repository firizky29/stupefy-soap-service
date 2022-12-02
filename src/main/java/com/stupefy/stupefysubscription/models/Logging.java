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

    public boolean pushToDatabase() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO logging (description, IP, endpoint) VALUES ('" + this.description + "', '" + this.IP + "', '" + this.endpoint + "')";
            stmt.executeUpdate(sql);
            // stmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}