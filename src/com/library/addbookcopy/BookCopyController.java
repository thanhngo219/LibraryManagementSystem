package com.library.addbookcopy;

import com.library.business.Book;
import com.library.business.BookCopy;
import com.library.business.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BookCopyController {

    @FXML
    private TextField txtISBN;

    @FXML
    private Button btnLookUp;

    @FXML
    private Button btnAddBookCopy;

    private Book book;

    public void lookUp(ActionEvent event) {

        String isbn = txtISBN.getText();
        Library.read();
        book = Library.getBook(isbn);
        if (book == null) {
            System.out.println("Cannot find book " + isbn);
            return;
        }
        btnAddBookCopy.setDisable(false);

    }

    public void addBookCopy(ActionEvent event) {
        if (book == null) {
            System.out.println("Cannot find book " + txtISBN.getText());
            return;
        }

        BookCopy newBookCopy = Library.newBookCopy(book);
        Library.write();
        if (newBookCopy == null) {
            System.out.println("Cannot add new book copy.");
        }
        else {
            System.out.println("New book copy is added.");
        }
    }


}
