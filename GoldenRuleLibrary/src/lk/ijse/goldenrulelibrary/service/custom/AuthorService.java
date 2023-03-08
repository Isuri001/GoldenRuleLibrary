package lk.ijse.goldenrulelibrary.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.service.SuperService;
import lk.ijse.goldenrulelibrary.to.Author;

import java.sql.SQLException;

public interface AuthorService extends SuperService {
    String getAuthorCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Author> getAllAuthors() throws SQLException, ClassNotFoundException;
    String getAuthorId() throws SQLException, ClassNotFoundException;
    String getLastAuthorId() throws SQLException, ClassNotFoundException;
    boolean addAuthor(Author temp) throws SQLException, ClassNotFoundException;
    ObservableList<Author> searchAuthor(String searchBy, String value) throws SQLException, ClassNotFoundException;

}
