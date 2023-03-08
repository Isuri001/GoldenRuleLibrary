package lk.ijse.goldenrulelibrary.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.Borrowed_book_recordsDao;
import lk.ijse.goldenrulelibrary.dao.custom.impl.Borrowed_book_recordsDAOImpl;
import lk.ijse.goldenrulelibrary.service.custom.BorrowedBookRecordsService;
import lk.ijse.goldenrulelibrary.to.BorrowedBookRecords;

import java.sql.SQLException;

public class BorrowedBookRecordsServiceImpl implements BorrowedBookRecordsService {
    Borrowed_book_recordsDao borrowed_book_recordsDao = new Borrowed_book_recordsDAOImpl();
    @Override
    public String getRecordsCount() throws ClassNotFoundException, SQLException {
        return borrowed_book_recordsDao.getRecordsCount();
    }

    @Override
    public ObservableList<BorrowedBookRecords> getAllBorrowedBookRecords() throws SQLException, ClassNotFoundException {
        return borrowed_book_recordsDao.getAllBorrowedBookRecords();
    }

    @Override
    public String getBorrowedBookRecordsId() throws SQLException, ClassNotFoundException {
        return borrowed_book_recordsDao.getBorrowedBookRecordsId();
    }

    @Override
    public String getLastBorrowedBookRecordsId() throws SQLException, ClassNotFoundException {
        return borrowed_book_recordsDao.getLastBorrowedBookRecordsId();
    }

    @Override
    public boolean addBorrowedBookRecords(BorrowedBookRecords temp) throws SQLException, ClassNotFoundException {
        return borrowed_book_recordsDao.add(temp);
    }

    @Override
    public boolean updateStatus(BorrowedBookRecords borrowedBookRecords) throws SQLException, ClassNotFoundException {
        return borrowed_book_recordsDao.update(borrowedBookRecords);
    }

    @Override
    public ObservableList<BorrowedBookRecords> searchBorrowedBookRecords(String searchBy, String value) throws SQLException, ClassNotFoundException {
        return borrowed_book_recordsDao.search(searchBy,value);
    }

    @Override
    public String getDateDifference(String id) throws SQLException, ClassNotFoundException {
        return borrowed_book_recordsDao.getDateDifference(id);
    }
}
