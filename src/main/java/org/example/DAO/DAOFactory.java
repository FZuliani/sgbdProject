package org.example.DAO;
import org.example.tools.*;

public class DAOFactory {

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
