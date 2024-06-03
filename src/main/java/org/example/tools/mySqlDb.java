package org.example.tools;

import java.sql.DriverManager;

public class mySqlDb {
    //singleton connect to mysql
    private static java.sql.Connection conn = null;

    private mySqlDb() {
        // Exists only to defeat instantiation.
    }

    public static java.sql.Connection connect() {
        if(conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
