package lk.ijse.goldenrulelibrary.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.CrudDao;
import lk.ijse.goldenrulelibrary.to.BorrowedBookRecords;

import java.sql.SQLException;

public interface Borrowed_book_recordsDao extends CrudDao<BorrowedBookRecords,String> {
    String getRecordsCount() throws  ClassNotFoundException, SQLException;
    ObservableList<BorrowedBookRecords> getAllBorrowedBookRecords() throws SQLException, ClassNotFoundException;
    String getBorrowedBookRecordsId() throws SQLException, ClassNotFoundException;
    String getLastBorrowedBookRecordsId() throws SQLException, ClassNotFoundException;
    String getDateDifference(String id) throws SQLException, ClassNotFoundException;
    ObservableList<BorrowedBookRecords> search(String searchBy, String value) throws SQLException, ClassNotFoundException;
}
