package lk.ijse.goldenrulelibrary.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.CrudDao;
import lk.ijse.goldenrulelibrary.to.Author;

import java.sql.SQLException;

public interface AuthorDao extends CrudDao<Author,String> {
    String getAuthorCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Author> getAllAuthors() throws SQLException, ClassNotFoundException;
    String getAuthorId() throws SQLException, ClassNotFoundException;
    String getLastAuthorId() throws SQLException, ClassNotFoundException;
    ObservableList search(String searchBy,String value) throws SQLException, ClassNotFoundException;

}
