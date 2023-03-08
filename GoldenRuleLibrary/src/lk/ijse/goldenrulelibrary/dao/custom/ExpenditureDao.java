package lk.ijse.goldenrulelibrary.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.CrudDao;
import lk.ijse.goldenrulelibrary.to.Expenditure;

import java.sql.SQLException;

public interface ExpenditureDao extends CrudDao<Expenditure,String> {
    String getExpenditureCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Expenditure> getAllExpenditure() throws SQLException, ClassNotFoundException;
    String getTrasId() throws SQLException, ClassNotFoundException;
    String getLastTrasId() throws SQLException, ClassNotFoundException;
    ObservableList<Expenditure> search(String searchBy, String value) throws SQLException, ClassNotFoundException;
}
