package lk.ijse.goldenrulelibrary.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.CategoryDao;
import lk.ijse.goldenrulelibrary.to.Catagory;
import lk.ijse.goldenrulelibrary.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAOImpl implements CategoryDao {
    @Override
    public boolean add(Catagory temp) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into category Values(?,?,?)", temp.getId(), temp.getCategory(), temp.getAge_group());

    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Catagory obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean search(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ObservableList<Catagory> search(String searchBy, String value) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from category where "+searchBy+" Like '%"+value+"%'");
        ObservableList<Catagory>list= FXCollections.observableArrayList();
        while(rs.next()){
            String id = rs.getString(1);
            String category = rs.getString(2);
            String age_group = rs.getString(3);
            list.add(new Catagory(id,category,age_group));

        }
        return list;
    }

    @Override
    public String getCatagoryCount() throws ClassNotFoundException, SQLException {
        ResultSet rs= CrudUtil.execute("SELECT count(C_Id) from category ");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<Catagory> getAllCatagory() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from category");
        ObservableList<Catagory>list= FXCollections.observableArrayList();
        while(rs.next()){
            String id = rs.getString(1);
            String category = rs.getString(2);
            String age_group = rs.getString(3);
            list.add(new Catagory(id,category,age_group));

        }
        return list;
    }

    @Override
    public String getCatagoryId() throws SQLException, ClassNotFoundException {
        String lastCatagoryId=getLastcatagoryId();
        if(lastCatagoryId==null){
            return "C-0001";
        }else{
            String[] split=lastCatagoryId.split("[C][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newCatagoryId=String.format("C-%04d", lastDigits);
            return newCatagoryId;
        }
    }

    @Override
    public String getLastcatagoryId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT C_Id from category order by C_Id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
