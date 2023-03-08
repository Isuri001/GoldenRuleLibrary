package lk.ijse.goldenrulelibrary.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.goldenrulelibrary.dao.custom.MemberDao;
import lk.ijse.goldenrulelibrary.dao.custom.impl.MemberDAOImpl;
import lk.ijse.goldenrulelibrary.service.custom.MemberService;
import lk.ijse.goldenrulelibrary.to.Member;

import java.sql.SQLException;

public class MemberServiceImpl implements MemberService {
    MemberDao memberDao =new MemberDAOImpl();
    @Override
    public String getMemberCount() throws ClassNotFoundException, SQLException {
        return memberDao.getMemberCount();
    }

    @Override
    public ObservableList<Member> getAllMembers() throws SQLException, ClassNotFoundException {
        return memberDao.getAllMembers();
    }

    @Override
    public String getMemberId() throws SQLException, ClassNotFoundException {
        return memberDao.getMemberId();
    }

    @Override
    public String getLastMemberId() throws SQLException, ClassNotFoundException {
        return memberDao.getLastMemberId();
    }

    @Override
    public boolean addMember(Member temp) throws SQLException, ClassNotFoundException {
        return memberDao.add(temp);
    }

    @Override
    public boolean updateMember(Member member) throws SQLException, ClassNotFoundException {
        return memberDao.update(member);
    }

    @Override
    public ObservableList<Member> searchMember(String searchBy, String value) throws SQLException, ClassNotFoundException {
        return memberDao.search(searchBy,value);
    }

    @Override
    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException {
        return memberDao.delete(id);
    }
}
