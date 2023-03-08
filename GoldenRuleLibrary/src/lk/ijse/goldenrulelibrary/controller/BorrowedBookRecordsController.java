package lk.ijse.goldenrulelibrary.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lk.ijse.goldenrulelibrary.service.ServiceFactory;
import lk.ijse.goldenrulelibrary.service.ServiceType;
import lk.ijse.goldenrulelibrary.service.custom.BookService;
import lk.ijse.goldenrulelibrary.service.custom.BorrowedBookRecordsService;
import lk.ijse.goldenrulelibrary.service.custom.MemberService;
import lk.ijse.goldenrulelibrary.service.custom.impl.BookServiceImpl;
import lk.ijse.goldenrulelibrary.service.custom.impl.BorrowedBookRecordsServiceImpl;
import lk.ijse.goldenrulelibrary.service.custom.impl.MemberServiceImpl;
import lk.ijse.goldenrulelibrary.to.*;

import java.io.IOException;
import java.sql.SQLException;

public class BorrowedBookRecordsController {
    public AnchorPane BorrowedBookRecordsContext;
    public JFXButton btnAddRecords;
    public JFXButton btnClear;
    public ToggleGroup btnGroup;
    public JFXButton btnDeleteRecords;
    public JFXButton btnBack;
    public JFXButton btnSearch;
    public JFXComboBox cbStatusComboBox;
    public JFXDatePicker datePicker2;
    public JFXComboBox cbBookComboBox;
    public JFXComboBox cbMemberComboBox;
    public Label lblBorrowedBookRecordId;
    public JFXDatePicker datePicker3;
    public TableView tblBorrowedBookRecords;
    public TableColumn columnMId;
    public TableColumn columnBorrowedBookRecordsId;
    public TableColumn columnBookId;
    public TableColumn columnIssueDate;
    public TableColumn ColumnReturnDate;
    public TableColumn columnStatus;
    public RadioButton rdBookId;
    public JFXTextField txtSearch;
    public RadioButton rdMemberId;
    public JFXButton btnUpdateStatus;
    public Label lblBorrowed_Book_Records_Id;
    private BorrowedBookRecordsService borrowedBookRecordsService;
    private BookService bookService;
    private MemberService memberService;

    public void initialize() {
        borrowedBookRecordsService = ServiceFactory.getInstance().getService(ServiceType.BORROWEDBOOKRECORDS);
        bookService = ServiceFactory.getInstance().getService(ServiceType.BOOK);
        memberService =ServiceFactory.getInstance().getService(ServiceType.MEMBER);
        setLabelBorrowedBookRecordsId();
        setMemberComboBox();
        setBookComboBox();
        setCbStatusComboBox();
        setBorrowedBookRecordsTable();
    }


    public void setBorrowedBookRecordsTable() {
        columnBorrowedBookRecordsId.setCellValueFactory(new PropertyValueFactory<BorrowedBookRecords, String>("borrowed_book_records_id"));
        columnMId.setCellValueFactory(new PropertyValueFactory<BorrowedBookRecords, String>("m_id"));
        columnBookId.setCellValueFactory(new PropertyValueFactory<BorrowedBookRecords, String>("book_id"));
        columnIssueDate.setCellValueFactory(new PropertyValueFactory<BorrowedBookRecords, String>("issue_date"));
        ColumnReturnDate.setCellValueFactory(new PropertyValueFactory<BorrowedBookRecords, String>("return_date"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<BorrowedBookRecords, String>("status"));

        try {
            tblBorrowedBookRecords.setItems(borrowedBookRecordsService.getAllBorrowedBookRecords());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void setLabelBorrowedBookRecordsId() {
        try {
            lblBorrowedBookRecordId.setText(borrowedBookRecordsService.getBorrowedBookRecordsId());
            //System.out.println(BookController.getBookId());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting New Borrowed_Book_Records_Id Error - Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting New Borrowed_Book_Records_Id Error - Driver Error").show();
        }
    }


    public void setCbStatusComboBox() {
        String[] status = {"Away", "Returned"};
        cbStatusComboBox.setItems(FXCollections.observableArrayList(status));
    }

    public void setMemberComboBox() {
        cbMemberComboBox.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                return ((Member) object).getId();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        try {
            ObservableList<Member> allMembers = memberService.getAllMembers();
            cbMemberComboBox.setItems(allMembers);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting All Members  Error - Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting All Members Error - Driver Error").show();
        }
    }


    public void setBookComboBox() {
        cbBookComboBox.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                return ((Book) object).getBook_Id();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        try {
            ObservableList<Book> allBooks = bookService.getAllBooks();
            cbBookComboBox.setItems(allBooks);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting All Books  Error - Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting All Books Error - Driver Error").show();
        }
    }

    public void btnAddRecordsOnAction(ActionEvent actionEvent) {

        Member ob = (Member) cbMemberComboBox.getSelectionModel().getSelectedItem();
        //System.out.println(ob.toString());

        Book ob1 = (Book) cbBookComboBox.getSelectionModel().getSelectedItem();
        //System.out.println(ob1.toString());


        BorrowedBookRecords borrowedBookRecords = new BorrowedBookRecords(lblBorrowedBookRecordId.getText(),
                ob.getId(), ob1.getBook_Id(), String.valueOf(datePicker2.getValue()),
                String.valueOf(datePicker3.getValue()), cbStatusComboBox.getSelectionModel().
                getSelectedItem().toString());

        try {
            boolean flag =borrowedBookRecordsService.addBorrowedBookRecords(borrowedBookRecords);
            if (flag) {
                new Alert(Alert.AlertType.INFORMATION, "Borrowed_Book_Records Added Success").show();
                setLabelBorrowedBookRecordsId();
                setBorrowedBookRecordsTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Borrowed_Book_Records Added Fail").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void search(String searchBy) throws SQLException, ClassNotFoundException {
        tblBorrowedBookRecords.setItems(borrowedBookRecordsService.searchBorrowedBookRecords(searchBy,txtSearch.getText()));
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(rdBookId.isSelected()){
            search("Book_Id");
        }
        if(rdMemberId.isSelected()){
            search("m_Id");
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        BorrowedBookRecordsContext.getChildren().clear();
        BorrowedBookRecordsContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml")));
    }

    public void btnUpdateStatusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String borrowed_book_records_id = lblBorrowed_Book_Records_Id.getText();
        //String status=cbStatusComboBox.getSelectionModel().getSelectedItem().toString();
        ObservableList<BorrowedBookRecords> borrowed_book_id = borrowedBookRecordsService.searchBorrowedBookRecords("Borrowed_Book_Records_ID", borrowed_book_records_id);
        BorrowedBookRecords ob = borrowed_book_id.get(0);
        String dateDifference = borrowedBookRecordsService.getDateDifference(ob.getBorrowed_book_records_id());

        if(dateDifference==null){
            new Alert(Alert.AlertType.ERROR,"Record Not Found").show();
            return;
        }
        int i = Integer.parseInt(dateDifference);
        if(i>0){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FineForm.fxml"));
            Parent load = loader.load();
            FineFormController controller = loader.getController();
            controller.setDayCount(dateDifference,ob.getBorrowed_book_records_id());
            stage.setScene(new Scene(load));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnAddRecords.getScene().getWindow());
            stage.showAndWait();
            if(!controller.isAccepted()){
                new Alert(Alert.AlertType.ERROR,"Return Record Not Added").show();
                return;
            }
            ob.setStatus("Returned");
            try {
                boolean flag =borrowedBookRecordsService.updateStatus(ob);
                if(flag){
                    new Alert(Alert.AlertType.INFORMATION,"Records Added Success").show();
                    setLabelBorrowedBookRecordsId();
                    setBorrowedBookRecordsTable();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            ob.setStatus("Returned");
            try {
                boolean flag =borrowedBookRecordsService.updateStatus(ob);

                if(flag){
                    new Alert(Alert.AlertType.INFORMATION,"Records Added Success").show();
                    setLabelBorrowedBookRecordsId();
                    setBorrowedBookRecordsTable();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void tblBorrowedBookRecordMouseOnAction(MouseEvent mouseEvent) {
        if(tblBorrowedBookRecords.getSelectionModel().getSelectedIndex()==-1)return;

        lblBorrowed_Book_Records_Id.setText(((BorrowedBookRecords)(tblBorrowedBookRecords.getSelectionModel().getSelectedItem())).getBorrowed_book_records_id());
    }
}

