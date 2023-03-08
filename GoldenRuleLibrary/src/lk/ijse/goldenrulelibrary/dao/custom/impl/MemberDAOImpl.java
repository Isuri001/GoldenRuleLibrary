package lk.ijse.goldenrulelibrary.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.MemberDao;
import lk.ijse.goldenrulelibrary.to.Member;
import lk.ijse.goldenrulelibrary.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAOImpl implements MemberDao {
    @Override
    public boolean add(Member temp) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into member Values(?,?,?,?,?)", temp.getId(), temp.getName(), temp.getAddress(),temp.getType(),temp.getContact());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Delete from member where m_Id = ?",id);
    }

    @Override
    public boolean update(Member member) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update member set m_name = ? , address = ? , m_Type = ? ,contact = ? where " +
                "m_Id = ?",member.getName(),member.getAddress(),member.getType(),member.getContact(),member.getId());
    }

    @Override
    public boolean search(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ObservableList<Member> search(String searchBy,String value) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from member where "+searchBy+" Like '%"+value+"%'");
        ObservableList<Member>list= FXCollections.observableArrayList();
        while(rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String type = rs.getString(4);
            String contact = rs.getString(5);
            list.add(new Member(id,name,address,type,contact));

        }
        return  list;
    }

    @Override
    public String getMemberCount() throws ClassNotFoundException, SQLException {
        ResultSet rs= CrudUtil.execute("SELECT count(m_Id) from member");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<Member> getAllMembers() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from member");
        ObservableList<Member> list = FXCollections.observableArrayList();
        while(rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String type = rs.getString(4);
            String contact = rs.getString(5);
            list.add(new Member(id,name,address,type,contact));
        }
        return  list;
    }

    @Override
    public String getMemberId() throws SQLException, ClassNotFoundException {
        String lastMemberId=getLastMemberId();
        if(lastMemberId==null){
            return "M-0001";
        }else{
            String[] split=lastMemberId.split("[M][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newMemberId=String.format("M-%04d", lastDigits);
            return newMemberId;
        }
    }

    @Override
    public String getLastMemberId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT m_Id from member order by m_Id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
