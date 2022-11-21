package com.stupefy.stupefysubscription.database;

import java.sql.Connection;
import java.sql.Statement;

import com.stupefy.stupefysubscription.database.Database;

public class Migrate {
    private static Connection conn = Database.getConnection();

    private static void createTableSubscription() {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS subscription(
                creator_id INT NOT NULL,
                subscriber INT NOT NULL,
                status ENUM("PENDING", "ACCEPTED", "REJECTED") NOT NULL DEFAULT "PENDING",
                last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                PRIMARY KEY(creator_id, subscriber)
            )""");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createTableSubscription();
    }
}
