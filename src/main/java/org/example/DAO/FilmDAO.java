package org.example.DAO;

import org.example.Models.Films;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmDAO extends DAO<Films> {

    @Override
    public Films parseFromSQL(ResultSet rs) throws SQLException {
        Films film = new Films();
        film.setId(rs.getString("id"));
        film.setTitle(rs.getString("title"));
        film.setReleaseDate(rs.getString("releaseDate"));
        film.setDuration(rs.getString("duration"));
        film.setGenre(rs.getString("genre"));
        film.setDirector(rs.getString("director"));
        film.setDescription(rs.getString("description"));
        //film.setActors(DAOFactory.create("actors").getManyToMany(rs.getString("id")));

        return film;
    }
}
