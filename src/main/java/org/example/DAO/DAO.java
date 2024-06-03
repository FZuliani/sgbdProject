package org.example.DAO;


import java.util.List;

public abstract class DAO<Object> implements IDAO<Object>{
    public int create(Object object) {
        System.out.println("Creating object");
        return 0;
    }

    public Object find(String id) {
        System.out.println("Finding object");
        return null;
    }

    public List<Object> getAll() {
        System.out.println("Getting all objects");
        return null;
    }
}
