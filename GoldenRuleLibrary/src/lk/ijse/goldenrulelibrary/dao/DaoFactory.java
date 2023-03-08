package lk.ijse.goldenrulelibrary.dao;

import lk.ijse.goldenrulelibrary.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        if (daoFactory == null)daoFactory =new DaoFactory();
        return daoFactory;
    }

    public < T>T getDao(DaoType daoType){
        switch (daoType){
            case BORROWEDBOOKRECORDS:return (T)new Borrowed_book_recordsDAOImpl();
            case EXPENDITURE:return (T)new ExpenditureDAOImpl();
            case PUBLISHER:return (T)new PublisherDAOImpl();
            case CATEGORY: return (T)new CategoryDAOImpl();
            case MEMBER:return (T)new MemberDAOImpl();
            case AUTHOR:return (T)new AuthorDAOImpl();
            case FINE:return (T)new FineDAOImpl();
            case BOOK:return (T)new BookDAOImpl();
        }
        return null;
    }
}
