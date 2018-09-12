package com.library.addbook;

import com.library.business.Author;
import com.library.business.BookType;
import com.library.business.BorrowDay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BookController {


    @FXML
    private Label lblTitle;

    @FXML
    private Label lblISBN;

    @FXML
    private Label lblAuthors;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtISBNNumber;

    @FXML
    private ComboBox<Author> cbbAuthors;

    @FXML
    private ComboBox<BookType> cbbBookType;

    @FXML
    private ComboBox<BorrowDay> cbbBorrowDay;


    @FXML
    private Button btnAddBook;


    public void addBook(ActionEvent event) {
        String title = txtTitle.getText();
        String ISBNNumber = txtISBNNumber.getText();

        Author author = cbbAuthors.getValue();
        BookType bookType = cbbBookType.getValue();
        BorrowDay borrowDay = cbbBorrowDay.getValue();

        System.out.println("AddBook" + title + ISBNNumber + author + bookType + borrowDay);

    }


}
