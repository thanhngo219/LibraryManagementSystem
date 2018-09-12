package com.library.homescreen;

import com.library.business.User;
import com.library.constant.LibraryConstant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnEditMember;

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnCheckoutBook;

    @FXML
    private Button btnAddBookCopy;

    private User currentUser;

    public void initData(User currentUser) {
        this.currentUser = currentUser;
    }

    public void btnAddMemberOnClick(ActionEvent event) throws Exception {
        navigateToNewScreen("../addmember/addMember.fxml", event);
    }

    public void btnEditMemberOnClick(ActionEvent event) throws Exception {
        navigateToNewScreen("../editMember/editMember.fxml", event);
    }

    public void btnAddBookOnClick(ActionEvent event) throws Exception {
        navigateToNewScreen("../addbook/book.fxml", event);
    }

    public void btnAddBookCopyOnClick(ActionEvent event) throws Exception {
        navigateToNewScreen("../addbookcopy/bookcopy.fxml", event);
    }

    public void btnCheckoutBookOnClick(ActionEvent event) {
        System.out.println("btnCheckoutBookOnClick!");
    }

    public void disableBtnAddMember(boolean value) {
        btnAddMember.setDisable(value);
    }

    public void disableBtnEditMember(boolean value) {
        btnEditMember.setDisable(value);
    }

    public void disableBtnAddBook(boolean value) {
        btnAddBook.setDisable(value);
    }

    public void disableBtnAddCheckoutBook(boolean value) {
        btnCheckoutBook.setDisable(value);
    }

    public void disableBtnAddBookCopy(boolean value) {
        btnAddBookCopy.setDisable(value);
    }

    private void navigateToNewScreen(String fxmlPath, ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(LibraryConstant.APPLICATION_TITLE);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
