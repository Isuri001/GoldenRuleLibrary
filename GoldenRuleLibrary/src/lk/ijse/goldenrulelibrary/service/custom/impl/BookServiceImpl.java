package lk.ijse.goldenrulelibrary.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.BookDao;
import lk.ijse.goldenrulelibrary.dao.custom.impl.BookDAOImpl;
import lk.ijse.goldenrulelibrary.service.custom.BookService;
import lk.ijse.goldenrulelibrary.to.Book;

import java.sql.SQLException;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDAOImpl();
    @Override
    public String getBookCount() throws ClassNotFoundException, SQLException {
        return bookDao.getBookCount();
    }

    @Override
    public ObservableList<Book> getAllBooks() throws SQLException, ClassNotFoundException {
        return bookDao.getAllBooks();
    }

    @Override
    public String getBookId() throws SQLException, ClassNotFoundException {
        return bookDao.getBookId();
    }

    @Override
    public String getLastBookId() throws SQLException, ClassNotFoundException {
        return bookDao.getLastBookId();
    }

    @Override
    public boolean addBook(Book temp) throws SQLException, ClassNotFoundException {
        return bookDao.add(temp);
    }

    @Override
    public boolean updateBook(Book book) throws SQLException, ClassNotFoundException {
        return bookDao.update(book);
    }

    @Override
    public boolean deleteBook(String id) throws SQLException, ClassNotFoundException {
        return bookDao.delete(id);
    }

    @Override
    public ObservableList<Book> searchBook(String searchBy, String value) throws SQLException, ClassNotFoundException {
        return bookDao.search(searchBy,value);
    }
}
