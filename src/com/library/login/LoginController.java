package com.library.login;

import com.library.business.User;
import com.library.business.UserDao;
import com.library.homescreen.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtID;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSubmit;

    public void onLoginClick(ActionEvent event) throws Exception {
        String id = txtID.getText();
        String password = txtPassword.getText();

        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID cannot be empty", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password cannot be empty", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        // Implement check ID and password
        UserDao userDao = new UserDao();
        User currentUser = userDao.login(id, password);

        if (currentUser == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username and password are incorrect", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../homescreen/homescreen.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Library Management System");
        HomeController homeController = fxmlLoader.<HomeController>getController();
        switch (currentUser.getRole()) {
            case LIBRARIAN: {
                homeController.disableBtnAddMember(true);
                homeController.disableBtnEditMember(true);
                homeController.disableBtnAddBook(true);
                homeController.disableBtnAddBookCopy(true);
                break;
            }
            case ADMIN: {
                homeController.disableBtnAddBook(true);
                homeController.disableBtnAddBookCopy(true);
                homeController.disableBtnAddCheckoutBook(true);
                break;
            }
            default:
                break;
        }
        homeController.initData(currentUser);

        stage.setResizable(false);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
