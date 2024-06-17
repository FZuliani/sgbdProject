package org.example.DAO;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.example.Models.Actors;
import org.example.Models.Films;
import org.example.tools.ConnectionType;

import java.sql.Connection;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmDAO extends DAO<Films> {

    @Override
    public Films parseFromSQL(ResultSet rs) throws SQLException {
        Films film = new Films();
        film.set_id(rs.getString("id"));
        film.setTitle(rs.getString("title"));
        film.setDirector(rs.getString("director"));
        film.setGenre(rs.getString("genre"));
        film.setReleaseDate(rs.getString("release_date"));
        film.setDuration(rs.getString("duration"));
        film.setDescription(rs.getString("description"));
        film.setActors(getActors(film.get_id()));
        return film;
    }

    public List<Actors> getActors(String id) {
        List<Actors> actors = null;
        if(DAOFactory.connectionType.equals(ConnectionType.MONGO)) {
            actors = DAOFactory.create("actor").getManyToMany(id, "films", "film_id", "actor_id");
        }
        else if (DAOFactory.connectionType.equals(ConnectionType.MYSQL)) {
            actors = DAOFactory.create("actor").getManyToMany(id, "film_actors", "film_id", "actor_id");
        }
        return actors;
    }

    public int create(Films film) throws SQLException {
        int result = 0;
        if (DAOFactory.connectionType.equals(ConnectionType.MONGO)) {
            // insert into mongo
            var json = new Gson().toJson(film, Films.class);
            var id = ((MongoClient) DAOFactory.getConnection(ConnectionType.MONGO)).getDatabase("Hollywood").getCollection("films").insertOne(Document.parse(json)).getInsertedId();
            System.out.println("Object created with id: " + id);
        } else if (DAOFactory.connectionType.equals(ConnectionType.MYSQL)) {
            // insert into mysql
            String query = "INSERT INTO films (id, title, releaseDate, duration, genre, director, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try {
                var ps = ((Connection) DAOFactory.getConnection(ConnectionType.MYSQL)).prepareStatement(query);
                ps.setString(1, film.get_id());
                ps.setString(2, film.getTitle());
                ps.setString(3, film.getReleaseDate());
                ps.setString(4, film.getDuration());
                ps.setString(5, film.getGenre());
                ps.setString(6, film.getDirector());
                ps.setString(7, film.getDescription());
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return result;
    }

}
