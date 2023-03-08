package lk.ijse.goldenrulelibrary.service.custom;

import lk.ijse.goldenrulelibrary.service.SuperService;
import net.sf.jasperreports.engine.JRException;

import java.sql.Connection;
import java.util.HashMap;

public interface PrintBillService extends SuperService {
    void printBill(String query, String billPath, HashMap<String,Object> hm, Connection con, String savePath) throws JRException;
}
