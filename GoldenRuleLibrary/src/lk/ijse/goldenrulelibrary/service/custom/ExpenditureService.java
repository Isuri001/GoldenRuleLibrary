package lk.ijse.goldenrulelibrary.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.service.SuperService;
import lk.ijse.goldenrulelibrary.to.Expenditure;

import java.sql.SQLException;

public interface ExpenditureService extends SuperService {
    String getExpenditureCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Expenditure> getAllExpenditure() throws SQLException, ClassNotFoundException;
    String getTrasId() throws SQLException, ClassNotFoundException;
    String getLastTrasId() throws SQLException, ClassNotFoundException;
    boolean addExpenditure(Expenditure temp) throws SQLException, ClassNotFoundException;
    ObservableList<Expenditure> searchExpenditure(String searchBy, String value) throws SQLException, ClassNotFoundException;

}
