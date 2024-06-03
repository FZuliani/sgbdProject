package org.example;

import com.mongodb.client.MongoClient;
import org.example.DAO.*;
import org.example.Models.*;
import org.example.tools.*;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        MongoClient mgdb = mongoDb.connect();
        Connection msdb = mySqlDb.connect();

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