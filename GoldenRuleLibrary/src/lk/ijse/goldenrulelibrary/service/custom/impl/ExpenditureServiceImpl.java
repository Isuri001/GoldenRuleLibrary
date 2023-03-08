package lk.ijse.goldenrulelibrary.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.ExpenditureDao;
import lk.ijse.goldenrulelibrary.dao.custom.impl.ExpenditureDAOImpl;
import lk.ijse.goldenrulelibrary.service.custom.ExpenditureService;
import lk.ijse.goldenrulelibrary.to.Expenditure;

import java.sql.SQLException;

public class ExpenditureServiceImpl implements ExpenditureService {
    ExpenditureDao expenditureDao = new ExpenditureDAOImpl();
    @Override
    public String getExpenditureCount() throws ClassNotFoundException, SQLException {
        return expenditureDao.getExpenditureCount();
    }

    @Override
    public ObservableList<Expenditure> getAllExpenditure() throws SQLException, ClassNotFoundException {
        return expenditureDao.getAllExpenditure();
    }

    @Override
    public String getTrasId() throws SQLException, ClassNotFoundException {
        return expenditureDao.getTrasId();
    }

    @Override
    public String getLastTrasId() throws SQLException, ClassNotFoundException {
        return expenditureDao.getLastTrasId();
    }

    @Override
    public boolean addExpenditure(Expenditure temp) throws SQLException, ClassNotFoundException {
        return expenditureDao.add(temp);
    }

    @Override
    public ObservableList<Expenditure> searchExpenditure(String searchBy, String value) throws SQLException, ClassNotFoundException {
        return expenditureDao.search(searchBy,value);
    }
}
