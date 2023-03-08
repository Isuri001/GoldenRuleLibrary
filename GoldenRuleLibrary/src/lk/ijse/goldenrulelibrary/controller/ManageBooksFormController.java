package lk.ijse.goldenrulelibrary.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import lk.ijse.goldenrulelibrary.service.ServiceFactory;
import lk.ijse.goldenrulelibrary.service.ServiceType;
import lk.ijse.goldenrulelibrary.service.custom.AuthorService;
import lk.ijse.goldenrulelibrary.service.custom.BookService;
import lk.ijse.goldenrulelibrary.service.custom.CategoryService;
import lk.ijse.goldenrulelibrary.service.custom.PublisherService;
import lk.ijse.goldenrulelibrary.service.custom.impl.AuthorServiceImpl;
import lk.ijse.goldenrulelibrary.service.custom.impl.BookServiceImpl;
import lk.ijse.goldenrulelibrary.service.custom.impl.CategorySeviceImpl;
import lk.ijse.goldenrulelibrary.service.custom.impl.PublisherServiceImpl;
import lk.ijse.goldenrulelibrary.to.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ManageBooksFormController {
    public AnchorPane ManageBookFormContext;
    public JFXButton btnAddBook;
    public JFXButton btnClear;
    public TableView ManageBookContext;
    public ToggleGroup btnGroup;
    public JFXButton btnUpdateBook;
    public JFXButton btnDeleteBook;
    public JFXButton btnClear2;
    public JFXButton btnBack;
    public JFXButton btnSearch;
    public Label lblBookId;
    public JFXComboBox cbPublisher;
    public JFXComboBox cbAuthor;
    public JFXComboBox cbCatagory;
    public JFXTextField txtName;
    public JFXTextField txtEdition;
    public TableView tblBookDetails;
    public TableColumn colBookId;
    public TableColumn colBookName;
    public TableColumn colBookEdition;
    public TableColumn colPublisherId;
    public TableColumn colAuthorId;
    public Label lblBookId2;
    public JFXTextField txtBookName2;
    public JFXTextField txtBookEdition2;
    public JFXTextField txtPublisherId2;
    public JFXTextField txtAuthorId2;
    public TableColumn colCatagoryId;
    public JFXComboBox cbPublisher2;
    public JFXComboBox cbAuthor2;
    public JFXComboBox cbCatagory2;
    public JFXTextField txtSearch;
    public RadioButton rdId;
    public RadioButton rdName;
    public RadioButton rdCatagory;
    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;
    private PublisherService publisherService;


    public void initialize() {
        bookService = ServiceFactory.getInstance().getService(ServiceType.BOOK);
        authorService = ServiceFactory.getInstance().getService(ServiceType.AUTHOR);
        categoryService = ServiceFactory.getInstance().getService(ServiceType.CATEGORY);
        publisherService =new PublisherServiceImpl();
        setLabelBookId();
        setPublisherComboBox();
        setAuthorComboBox();
        setCatagoryComboBox();
        setCbCatagory2ComboBox();
        setCbAuthor2ComboBox();
        setCbPublisher2ComboBox();
        setTblBookDetails();


    }
    public void setTblBookDetails(){
        colBookId.setCellValueFactory(new PropertyValueFactory<Book,String>("book_Id"));
        colBookName.setCellValueFactory(new PropertyValueFactory<Book,String>("b_Name"));
        colBookEdition.setCellValueFactory(new PropertyValueFactory<Book,String>("edition"));
        colPublisherId.setCellValueFactory(new PropertyValueFactory<Book,String>("pub_Id"));
        colAuthorId.setCellValueFactory(new PropertyValueFactory<Book,String>("au_Id"));
        colCatagoryId.setCellValueFactory(new PropertyValueFactory<Book,String>("c_Id"));

        try {
            tblBookDetails.setItems(bookService.getAllBooks());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void search(String searchBy) throws SQLException, ClassNotFoundException {
        tblBookDetails.setItems(bookService.searchBook(searchBy,txtSearch.getText()));
    }

    public void setLabelBookId() {
        try {
            lblBookId.setText(bookService.getBookId());
            //System.out.println(BookController.getBookId());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting new Book Id Error - Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting New Book Id Error - Driver Error").show();
        }
    }



    public void setAuthorComboBox(){
        try {
            cbAuthor.setItems(authorService.getAllAuthors());

            cbAuthor.setConverter(new StringConverter() {
                @Override
                public String toString(Object object) {
                    return ((Author)object).getId();
                }

                @Override
                public Object fromString(String string) {
                    return null;
                }
            });
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting New Author Id Error -Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting New Author Id Error - Driver Error").show();
        }
    }

    public void setCatagoryComboBox(){
        try {
            cbCatagory.setItems((categoryService.getAllCatagory()));

            cbCatagory.setConverter(new StringConverter() {
                @Override
                public String toString(Object object) {
                    return ((Catagory)object).getId();
                }

                @Override
                public Object fromString(String string) {
                    return null;
                }
            });
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting New Catagory Id Error -Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting All Catagory Id  Error - Driver Error").show();
        }
    }

    public void setPublisherComboBox(){
        cbPublisher.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                return ((Publisher)object).getId();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        try {
            ObservableList<Publisher> allPublishers = publisherService.getAllPublishers();
            cbPublisher.setItems(allPublishers);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting All Publishers  Error - Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting All Publishers Error - Driver Error").show();
        }
    }

    public void setCbPublisher2ComboBox(){
        try {
            cbPublisher2.setItems((publisherService.getAllPublishers()));

            cbPublisher2.setConverter(new StringConverter() {
                @Override
                public String toString(Object object) {
                    return ((Publisher)object).getId();
                }

                @Override
                public Object fromString(String string) {
                    return null;
                }
            });
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting New Publisher Id Error -Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting All Publisher Id  Error - Driver Error").show();
        }
    }

    public void setCbAuthor2ComboBox(){
        try {
            cbAuthor2.setItems((authorService.getAllAuthors()));

            cbAuthor2.setConverter(new StringConverter() {
                @Override
                public String toString(Object object) {
                    return ((Author)object).getId();
                }

                @Override
                public Object fromString(String string) {
                    return null;
                }
            });
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting New Author Id Error -Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting All Author Id  Error - Driver Error").show();
        }
    }

    public void setCbCatagory2ComboBox(){
        try {
            cbCatagory2.setItems((categoryService.getAllCatagory()));

            cbCatagory2.setConverter(new StringConverter() {
                @Override
                public String toString(Object object) {
                    return ((Catagory)object).getId();
                }

                @Override
                public Object fromString(String string) {
                    return null;
                }
            });
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Getting New Catagory Id Error -Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Getting All Catagory Id  Error - Driver Error").show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        ManageBookFormContext.getChildren().clear();
        ManageBookFormContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml")));
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(rdName.isSelected()){
            search("B_Name");
        }
        if(rdId.isSelected()){
            search("Book_Id");
        }
        if(rdCatagory.isSelected()){
            search("C_Id");
        }
    }



    public void btnAddBookOnAction(ActionEvent actionEvent) {
        Publisher ob = (Publisher) cbPublisher.getSelectionModel().getSelectedItem();
        System.out.println(ob.toString());

        Author ob1=(Author) cbAuthor.getSelectionModel().getSelectedItem();
        System.out.println(ob1.toString());

        Catagory ob2=(Catagory) cbCatagory.getSelectionModel().getSelectedItem();
        System.out.println(ob2.toString());

        Book book = new Book(lblBookId.getText(),ob.getId(),ob1.getId(),ob2.getId(),txtName.getText(),txtEdition.getText());

        try {
            boolean flag = bookService.addBook(book);
            if(flag){
                new Alert(Alert.AlertType.INFORMATION,"Book Added Success").show();
                setLabelBookId();
                txtEdition.clear();
                txtName.clear();
                setTblBookDetails();

        }else{
            new Alert(Alert.AlertType.ERROR,"Book Added Fail").show();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    public void tblBookClickOnAction(MouseEvent mouseEvent) {
        Book selectedBook = (Book) tblBookDetails.getSelectionModel().getSelectedItem();
        if(selectedBook==null){
            return;
        }
        lblBookId2.setText(selectedBook.getBook_Id());
        txtBookName2.setText(selectedBook.getB_Name());
        txtBookEdition2.setText(selectedBook.getEdition());
        //Author selectedItem =(Author) cbAuthor2.getSelectionModel().getSelectedItem();
        ObservableList items = cbAuthor2.getItems();
        int count = 0;
        for(Object ob : items){
            if(((Author)ob).getId().equals(selectedBook.getAu_Id())){
                cbAuthor2.getSelectionModel().select(count);
                break;
            }
            count++;
        }
        count = 0;

        items = cbPublisher2.getItems();
        for(Object ob : items){
            if(((Publisher)ob).getId().equals(selectedBook.getPub_Id())){
                cbPublisher2.getSelectionModel().select(count);
                break;
            }
            count++;
        }

        count = 0;
        items = cbCatagory2.getItems();
        for(Object ob : items){
            if(((Catagory)ob).getId().equals(selectedBook.getC_Id())){
                cbCatagory2.getSelectionModel().select(count);
                break;
            }
            count++;
        }
    }



    public void btnUpdateBookOnAction(ActionEvent actionEvent) {
        String id = lblBookId2.getText();
        String pub_id = ((Publisher)cbPublisher2.getSelectionModel().getSelectedItem()).getId();
        String au_id = ((Author)cbAuthor2.getSelectionModel().getSelectedItem()).getId();
        String c_id = ((Catagory)cbCatagory2.getSelectionModel().getSelectedItem()).getId();
        String b_name = txtBookName2.getText();
        String edition= txtBookEdition2.getText();
        Book  book = new Book(id,pub_id,au_id,c_id,b_name,edition);

        try {
            boolean flag = bookService.updateBook(book);
            if(flag){
                new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
                setTblBookDetails();
                setLabelBookId();
                txtBookName2.clear();
                txtBookEdition2.clear();
                cbCatagory2.getSelectionModel().clearSelection();
                cbAuthor2.getSelectionModel().clearSelection();
                cbPublisher2.getSelectionModel().clearSelection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteBookOnAction(ActionEvent actionEvent) {
        String id = lblBookId2.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.WARNING, "Do You Want To Delete " +
                lblBookId2.getText() + " From The System",ButtonType.YES,ButtonType.NO).showAndWait();
        if(buttonType.get().getText().toString().equalsIgnoreCase("No")){
            new Alert(Alert.AlertType.INFORMATION,"Not Deleted").show();
            return;
        }

        try {
            boolean b = bookService.deleteBook(id);
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Deleted Success").show();
                setTblBookDetails();
                txtBookEdition2.clear();
                txtBookName2.clear();
                cbCatagory2.getSelectionModel().clearSelection();
                cbAuthor2.getSelectionModel().clearSelection();
                cbPublisher2.getSelectionModel().clearSelection();
            }else {
                new Alert(Alert.AlertType.ERROR,"Book Not Found").show();
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
