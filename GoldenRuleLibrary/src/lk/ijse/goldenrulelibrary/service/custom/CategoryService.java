package lk.ijse.goldenrulelibrary.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.service.SuperService;
import lk.ijse.goldenrulelibrary.to.Catagory;

import java.sql.SQLException;

public interface CategoryService extends SuperService {
    String getCatagoryCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Catagory> getAllCatagory() throws SQLException, ClassNotFoundException;
    String getCatagoryId() throws SQLException, ClassNotFoundException;
    String getLastcatagoryId() throws SQLException, ClassNotFoundException;
    boolean addCatagory(Catagory temp) throws SQLException, ClassNotFoundException;
    ObservableList<Catagory> searchCatagory(String searchBy, String value) throws SQLException, ClassNotFoundException;

}
