package org.example.DAO;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.example.tools.mongoDb;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<Object> implements IDAO<Object>{
    private final java.lang.Object connection = DAOFactory.getConnection("mongo");
    private final Gson gson = new Gson();
    
    public int create(Object object) throws SQLException {

        if(connection.getClass().getName().equalsIgnoreCase(MongoClient.class.getName())) {
            var id = ((MongoClient)connection).getDatabase("Hollywood").getCollection(object.getClass().getName().toLowerCase()).insertOne(new Document("json", new Gson().toJson(object))).getInsertedId();
            System.out.println("Object created with id: " + id);
        }
        else if (connection.getClass().getName().equalsIgnoreCase(Connection.class.getName())) {
            // insert into mysql
            ((Connection)connection).createStatement().executeUpdate("INSERT INTO " + object.getClass().getName().toLowerCase() + " VALUES (" + object + ");");
            return 0;
        }
        return 0;
    }

    public Object find(String id) {
        Object object = null;
        if(connection.getClass().getName().equalsIgnoreCase(MongoClient.class.getName())) {
            object = gson.fromJson(((MongoClient)connection).getDatabase("Hollywood").getCollection(object.getClass().getName().toLowerCase()).find(new Document("_id", id)).first().toJson(), (Class<Object>) object.getClass());
            System.out.println("Object found: " + object);
        }
        else if (connection.getClass() == Connection.class) {
            // select from mysql

            return null;
        }
        return object;
    }

    public List<Object> getAll() {
        System.out.println("Getting all objects");
        return null;
    }
}
