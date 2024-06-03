package org.example;

import org.example.tools.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        mongoDb.connect();
        mySqlDb.connect();
    }
}