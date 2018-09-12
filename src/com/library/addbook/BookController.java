package com.library.addbook;

import com.library.business.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbbBookType.getItems().addAll(BookType.values());
        cbbBorrowDay.getItems().addAll(BorrowDay.values());
        Library.read();
        cbbAuthors.getItems().addAll(Library.getAuthors());
    }

    public void addBook(ActionEvent event) {
        String title = txtTitle.getText();
        String isbnNumber = txtISBNNumber.getText();

        Author author = cbbAuthors.getValue();
        BookType bookType = cbbBookType.getValue();
        BorrowDay borrowDay = cbbBorrowDay.getValue();

        Book newBook = Library.newBook(isbnNumber);
        newBook.setTitle(title);
        newBook.getAuthors().add(author);
        newBook.setBookType(bookType);
        newBook.setBorrowDay(borrowDay);

        BookCopy newBookCopy = Library.newBookCopy(newBook);
        newBook.getBookCopies().add(newBookCopy);

        Library.write();
        System.out.println("New book is added.");
    }
}
