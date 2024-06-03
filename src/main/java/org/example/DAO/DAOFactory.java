package org.example.DAO;

import com.mongodb.client.MongoClient;
import org.example.tools.*;

import java.sql.Connection;

public class DAOFactory {
    public static MongoClient connection;
    public static Connection sqlConnection;

    public static Object getConnection(String type) {
        if(type.equals("mongo")) {
            return mongoDb.connect();
        }
        return mySqlDb.connect();
    }

    public static IDAO create(String type) {
        if(type.equals("film")) {
            return new FilmDAO();
        } else if(type.equals("actor")) {
            return new ActorDAO();
        }
        return null;
    }
}
