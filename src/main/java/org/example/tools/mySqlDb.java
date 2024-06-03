package org.example.tools;

import java.sql.DriverManager;

public class mySqlDb {
    //singleton connect to mysqlexit
    
    private static java.sql.Connection conn = null;

    private mySqlDb() {
        // Private uniquement pour éviter l'instanciation.
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
