package org.example;

import com.mongodb.client.MongoClient;
import org.example.DAO.*;
import org.example.Models.*;
import org.example.tools.*;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        IDAO filmDAO = DAOFactory.create("film");
        System.out.println(filmDAO.find("1"));
    }
}