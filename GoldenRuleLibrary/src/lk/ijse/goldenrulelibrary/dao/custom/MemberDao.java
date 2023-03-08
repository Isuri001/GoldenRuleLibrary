package lk.ijse.goldenrulelibrary.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.CrudDao;
import lk.ijse.goldenrulelibrary.to.Member;

import java.sql.SQLException;

public interface MemberDao extends CrudDao<Member,String> {
    String getMemberCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Member> getAllMembers() throws SQLException, ClassNotFoundException;
    String getMemberId() throws SQLException, ClassNotFoundException;
    String getLastMemberId() throws SQLException, ClassNotFoundException;
    ObservableList<Member> search(String searchBy,String value) throws SQLException, ClassNotFoundException;
}
