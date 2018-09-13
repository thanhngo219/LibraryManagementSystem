package com.library.addbook;

import com.library.business.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private TableView<Book> tbvBook;

    @FXML
    private Button btnAddBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbbBookType.getItems().addAll(BookType.values());
        cbbBorrowDay.getItems().addAll(BorrowDay.values());
        Library.read();
        cbbAuthors.getItems().addAll(Library.getAuthors());

        TableColumn<Book, String> bookTitleCol = new TableColumn<>(String.format("Title"));
        bookTitleCol.setMinWidth(140);
        bookTitleCol.setEditable(false);
        bookTitleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookTitleCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Book, String> bookISBNCol = new TableColumn<>(String.format("ISBN"));
        bookISBNCol.setMinWidth(80);
        bookISBNCol.setEditable(false);
        bookISBNCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        bookISBNCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Book, String> borrowDayCol = new TableColumn<>(String.format("Author"));
        borrowDayCol.setMinWidth(120);
        borrowDayCol.setEditable(false);
        borrowDayCol.setCellValueFactory(new PropertyValueFactory<Book, String>("authorName"));
        borrowDayCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Book, String> authorCol = new TableColumn<>(String.format("Borrow Day"));
        authorCol.setMinWidth(120);
        authorCol.setEditable(false);
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("strBorrowDay"));
        authorCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Book, String> bookTypeCol = new TableColumn<>(String.format("Book Type"));
        bookTypeCol.setMinWidth(120);
        bookTypeCol.setEditable(false);
        bookTypeCol.setCellValueFactory(new PropertyValueFactory<Book, String>("strBookType"));
        bookTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tbvBook.getColumns().setAll(bookTitleCol, bookISBNCol, authorCol, bookTypeCol, borrowDayCol);

        List<Book> list = Library.getBooks();
        tbvBook.setItems(FXCollections.observableArrayList(list));
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
        tbvBook.getItems().clear();
        tbvBook.setItems(FXCollections.observableArrayList(Library.getBooks()));
    }
}
