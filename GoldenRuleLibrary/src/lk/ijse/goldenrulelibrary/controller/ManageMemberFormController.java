package lk.ijse.goldenrulelibrary.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import lk.ijse.goldenrulelibrary.service.ServiceFactory;
import lk.ijse.goldenrulelibrary.service.ServiceType;
import lk.ijse.goldenrulelibrary.service.custom.MemberService;
import lk.ijse.goldenrulelibrary.service.custom.impl.MemberServiceImpl;
import lk.ijse.goldenrulelibrary.to.*;
import lk.ijse.goldenrulelibrary.util.Regex;
import lk.ijse.goldenrulelibrary.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageMemberFormController {


    public JFXButton btnAddMember;
    public JFXButton btnClear;
    public ToggleGroup btnGroup;
    public JFXButton btnUpdateMember;
    public JFXButton btnDeleteMember;
    public JFXButton btnClear2;
    public JFXButton btnBack;
    public AnchorPane ManageMembersContext;
    public JFXButton btnSearch;
    public Label lblMemberId;
    public JFXComboBox cbSelectType;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TableView ManageMemberContext;
    public TableColumn columnMemberId;
    public TableColumn columnName;
    public TableColumn columnaddress;
    public TableColumn columnType;
    public TableColumn columnContact;
    public TableView tblMember;
    public Label lblMemberId2;
    public JFXTextField txtName2;
    public JFXTextField txtAddress2;
    public JFXTextField txtContact2;
    public JFXComboBox cbSelectType2;
    public JFXTextField txtSearch;
    public RadioButton rdId;
    public RadioButton rdName;
    private MemberService memberService;

    public void initialize() {
        memberService = new MemberServiceImpl();
        setLabelMemberId();
        setM_TypeComboBox();
        setTblMember();

    }

    public void setTblMember() {
        columnMemberId.setCellValueFactory(new PropertyValueFactory<Member, String>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        columnaddress.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
        columnType.setCellValueFactory(new PropertyValueFactory<Member, String>("Type"));
        columnContact.setCellValueFactory(new PropertyValueFactory<Member, String>("Contact"));
        try {
            tblMember.setItems(memberService.getAllMembers());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void setLabelMemberId() {
        try {
            lblMemberId.setText(memberService.getMemberId());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting new Member Id Error - Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting New Member Id Error - Driver Error").show();

        }
    }

    public void setM_TypeComboBox() {
        String[] m_type = {"Child", "Teen", "Adult"};
        cbSelectType.setItems(FXCollections.observableArrayList(m_type));
        cbSelectType2.setItems(FXCollections.observableArrayList(m_type));
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        ManageMembersContext.getChildren().clear();
        ManageMembersContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml")));
    }


    public void search(String searchBy) throws SQLException, ClassNotFoundException {
        tblMember.setItems(memberService.searchMember(searchBy, txtSearch.getText()));
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (rdName.isSelected()) {
            search("m_Name");
        }
        if (rdId.isSelected()) {
            search("m_Id");
        }

    }

    public void btnAddMemberOnAction(ActionEvent actionEvent) {
        String id = lblMemberId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String type = cbSelectType.getSelectionModel().getSelectedItem().toString();


        if (name.length() < 5) {
            new Alert(Alert.AlertType.ERROR, "Member Added Fail - Please Enter name with characters more than 5").show();
            return;
        }

        Member temp = new Member(id, name, address, type, contact);

        try {
            boolean flag = memberService.addMember(temp);
            if (flag) {
                new Alert(Alert.AlertType.INFORMATION, "Member Added Successful").show();
                setLabelMemberId();
                txtName.clear();
                txtAddress.clear();
                txtContact.clear();
                setTblMember();

            } else {
                new Alert(Alert.AlertType.ERROR, "Member Added Fail").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void tblMemberClickOnAction(MouseEvent mouseEvent) {
        Member selectedMember = (Member) tblMember.getSelectionModel().getSelectedItem();
        if (selectedMember == null) {
            return;
        }
        lblMemberId2.setText(selectedMember.getId());
        txtName2.setText(selectedMember.getName());
        txtAddress2.setText(selectedMember.getAddress());
        txtContact2.setText(selectedMember.getContact());
        //cbSelectType2.setSelectionModel(cbSelectType2.getSelectionModel());
        ObservableList items = cbSelectType2.getItems();
        int count = 0;
        for (Object ob : items) {
            if ((ob.toString()).equals(selectedMember.getType())) {
                cbSelectType2.getSelectionModel().select(count);
                break;
            }
            count++;
        }
    }

    public void btnUpdateMemberOnAction(ActionEvent actionEvent) {
        String id = lblMemberId2.getText();
        String name = txtName2.getText();
        String address = txtAddress2.getText();
        String contact = txtContact2.getText();
        String type = cbSelectType2.getSelectionModel().getSelectedItem().toString();
        Member member = new Member(id, name, address, type, contact);

        try {
            boolean flag = memberService.updateMember(member);
            if (flag) {
                new Alert(Alert.AlertType.INFORMATION, "Update Success").show();
                setTblMember();
                setLabelMemberId();
                txtName2.clear();
                txtAddress2.clear();
                txtContact2.clear();
                cbSelectType2.getSelectionModel().clearSelection();


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtContactOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PHONE,txtContact);
    }

    public void txtNameOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);
    }

    public void btnDeleteMemberOnAction(ActionEvent actionEvent) {
        String id = lblMemberId2.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.WARNING, "Do You Want To Delete " +
                lblMemberId2.getText() + " From The System",ButtonType.YES,ButtonType.NO).showAndWait();
        if(buttonType.get().getText().toString().equalsIgnoreCase("No")){
            new Alert(Alert.AlertType.INFORMATION,"Not Deleted").show();
            return;
        }

        try {
            boolean b = memberService.deleteMember(id);
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Deleted Success").show();
                setTblMember();
                txtAddress2.clear();
                txtName2.clear();
                txtContact2.clear();
                cbSelectType2.getSelectionModel().clearSelection();
            }else {
                new Alert(Alert.AlertType.ERROR,"Member Not Found").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnClear2OnAction(ActionEvent actionEvent) {
    }
}

