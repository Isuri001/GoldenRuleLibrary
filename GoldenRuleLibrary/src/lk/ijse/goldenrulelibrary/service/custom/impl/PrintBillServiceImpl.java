package lk.ijse.goldenrulelibrary.service.custom.impl;

import lk.ijse.goldenrulelibrary.db.DBConnection;
import lk.ijse.goldenrulelibrary.service.custom.PrintBillService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class PrintBillServiceImpl implements PrintBillService {
    public void printBill(String query, String billPath, HashMap<String,Object> hm, Connection con,String savePath) throws JRException {
        JasperDesign jasdi = JRXmlLoader.load(billPath);
        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(query);
        jasdi.setQuery(newQuery);
        JasperReport jr = JasperCompileManager.compileReport(jasdi);
        JasperPrint print = JasperFillManager.fillReport(jr,hm, con);
        JasperExportManager.exportReportToPdfFile(print,savePath);
        JasperViewer view = new JasperViewer(print,false);
        view.show();
    }
}
