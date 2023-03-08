package lk.ijse.goldenrulelibrary.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.CrudDao;
import lk.ijse.goldenrulelibrary.to.Publisher;

import java.sql.SQLException;

public interface PublisherDao extends CrudDao<Publisher,String> {
    String getPublisherCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Publisher> getAllPublishers() throws SQLException, ClassNotFoundException;
    String getPublisherId() throws SQLException, ClassNotFoundException;
    String getLastPublisherId() throws SQLException, ClassNotFoundException;
    ObservableList<Publisher> search(String searchBy, String value) throws SQLException, ClassNotFoundException;
}
