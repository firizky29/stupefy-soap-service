package com.stupefy.stupefysubscription.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Migrate {
    private static Connection conn = Database.getConnection();

    private static void createTableSubscription() {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS subscriptions(
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

    private static void createTableLogging() {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS logging(
                id INT AUTO_INCREMENT PRIMARY KEY,
                description VARCHAR(256) NOT NULL,
                IP VARCHAR(16) NOT NULL,
                endpoint VARCHAR(256) NOT NULL,
                requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )""");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createTableSubscription();
        createTableLogging();
    }
}
