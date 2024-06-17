package org.example.DAO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.example.tools.ConnectionType;
import org.example.tools.mySqlDb;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class DAO<T> implements IDAO<T>{
    private final Type type =  ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    private final String className = type.getTypeName().replace("org.example.Models.", "").toLowerCase();

    private final java.lang.Object connection = DAOFactory.getConnection(ConnectionType.MONGO);
    private final Gson gson = new Gson();

    public int create(T object) throws SQLException {

        return 0;
    }

    public T find(String id) {
        T object = null;
        if(DAOFactory.connectionType.equals(ConnectionType.MONGO)) {
            // select from mongo
            Bson filter = Filters.eq("_id", id);
            object = gson.fromJson((Objects.requireNonNull(((MongoClient) connection).getDatabase("Hollywood").getCollection(className).find(filter).first()).toJson()), (Type) type);

        }
        else if(DAOFactory.connectionType.equals(ConnectionType.MYSQL)) {
            // select from mysql
            try {
                var rs = ((Connection) connection).createStatement().executeQuery("SELECT * FROM " + className + " WHERE id = " + id + ";");
                //convert to JSON
                JsonArray jsonArray = mySqlDb.convert(rs);
                object = gson.fromJson(jsonArray.get(0), (Type) type);

                int test = 0;
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        return object;
    }

    public List<T> getAll() {
        ArrayList<T> object = new ArrayList<T>();
        if(DAOFactory.connectionType.equals(ConnectionType.MONGO)) {
            // select from mongo
            var rs = ((MongoClient) connection).getDatabase("Hollywood").getCollection(className).find();
            for (Document doc : rs) {
                object.add(gson.fromJson(doc.toJson(), (Type) type));
            }
        }
        else if(DAOFactory.connectionType.equals(ConnectionType.MYSQL)) {
            // select from mysql
            try {
                var rs = ((Connection) connection).createStatement().executeQuery("SELECT * FROM " + className + ";");
                //convert to JSON
                JsonArray jsonArray = mySqlDb.convert(rs);
                for (int i = 0; i < jsonArray.size(); i++) {
                    object.add(gson.fromJson(jsonArray.get(i), (Type) type));
                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        return object;
    }

    public T parseFromSQL(java.sql.ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public List<T> getManyToMany(String id, String tableName, String columnFrom, String columnTo) {
        ArrayList<T> object = new ArrayList<T>();
        if(DAOFactory.connectionType.equals(ConnectionType.MONGO)) {
            //get from the inside table
            var rs = ((MongoClient) connection).getDatabase("Hollywood").getCollection(tableName).find(Filters.eq("_id", id)).first().getList("film_actors", Document.class);
            for (Document doc : rs) {
                object.add(gson.fromJson(doc.toJson(), (Type) type));
            }
        }
        else if(DAOFactory.connectionType.equals(ConnectionType.MYSQL)) {
            //get from an intermediate table
            try {
                var rs = ((Connection) connection).prepareStatement("SELECT * FROM " + tableName + " WHERE " + columnFrom + " = " + id + ";").executeQuery();
                //convert to JSON
                JsonArray jsonArray = mySqlDb.convert(rs);
                for (int i = 0; i < jsonArray.size(); i++) {
                    String idActor = jsonArray.get(i).getAsJsonObject().get(columnTo).getAsString();
                    object.add(this.find(idActor));
                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        return object;
    }

}
