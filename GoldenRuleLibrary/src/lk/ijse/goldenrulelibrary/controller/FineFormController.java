package lk.ijse.goldenrulelibrary.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import lk.ijse.goldenrulelibrary.db.DBConnection;
import lk.ijse.goldenrulelibrary.service.ServiceFactory;
import lk.ijse.goldenrulelibrary.service.ServiceType;
import lk.ijse.goldenrulelibrary.service.custom.FineService;
import lk.ijse.goldenrulelibrary.service.custom.PrintBillService;
import lk.ijse.goldenrulelibrary.service.custom.impl.FineServiceImpl;
import lk.ijse.goldenrulelibrary.service.custom.impl.PrintBillServiceImpl;
import lk.ijse.goldenrulelibrary.to.Fine;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class FineFormController {
    public Label lblFine1;
    public Label lblDays;
    public JFXTextField txtFine;
    public JFXButton btnCalculate;
    public JFXButton btnAdd;
    public Label lblFineId;
    public Label bbrId;
    public Label lblTotalFine;
    private boolean flag = false;
    private FineService fineService =new FineServiceImpl();
    PrintBillService printBillService = new PrintBillServiceImpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        fineService = ServiceFactory.getInstance().getService(ServiceType.FINE);
        flag = false;
        lblFineId.setText(fineService.getFineId());
    }

    public void btnCalculateOnAction(ActionEvent actionEvent) {
        lblTotalFine.setText(String.valueOf(Integer.parseInt(lblDays.getText())*Double.parseDouble(txtFine.getText())));
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, JRException {
        flag = fineService.addFine(new Fine(lblFineId.getText(), bbrId.getText(), Double.parseDouble(lblTotalFine.getText())));
        if(flag){
            printBill();
            btnAdd.setDisable(true);
        }
    }

    public void setDayCount(String count,String bbrId){
        lblDays.setText(count);
        this.bbrId.setText(bbrId);
    }

    public boolean isAccepted(){
        return flag;
    }


    public void printBill() throws JRException, SQLException, ClassNotFoundException {
        String billPath = "D:\\GoldenRuleLibrary\\GoldenRuleLibrary\\" +
                "GoldenRuleLibrary\\src\\lk\\ijse\\goldenrulelibrary\\report\\LibararyFine.jrxml";

        String query = "select f.f_id,b.B_Name,bbr.issue_Date,bbr.return_date,DATEDIFF(NOW(),bbr.return_date) " +
                "as late,f.cost from fine f inner join Borrowed_Book_Records bbr on f.Borrowed_Book_Records_id " +
                "= bbr.Borrowed_Book_Records_Id inner join book b on bbr.Book_Id = b.Book_Id where f.f_id = '"+
                lblFineId.getText()+"'";
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("Fine",txtFine.getText());


        String savePath = "D:\\GoldenRuleLibrary\\GoldenRuleLibrary\\GoldenRuleLibrary\\src\\lk\\ijse\\golde" +
                "nrulelibrary\\bills\\"+ LocalDate.now().toString()+LocalTime.now().getHour()+LocalTime.now().getMinute()+".pdf";

        printBillService.printBill(query,billPath,hm,DBConnection.getInstance().getConnection(), savePath );

    }
}
