package lk.ijse.goldenrulelibrary.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.service.SuperService;
import lk.ijse.goldenrulelibrary.to.Book;

import java.sql.SQLException;

public interface BookService extends SuperService {
    String getBookCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Book> getAllBooks() throws SQLException, ClassNotFoundException;
    String getBookId() throws SQLException, ClassNotFoundException;
    String getLastBookId() throws SQLException, ClassNotFoundException;
    boolean addBook(Book temp) throws SQLException, ClassNotFoundException;
    boolean updateBook(Book book) throws SQLException, ClassNotFoundException;
    boolean deleteBook(String id) throws SQLException, ClassNotFoundException;
    ObservableList<Book> searchBook(String searchBy,String value) throws SQLException, ClassNotFoundException;

}
