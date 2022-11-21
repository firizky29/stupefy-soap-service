package com.stupefy.stupefysubscription.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private Connection conn;
    private static Database db;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DB_HOST = System.getenv("DB_HOST");
            String DB_NAME = System.getenv("MYSQL_DATABASE");
            String DB_URL = "jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME;
            String DB_ROOT_PASSWORD = System.getenv("MYSQL_ROOT_PASSWORD"); //TODO
            this.conn = DriverManager.getConnection(DB_URL, "root", DB_ROOT_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        if (db == null) {
            db = new Database();
        }
        return db.conn;
    }
}
