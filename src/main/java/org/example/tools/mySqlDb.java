package org.example.tools;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.DriverManager;
import java.sql.ResultSet;


public class mySqlDb {
    //singleton connect to mysqlexit
    
    private static java.sql.Connection conn = null;

    private mySqlDb() {
        // Private uniquement pour éviter l'instanciation.
    }

    public static java.sql.Connection connect() {
        if(conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hollywood", "root", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static JsonArray convert(ResultSet resultSet) throws Exception {

        JsonArray jsonArray = new JsonArray();

        while (resultSet.next()) {

            int columns = resultSet.getMetaData().getColumnCount();
            JsonObject obj = new JsonObject();

            Gson gson = new Gson();
            for (int i = 0; i < columns; i++)
                obj.add(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), gson.toJsonTree(resultSet.getObject(i + 1)));

            jsonArray.add(obj);
        }
        return jsonArray;
    }
}
