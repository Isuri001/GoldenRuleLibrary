package lk.ijse.goldenrulelibrary.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.CrudDao;
import lk.ijse.goldenrulelibrary.to.Catagory;

import java.sql.SQLException;

public interface CategoryDao extends CrudDao<Catagory,String> {
    String getCatagoryCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Catagory> getAllCatagory() throws SQLException, ClassNotFoundException;
    String getCatagoryId() throws SQLException, ClassNotFoundException;
    String getLastcatagoryId() throws SQLException, ClassNotFoundException;
    ObservableList<Catagory> search(String searchBy, String value) throws SQLException, ClassNotFoundException;
}
