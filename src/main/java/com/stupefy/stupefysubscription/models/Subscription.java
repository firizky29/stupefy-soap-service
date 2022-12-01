package com.stupefy.stupefysubscription.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.stupefy.stupefysubscription.database.Database;
import com.stupefy.stupefysubscription.enums.Status;
import com.stupefy.stupefysubscription.httpclient.HttpClient;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Subscription")
@XmlType(propOrder = { "creator_id", "subscriber", "status" })
public class Subscription {
    private static Connection conn = Database.getConnection();
    private int creator_id;
    private int subscriber;
    private Status status;

    public Subscription(int creator_id, int subscriber, Status status) {
        this.creator_id = creator_id;
        this.subscriber = subscriber;
        this.status = status;
    }

    public Subscription() {
        this.creator_id = -1;
        this.subscriber = -1;
        this.status = Status.PENDING;
    }

    public static int addPendingSubs(int creator_id, int subscriber, String callbackUrl) {
        try {
            Statement stmt = conn.createStatement();
            int rowChange = stmt.executeUpdate("INSERT INTO subscriptions(creator_id, subscriber, status, callbackurl) VALUES(" + creator_id + ", " + subscriber +",'PENDING', '" + callbackUrl + "')");
            return rowChange;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } 
    }

    public static int respondPendingSubs(int creator_id, int subscriber, boolean isAccepted) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT callbackurl FROM subscriptions WHERE creator_id = " + creator_id + " AND subscriber = " + subscriber);
            rs.next();
            String callbackUrl = rs.getString("callbackurl");
            int rowChange = stmt.executeUpdate("UPDATE subscriptions SET status = " + (isAccepted ? "'ACCEPTED'" : "'REJECTED'") + " WHERE creator_id = " + creator_id + " AND subscriber = " + subscriber);
            if(rowChange>0) {
                // callback ke app
                String baseurl="http://localhost:8080/", dir="dummy";
                for(int i=8; i<callbackUrl.length(); i++) {
                    if(callbackUrl.charAt(i) == '/') {
                        baseurl = callbackUrl.substring(0, i+1);
                        dir = callbackUrl.substring(i+1);
                        break;
                    }
                }
                HttpClient client = new HttpClient(baseurl);
                if(isAccepted) {
                    client.putRespond(dir, "ACCEPTED");
                } else {
                    client.putRespond(dir, "REJECTED");
                }
            }
            return rowChange;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static List<Subscription> getPendingSubs(int offset, int limit) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subscriptions WHERE status = 'PENDING' ORDER BY last_updated DESC LIMIT " + offset + ", " + limit);
            List<Subscription> listSubs = new ArrayList<Subscription>();
            int i = 0;
            while (rs.next()) {
                listSubs.add(new Subscription(rs.getInt("creator_id"), rs.getInt("subscriber"), Status.valueOf(rs.getString("status"))));
                i++;
            }
            return listSubs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkStatus(int creator_id, int subscriber) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subscriptions WHERE creator_id = " + creator_id + " AND subscriber = " + subscriber);
            return Status.valueOf(rs.getString("status")) == Status.ACCEPTED;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int getCreator_id() {
        return creator_id;
    }

    public int getSubscriber() {
        return subscriber;
    }

    public Status getStatus() {
        return status;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public void setSubscriber(int subscriber) {
        this.subscriber = subscriber;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
