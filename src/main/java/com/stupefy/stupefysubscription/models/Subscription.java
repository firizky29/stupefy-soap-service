package com.stupefy.stupefysubscription.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.stupefy.stupefysubscription.database.Database;
import com.stupefy.stupefysubscription.enums.Status;

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

    public static int addPendingSubs(int creator_id, int subscriber) {
        try {
            Statement stmt = conn.createStatement();
            int rowChange = stmt.executeUpdate("INSERT INTO subscriptions(creator_id, subscriber, status) VALUES(" + creator_id + ", " + subscriber +",'PENDING')");
            return rowChange;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } 
    }

    public static int responsePendingSubs(int creator_id, int subscriber, boolean isAccepted) {
        try {
            Statement stmt = conn.createStatement();
            int rowChange = stmt.executeUpdate("UPDATE subscriptions SET status = " + (isAccepted ? "'ACCEPTED'" : "'REJECTED'") + " WHERE creator_id = " + creator_id + " AND subscriber = " + subscriber);
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
}
