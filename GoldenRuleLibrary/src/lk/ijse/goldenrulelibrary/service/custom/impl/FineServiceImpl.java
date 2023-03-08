package lk.ijse.goldenrulelibrary.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.FineDao;
import lk.ijse.goldenrulelibrary.dao.custom.impl.FineDAOImpl;
import lk.ijse.goldenrulelibrary.service.custom.FineService;
import lk.ijse.goldenrulelibrary.to.Fine;
import lk.ijse.goldenrulelibrary.to.IncomeTable;

import java.sql.SQLException;
import java.util.HashMap;

public class FineServiceImpl implements FineService {
    FineDao fineDao = new FineDAOImpl();
    @Override
    public ObservableList<Fine> getAllFine() throws SQLException, ClassNotFoundException {
        return fineDao.getAllFine();
    }

    @Override
    public String getFineId() throws SQLException, ClassNotFoundException {
        return fineDao.getFineId();
    }

    @Override
    public String getLastFineId() throws SQLException, ClassNotFoundException {
        return fineDao.getLastFineId();
    }

    @Override
    public boolean addFine(Fine temp) throws SQLException, ClassNotFoundException {
        return fineDao.add(temp);
    }

    @Override
    public ObservableList<IncomeTable> getIncomeByDate(int value) throws SQLException, ClassNotFoundException {
        return fineDao.getIncomeByDate(value);
    }

    @Override
    public HashMap getIncomeBYMonth() throws SQLException, ClassNotFoundException {
        return fineDao.getIncomeBYMonth();
    }
}
