package org.example.DAO;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.example.tools.mongoDb;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public abstract class DAO<T> implements IDAO<T>{
    private final Type type =  ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    private final String className = type.getTypeName().replace("org.example.Models.", "").toLowerCase();

    private final java.lang.Object connection = DAOFactory.getConnection("");
    private final Gson gson = new Gson();
    
    public int create(T object) throws SQLException {

        if(connection.getClass().getName().equalsIgnoreCase(MongoClient.class.getName())) {
            var id = ((MongoClient)connection).getDatabase("Hollywood").getCollection(className).insertOne(new Document("json", new Gson().toJson(object))).getInsertedId();
            System.out.println("Object created with id: " + id);
        }
        else if (connection.getClass().getName().equalsIgnoreCase(Connection.class.getName())) {
            // insert into mysql
            ((Connection)connection).createStatement().executeUpdate("INSERT INTO " + object.getClass().getName().toLowerCase() + " VALUES (" + object + ");");
            return 0;
        }
        return 0;
    }

    public T find(String id) {
        T object = null;
        if(connection.getClass().getName().contains("mongo")) {
            // select from mongo
            object = gson.fromJson((Objects.requireNonNull(((MongoClient) connection).getDatabase("Hollywood").getCollection(className).find().first())).toJson(), (Type) type);

        }
        else if (connection.getClass().getName().contains("sql")) {
            // select from mysql
            try {
                var rs = ((Connection) connection).createStatement().executeQuery("SELECT * FROM " + className + " WHERE id = " + id + ";");
                while (rs.next()) {
                    object = parseFromSQL(rs);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return object;
    }

    public List<T> getAll() {
        System.out.println("Getting all objects");
        return null;
    }

    public T parseFromSQL(java.sql.ResultSet rs) throws SQLException {
        return null;
    }
}
