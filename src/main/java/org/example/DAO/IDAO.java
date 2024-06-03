package org.example.DAO;

import java.util.List;

public interface IDAO<T> {
    int create(T t);
    T find(String id);
    List<T> getAll();
}
