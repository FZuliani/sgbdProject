package org.example.DAO;

import org.example.Models.Actors;

import java.sql.Array;
import java.sql.SQLException;
import java.util.List;

public class ActorDAO extends DAO<Actors>{


    @Override
    public Actors parseFromSQL(java.sql.ResultSet rs) throws SQLException {
        Actors actor = new Actors();
        actor.setId(rs.getString("id"));
        actor.setName(rs.getString("name"));
        actor.setSurname(rs.getString("surname"));
        actor.setBirthdate(rs.getString("birthdate"));
        return actor;
    }

    public List<Actors> findAllByFilmId(Array actors) {
        
        return null;
    }
}
