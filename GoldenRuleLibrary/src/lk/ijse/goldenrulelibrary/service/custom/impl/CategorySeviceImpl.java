package lk.ijse.goldenrulelibrary.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.CategoryDao;
import lk.ijse.goldenrulelibrary.dao.custom.impl.CategoryDAOImpl;
import lk.ijse.goldenrulelibrary.service.custom.CategoryService;
import lk.ijse.goldenrulelibrary.to.Catagory;

import java.sql.SQLException;

public class CategorySeviceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDAOImpl();
    @Override
    public String getCatagoryCount() throws ClassNotFoundException, SQLException {
        return categoryDao.getCatagoryCount();
    }

    @Override
    public ObservableList<Catagory> getAllCatagory() throws SQLException, ClassNotFoundException {
        return categoryDao.getAllCatagory();
    }

    @Override
    public String getCatagoryId() throws SQLException, ClassNotFoundException {
        return categoryDao.getCatagoryId();
    }

    @Override
    public String getLastcatagoryId() throws SQLException, ClassNotFoundException {
        return categoryDao.getLastcatagoryId();
    }

    @Override
    public boolean addCatagory(Catagory temp) throws SQLException, ClassNotFoundException {
        return categoryDao.add(temp);
    }

    @Override
    public ObservableList<Catagory> searchCatagory(String searchBy, String value) throws SQLException, ClassNotFoundException {
        return categoryDao.search(searchBy,value);
    }
}
