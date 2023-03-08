package lk.ijse.goldenrulelibrary.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.service.SuperService;
import lk.ijse.goldenrulelibrary.to.BorrowedBookRecords;

import java.sql.SQLException;

public interface BorrowedBookRecordsService extends SuperService {
    String getRecordsCount() throws  ClassNotFoundException, SQLException;
    ObservableList<BorrowedBookRecords> getAllBorrowedBookRecords() throws SQLException, ClassNotFoundException;
    String getBorrowedBookRecordsId() throws SQLException, ClassNotFoundException;
    String getLastBorrowedBookRecordsId() throws SQLException, ClassNotFoundException;
    boolean addBorrowedBookRecords(BorrowedBookRecords temp) throws SQLException, ClassNotFoundException;
    boolean updateStatus(BorrowedBookRecords borrowedBookRecords) throws SQLException, ClassNotFoundException;
    ObservableList<BorrowedBookRecords> searchBorrowedBookRecords(String searchBy, String value) throws SQLException, ClassNotFoundException;
    String getDateDifference(String id) throws SQLException, ClassNotFoundException;

}
