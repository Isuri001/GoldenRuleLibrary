package lk.ijse.goldenrulelibrary.dao;

import java.sql.SQLException;

public interface CrudDao<T,ID> extends SuperDao{
    boolean add(T obj) throws SQLException,ClassNotFoundException;
    boolean delete(ID id) throws SQLException,ClassNotFoundException;
    boolean update(T obj) throws SQLException,ClassNotFoundException;
    boolean search(ID id) throws SQLException,ClassNotFoundException;
}
