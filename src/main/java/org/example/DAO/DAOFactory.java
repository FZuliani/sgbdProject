package org.example.DAO;
import org.example.tools.*;

public class DAOFactory {
    public static String connectionType = "";

    public static Object getConnection(ConnectionType type) {
        connectionType = type.toString();
        if(type == ConnectionType.MONGO) {
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
