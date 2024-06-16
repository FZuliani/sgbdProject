package org.example;
import org.example.DAO.*;
import org.example.Models.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        IDAO filmDAO = DAOFactory.create("film");
        //Get film by id
        System.out.println(filmDAO.find("2"));
        //get actors by film id
        for (Actors actor : ((FilmDAO)filmDAO).getActors("1")) {
            System.out.println(actor);
        }
        //Create a new film
        Films newFilm = new Films(){
            {
                set_id("4");
                setTitle("The Dark Knight");
                setReleaseDate("2008-07-18");
                setDuration("152");
                setGenre("Action, Crime, Drama");
                setDirector("Christopher Nolan");
                setDescription("When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.");
            }
        };
        filmDAO.create(newFilm);
    }
}