package org.example.DAO;

import org.example.Models.Actors;
import org.example.Models.Films;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmDAO extends DAO<Films> {

    @Override
    public Films parseFromSQL(ResultSet rs) throws SQLException {
        Films film = new Films();
        film.setId(rs.getString("id"));
        film.setTitle(rs.getString("title"));
        film.setDirector(rs.getString("director"));
        film.setGenre(rs.getString("genre"));
        film.setReleaseDate(rs.getString("release_date"));
        film.setDuration(rs.getString("duration"));
        film.setDescription(rs.getString("description"));
        film.setActors(getActors(film.getId()));
        return film;
    }

    public List<Actors> getActors(String id) {
        return DAOFactory.create("actor").getManyToMany(id, "film_actors", "film_id");
    }
}
