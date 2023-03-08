package lk.ijse.goldenrulelibrary.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.AuthorDao;
import lk.ijse.goldenrulelibrary.dao.custom.impl.AuthorDAOImpl;
import lk.ijse.goldenrulelibrary.service.custom.AuthorService;
import lk.ijse.goldenrulelibrary.to.Author;

import java.sql.SQLException;

public class AuthorServiceImpl implements AuthorService {
    AuthorDao authorDao = new AuthorDAOImpl();
    @Override
    public String getAuthorCount() throws ClassNotFoundException, SQLException {
        return authorDao.getAuthorCount();
    }

    @Override
    public ObservableList<Author> getAllAuthors() throws SQLException, ClassNotFoundException {
        return authorDao.getAllAuthors();
    }

    @Override
    public String getAuthorId() throws SQLException, ClassNotFoundException {
        return authorDao.getAuthorId();
    }

    @Override
    public String getLastAuthorId() throws SQLException, ClassNotFoundException {
        return authorDao.getLastAuthorId();
    }

    @Override
    public boolean addAuthor(Author temp) throws SQLException, ClassNotFoundException {
        return authorDao.add(temp);
    }

    @Override
    public ObservableList<Author> searchAuthor(String searchBy, String value) throws SQLException, ClassNotFoundException {
        return authorDao.search(searchBy,value);
    }
}
