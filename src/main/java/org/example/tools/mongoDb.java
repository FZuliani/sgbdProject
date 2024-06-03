package org.example.tools;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class mongoDb {
    //singleton connect to mongodb
    private static MongoClient mg = null;

    private mongoDb() {
        // Private uniquement pour éviter l'instanciation.
    }

    public static MongoClient connect() {
        if(mg == null) {
            mg = MongoClients.create("mongodb://localhost:27017");
        }
        return mg;
    }
}
