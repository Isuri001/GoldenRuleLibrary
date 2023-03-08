package lk.ijse.goldenrulelibrary.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.goldenrulelibrary.service.ServiceFactory;
import lk.ijse.goldenrulelibrary.service.ServiceType;
import lk.ijse.goldenrulelibrary.service.custom.ExpenditureService;
import lk.ijse.goldenrulelibrary.service.custom.impl.ExpenditureServiceImpl;
import lk.ijse.goldenrulelibrary.to.Expenditure;

import java.io.IOException;
import java.sql.SQLException;

public class ManageExpenditureController {
    public AnchorPane ManageExpenditureContext;
    public JFXButton btnAddExpenditure;
    public JFXButton btnClear;
    public ToggleGroup btnGroup;
    public JFXButton btnBack;
    public JFXButton btnSearch;
    public JFXDatePicker datePicker1;
    public JFXTextField txtAmount;
    public JFXTextField txtInvoiceNo;
    public Label lblTrasId;
    public TableColumn columnTrasId;
    public TableColumn columnDate;
    public TableColumn columnAmount;
    public TableColumn columnInvoiceNo;
    public TableView tblExpenditure;
    public JFXTextField txtSearch;
    public RadioButton rdTrasId;
    private ExpenditureService expenditureService;

    public void initialize() {
        expenditureService = ServiceFactory.getInstance().getService(ServiceType.EXPENDITURE);
        setExpenditureIdLabel();
        setTblExpenditure();

    }

    public void setTblExpenditure() {
        columnDate.setCellValueFactory(new PropertyValueFactory<Expenditure, String>("date"));
        columnTrasId.setCellValueFactory(new PropertyValueFactory<Expenditure, String>("tras_id"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<Expenditure, String>("amount"));
        columnInvoiceNo.setCellValueFactory(new PropertyValueFactory<Expenditure, String>("invoice_no"));
        try {
            tblExpenditure.setItems(expenditureService.getAllExpenditure());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setExpenditureIdLabel(){
        try {
            lblTrasId.setText(expenditureService.getTrasId());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting new Tras Id Error - Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        ManageExpenditureContext.getChildren().clear();
        ManageExpenditureContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml")));
    }


    public void search(String searchBy) throws SQLException, ClassNotFoundException {
        tblExpenditure.setItems(expenditureService.searchExpenditure(searchBy,txtSearch.getText()));
    }


    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(rdTrasId.isSelected()){
            search("Tras_Id");
        }
    }


    public void btnAddExpenditureOnAction(ActionEvent actionEvent) {
        String date = String.valueOf(datePicker1.getValue());
        String id=lblTrasId.getText();
        String amount=txtAmount.getText();
        String invoice_no=txtInvoiceNo.getText();



        Expenditure temp = new Expenditure(date,id,amount,invoice_no);

        try {
            boolean flag = expenditureService.addExpenditure(temp);
            if(flag){
                new Alert(Alert.AlertType.INFORMATION,"Expenditure Added Successful").show();
                setExpenditureIdLabel();
                txtAmount.clear();
                txtInvoiceNo.clear();
                setTblExpenditure();



            }else{
                new Alert(Alert.AlertType.ERROR,"Expenditure Added Fail").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
