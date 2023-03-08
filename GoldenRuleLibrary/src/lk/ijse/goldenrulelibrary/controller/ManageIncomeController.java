package lk.ijse.goldenrulelibrary.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.goldenrulelibrary.db.DBConnection;
import lk.ijse.goldenrulelibrary.service.custom.FineService;
import lk.ijse.goldenrulelibrary.service.custom.PrintBillService;
import lk.ijse.goldenrulelibrary.service.custom.impl.FineServiceImpl;
import lk.ijse.goldenrulelibrary.service.custom.impl.PrintBillServiceImpl;
import lk.ijse.goldenrulelibrary.to.IncomeTable;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;

public class ManageIncomeController {
    public TableView tblIncome;
    public TableColumn colDate;
    public TableColumn colIncome;
    public BarChart chart;
    public JFXComboBox cbSelectMonth;
    public JFXButton btnBack;
    public AnchorPane ManageIncomeContext;
    public JFXButton btnPrint;
    private double total;
    private FineService fineService =new FineServiceImpl();
    PrintBillService printBillService = new PrintBillServiceImpl();

    public void initialize(){
        total = 0;
        setTable(LocalDate.now().getMonthValue());
        setChart();
        setCbSelectMonth();
        cbSelectMonth.getSelectionModel().select(LocalDate.now().getMonthValue()-1);
    }

    public void setTable(int value){
        colDate.setCellValueFactory(new PropertyValueFactory<IncomeTable,String>("date"));
        colIncome.setCellValueFactory(new PropertyValueFactory<IncomeTable,String>("income"));


        try {
            ObservableList<IncomeTable> incomeByDate = fineService.getIncomeByDate(value);
            for (IncomeTable ob : incomeByDate){
                total=total+ob.getIncome();
            }
            tblIncome.setItems(incomeByDate);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void setChart(){
        try {
            HashMap hm = fineService.getIncomeBYMonth();
            XYChart.Series series = new XYChart.Series();
            series.setName(String.valueOf(LocalDate.now().getYear()));

            for(int i=1 ; i<13 ;i++){
                //System.out.println(i);
                if(hm.get(i)==null){
                    series.getData().add(new XYChart.Data<>(Month.of(i).toString(),0));
                }else{
                    series.getData().add(new XYChart.Data<>(Month.of(i).toString(),hm.get(i)));
                }
            }
            chart.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setCbSelectMonth(){
        String [] selectMonth={"January","February","March","April","May","June","July","August","September","October","November","December"};
        cbSelectMonth.setItems(FXCollections.observableArrayList(selectMonth));
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
       ManageIncomeContext.getChildren().clear();
        ManageIncomeContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml")));
    }

    public void cbSelectMonthOnAction(ActionEvent actionEvent) {
        if(cbSelectMonth.getSelectionModel().getSelectedItem()==null)return;
        setTable(cbSelectMonth.getSelectionModel().getSelectedIndex()+1);
    }

    public void printBill() throws JRException, SQLException, ClassNotFoundException {
        String billPath = "D:\\GoldenRuleLibrary\\GoldenRuleLibrary\\GoldenRuleLibrary\\src\\lk\\ijse\\goldenrulelibrary\\report\\MonthlyIncome.jrxml";
        String query = "SELECT (f.date) as date,Sum(f.cost) as income,Month(f.date) as month,Year(f.date) as Year from fine f " +
                "Group BY (date) having month = "+(cbSelectMonth.getSelectionModel().getSelectedIndex()+1)+" and year ="+LocalDate.now().getYear();
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("total",String.valueOf(total));
        String savePath = "D:\\GoldenRuleLibrary\\GoldenRuleLibrary\\GoldenRuleLibrary\\src\\lk\\ijse\\goldenrulelibrary\\incomereports\\income"+ LocalDate.now().toString()+ LocalTime.now().getHour()+LocalTime.now().getMinute()+".pdf";
        printBillService.printBill(query,billPath,hm,DBConnection.getInstance().getConnection(),savePath);

    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        try {
            printBill();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

