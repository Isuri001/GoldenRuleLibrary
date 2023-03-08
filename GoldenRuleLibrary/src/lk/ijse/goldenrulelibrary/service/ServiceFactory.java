package lk.ijse.goldenrulelibrary.service;

import lk.ijse.goldenrulelibrary.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        if (serviceFactory==null)serviceFactory=new ServiceFactory();
        return serviceFactory;
    }

    public < T extends SuperService  >T getService(ServiceType serviceType){
        switch (serviceType){
            case BOOK:return (T)new BookServiceImpl();
            case FINE:return (T)new FineServiceImpl();
            case AUTHOR:return (T)new AuthorServiceImpl();
            case MEMBER:return (T)new MemberServiceImpl();
            case CATEGORY: return (T)new CategorySeviceImpl();
            case PRINTBILL:return (T)new PrintBillServiceImpl();
            case PUBLISHER:return (T)new PublisherServiceImpl();
            case EXPENDITURE:return (T)new ExpenditureServiceImpl();
            case BORROWEDBOOKRECORDS:return (T)new BorrowedBookRecordsServiceImpl();
        }
        return null;
    }
}
