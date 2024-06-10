package org.example.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    int create(T t) throws SQLException;
    T find(String id);
    List<T> getAll();
    List<T> getManyToMany(String id, String tableName, String columnName, String columnName2);
}
