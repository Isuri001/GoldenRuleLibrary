package lk.ijse.goldenrulelibrary.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.goldenrulelibrary.service.ServiceFactory;
import lk.ijse.goldenrulelibrary.service.ServiceType;
import lk.ijse.goldenrulelibrary.service.custom.AuthorService;
import lk.ijse.goldenrulelibrary.service.custom.impl.AuthorServiceImpl;
import lk.ijse.goldenrulelibrary.to.Author;

import java.io.IOException;
import java.sql.SQLException;

public class ManageAuthorFormController {
    public AnchorPane ManageAuthorContext;
    public JFXButton btnAddAuthor;
    public ToggleGroup btnGroup;
    public JFXButton btnClear;
    public JFXButton btnBack;
    public JFXButton btnSearch;
    public Label lblAuthorId;
    public JFXTextField txtAuthorName;
    public JFXTextField txtAuthorSubject;
    public TableView tblAuthor;
    public TableColumn columnId;
    public TableColumn columnName;
    public TableColumn columnQualification;
    public TableColumn columnSubject;
    public JFXTextField txtAuthorQualification;
    public JFXTextField txtSearch;
    public RadioButton rdId;
    public RadioButton rdName;
    private AuthorService authorService;

    public void initialize(){
        authorService = ServiceFactory.getInstance().getService(ServiceType.AUTHOR);
        setAuthorIdLabel();
        setAuthorTable();
    }

    public void setAuthorTable() {
        columnId.setCellValueFactory(new PropertyValueFactory<Author, String>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Author, String>("name"));
        columnSubject.setCellValueFactory(new PropertyValueFactory<Author, String>("subject"));
        columnQualification.setCellValueFactory(new PropertyValueFactory<Author, String>("qualification"));
        try {
            tblAuthor.setItems(authorService.getAllAuthors());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        ManageAuthorContext.getChildren().clear();
        ManageAuthorContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml")));
    }

    public void setAuthorIdLabel(){
        try {
            lblAuthorId.setText(authorService.getAuthorId());
            System.out.println(lblAuthorId.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void search(String searchBy) throws SQLException, ClassNotFoundException {
        tblAuthor.setItems(authorService.searchAuthor(searchBy,txtSearch.getText()));
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(rdName.isSelected()){
            search("Au_Name");
        }
        if(rdId.isSelected()){
            search("Au_Id");
        }

    }

    public void btnAddAuthorOnAction(ActionEvent actionEvent) {
        String id = lblAuthorId.getText();
        String name = txtAuthorName.getText();
        String subject = txtAuthorSubject.getText();
        String qualification = txtAuthorQualification.getText();

        if(name.length()<5){
            new Alert(Alert.AlertType.ERROR,"Author Added Fail - Please Enter name with characters more than 5").show();
            return;
        }

        Author temp = new Author(id,name,subject,qualification);

        try {
            boolean flag = authorService.addAuthor(temp);
            if(flag){
                new Alert(Alert.AlertType.INFORMATION,"Author Added Successful").show();
                setAuthorIdLabel();
                txtAuthorName.clear();
                txtAuthorSubject.clear();
                txtAuthorQualification.clear();
                setAuthorTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Author Added Fail").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
