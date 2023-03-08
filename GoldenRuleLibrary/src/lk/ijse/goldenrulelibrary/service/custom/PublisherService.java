package lk.ijse.goldenrulelibrary.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.service.SuperService;
import lk.ijse.goldenrulelibrary.to.Publisher;

import java.sql.SQLException;

public interface PublisherService extends SuperService {
    String getPublisherCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Publisher> getAllPublishers() throws SQLException, ClassNotFoundException;
    String getPublisherId() throws SQLException, ClassNotFoundException;
    String getLastPublisherId() throws SQLException, ClassNotFoundException;
    boolean addPublisher(Publisher temp) throws SQLException, ClassNotFoundException;
    ObservableList<Publisher> searchPublisher(String searchBy, String value) throws SQLException, ClassNotFoundException;

}
