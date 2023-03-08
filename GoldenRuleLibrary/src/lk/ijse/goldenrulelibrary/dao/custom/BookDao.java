package lk.ijse.goldenrulelibrary.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.CrudDao;
import lk.ijse.goldenrulelibrary.to.Book;

import java.sql.SQLException;

public interface BookDao extends CrudDao<Book,String> {
    String getBookCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Book> getAllBooks() throws SQLException, ClassNotFoundException;
    String getBookId() throws SQLException, ClassNotFoundException;
    String getLastBookId() throws SQLException, ClassNotFoundException;
    ObservableList<Book> search(String searchBy, String value) throws SQLException, ClassNotFoundException;
}
