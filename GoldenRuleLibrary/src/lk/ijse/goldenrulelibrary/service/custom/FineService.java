package lk.ijse.goldenrulelibrary.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.service.SuperService;
import lk.ijse.goldenrulelibrary.to.Fine;
import lk.ijse.goldenrulelibrary.to.IncomeTable;

import java.sql.SQLException;
import java.util.HashMap;

public interface FineService extends SuperService {
    ObservableList<Fine> getAllFine() throws SQLException, ClassNotFoundException;
    String getFineId() throws SQLException, ClassNotFoundException;
    String getLastFineId() throws SQLException, ClassNotFoundException;
    boolean addFine(Fine temp) throws SQLException, ClassNotFoundException;
    ObservableList<IncomeTable> getIncomeByDate(int value) throws SQLException, ClassNotFoundException;
    HashMap getIncomeBYMonth() throws SQLException, ClassNotFoundException;

}
