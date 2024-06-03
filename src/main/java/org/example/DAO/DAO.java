package org.example.DAO;


import com.google.gson.Gson;
import org.bson.Document;
import org.example.tools.mongoDb;

import java.util.List;

public abstract class DAO<Object> implements IDAO<Object>{
    public int create(Object object) {
        var mongoClient = mongoDb.connect();
        mongoClient.getDatabase("Hollywood").getCollection(object.getClass().getName().toLowerCase()).insertOne(new Document("json", new Gson().toJson(object)));
        return 0;
    }

    public Object find(String id) {
        System.out.println("Finding object");
        return null;
    }

    public List<Object> getAll() {
        System.out.println("Getting all objects");
        return null;
    }
}
