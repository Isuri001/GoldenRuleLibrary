package lk.ijse.goldenrulelibrary.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.AuthorDao;
import lk.ijse.goldenrulelibrary.to.Author;
import lk.ijse.goldenrulelibrary.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDAOImpl implements AuthorDao {

    @Override
    public boolean add(Author temp) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into author Values(?,?,?,?)", temp.getId(), temp.getName(), temp.getSubject(),temp.getQualification());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Author obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean search(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ObservableList search(String searchBy,String value) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from author where "+searchBy+" Like '%"+value+"%'");
        ObservableList<Author>list= FXCollections.observableArrayList();
        while(rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            String subject = rs.getString(3);
            String qualification = rs.getString(4);
            list.add(new Author(id,name,subject,qualification));

        }
        return list;
    }

    @Override
    public String getAuthorCount() throws ClassNotFoundException, SQLException {
        ResultSet rs= CrudUtil.execute("SELECT count(Au_Id) from author");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<Author> getAllAuthors() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from author");
        ObservableList<Author> list = FXCollections.observableArrayList();
        while(rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            String subject = rs.getString(3);
            String qualification = rs.getString(4);
            list.add(new Author(id,name,subject,qualification));
        }
        return  list;
    }

    @Override
    public String getAuthorId() throws SQLException, ClassNotFoundException {
        String lastAuthorId=getLastAuthorId();
        if(lastAuthorId==null){
            return "A-0001";
        }else{
            String[] split=lastAuthorId.split("[A][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newAuthorId=String.format("A-%04d", lastDigits);
            return newAuthorId;
        }
    }

    @Override
    public String getLastAuthorId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Au_Id from author order by Au_Id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
