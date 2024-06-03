package org.example.DAO;

public interface IDAO<T> {
    void create(T t);
    T read(String id);
    void update(T t);
    void delete(T t);
}
