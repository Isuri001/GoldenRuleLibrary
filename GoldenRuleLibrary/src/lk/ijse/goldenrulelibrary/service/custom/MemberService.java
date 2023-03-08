package lk.ijse.goldenrulelibrary.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.service.SuperService;
import lk.ijse.goldenrulelibrary.to.Member;

import java.sql.SQLException;

public interface MemberService extends SuperService {
    String getMemberCount() throws  ClassNotFoundException, SQLException;
    ObservableList<Member> getAllMembers() throws SQLException, ClassNotFoundException;
    String getMemberId() throws SQLException, ClassNotFoundException;
    String getLastMemberId() throws SQLException, ClassNotFoundException;
    boolean addMember(Member temp) throws SQLException, ClassNotFoundException;
    boolean updateMember(Member member) throws SQLException, ClassNotFoundException;
    ObservableList<Member> searchMember(String searchBy, String value) throws SQLException, ClassNotFoundException;
    boolean deleteMember(String id) throws SQLException, ClassNotFoundException;

}
