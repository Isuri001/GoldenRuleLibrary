package lk.ijse.goldenrulelibrary.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.PublisherDao;
import lk.ijse.goldenrulelibrary.dao.custom.impl.PublisherDAOImpl;
import lk.ijse.goldenrulelibrary.service.custom.PublisherService;
import lk.ijse.goldenrulelibrary.to.Publisher;

import java.sql.SQLException;

public class PublisherServiceImpl implements PublisherService {
    PublisherDao publisherDao =new PublisherDAOImpl();
    @Override
    public String getPublisherCount() throws ClassNotFoundException, SQLException {
        return publisherDao.getPublisherCount();
    }

    @Override
    public ObservableList<Publisher> getAllPublishers() throws SQLException, ClassNotFoundException {
        return publisherDao.getAllPublishers();
    }

    @Override
    public String getPublisherId() throws SQLException, ClassNotFoundException {
        return publisherDao.getPublisherId();
    }

    @Override
    public String getLastPublisherId() throws SQLException, ClassNotFoundException {
        return publisherDao.getLastPublisherId();
    }

    @Override
    public boolean addPublisher(Publisher temp) throws SQLException, ClassNotFoundException {
        return publisherDao.add(temp);
    }

    @Override
    public ObservableList<Publisher> searchPublisher(String searchBy, String value) throws SQLException, ClassNotFoundException {
        return publisherDao.search(searchBy,value);
    }
}
