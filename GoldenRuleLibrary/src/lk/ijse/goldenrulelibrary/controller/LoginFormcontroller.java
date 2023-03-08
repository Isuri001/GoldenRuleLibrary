package lk.ijse.goldenrulelibrary.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormcontroller {
    public AnchorPane LoginFormContext;
    public Button btnLogin;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public Label lblWrong;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnLogin) {
            String username = txtUserName.getText();
            String password = txtPassword.getText();
            if (username.equalsIgnoreCase("L") && password.equalsIgnoreCase("1")) {
                System.out.println("login success");
                Stage window = (Stage) LoginFormContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
            } else if
            (txtUserName.getText().isEmpty() && txtPassword.getText().isEmpty()) {
                lblWrong.setText("Please enter your data.");
            } else {
                lblWrong.setText("Wrong username or password!");
            }
        }
    }
}
