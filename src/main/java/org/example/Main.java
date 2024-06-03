package org.example;

import org.example.DAO.*;
import org.example.Models.*;
import org.example.tools.*;

public class Main {
    public static void main(String[] args) {
       var mgdb = mongoDb.connect();
       var msdb = mySqlDb.connect();

       for (var dbName : mgdb.listDatabaseNames()) {
           System.out.println(dbName);
       }
        System.out.println("=====================================");
       //get all db names from mysql
        try {
            var stmt = msdb.createStatement();
            var rs = stmt.executeQuery("SHOW DATABASES");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}