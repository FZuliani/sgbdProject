package org.example.DAO;

import java.sql.Connection;

public class DAOFactory {
    public static Connection connection;


    public static Connection getConnection() {
        return connection;
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
