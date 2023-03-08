package lk.ijse.goldenrulelibrary.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.BookDao;
import lk.ijse.goldenrulelibrary.to.Book;
import lk.ijse.goldenrulelibrary.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAOImpl implements BookDao {

    @Override
    public boolean add(Book temp) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into book Values(?,?,?,?,?,?)", temp.getBook_Id(), temp.getPub_Id(), temp.getAu_Id(),temp.getC_Id(),temp.getB_Name(),temp.getEdition());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Delete from book where Book_Id = ?",id);
    }

    @Override
    public boolean update(Book book) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update book set  Pub_Id = ? , Au_Id = ? ,C_Id = ?,B_Name = ?,Edition = ?   where " +
                "Book_Id = ?",book.getPub_Id(),book.getAu_Id(),book.getC_Id(),book.getB_Name(),book.getEdition(),book.getBook_Id());
    }

    @Override
    public boolean search(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ObservableList<Book> search(String searchBy, String value) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from book where "+searchBy+" Like '%"+value+"%'");
        ObservableList<Book>list= FXCollections.observableArrayList();
        while(rs.next()){
            String book_id = rs.getString(1);
            String pub_id = rs.getString(2);
            String au_id = rs.getString(3);
            String c_id = rs.getString(4);
            String b_name = rs.getString(5);
            String edition = rs.getString(6);
            list.add(new Book(book_id,pub_id,au_id,c_id,b_name,edition));

        }
        return list;
    }

    @Override
    public String getBookCount() throws ClassNotFoundException, SQLException {
        ResultSet rs= CrudUtil.execute("SELECT count(Book_Id) from book");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<Book> getAllBooks() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from book");
        ObservableList<Book>list= FXCollections.observableArrayList();
        while(rs.next()){
            String book_id = rs.getString(1);
            String pub_id = rs.getString(2);
            String au_id = rs.getString(3);
            String c_id = rs.getString(4);
            String b_name = rs.getString(5);
            String edition = rs.getString(6);
            list.add(new Book(book_id,pub_id,au_id,c_id,b_name,edition));

        }
        return list;
    }

    @Override
    public String getBookId() throws SQLException, ClassNotFoundException {
        String lastBookId=getLastBookId();
        if(lastBookId==null){
            return "B-0001";
        }else{
            String[] split=lastBookId.split("[B][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newBookId=String.format("B-%04d", lastDigits);
            return newBookId;
        }
    }

    @Override
    public String getLastBookId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Book_Id from book order by Book_Id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
