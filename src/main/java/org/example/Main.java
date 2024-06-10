package org.example;
import org.example.DAO.*;
import org.example.Models.*;

public class Main {
    public static void main(String[] args) {

        IDAO filmDAO = DAOFactory.create("film");
        System.out.println(filmDAO.find("2"));
        for (Actors actor : ((FilmDAO)filmDAO).getActors("1")) {
            System.out.println(actor);
        }
    }
}