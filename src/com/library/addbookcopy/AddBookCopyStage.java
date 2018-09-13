package com.library.addbookcopy;

import com.library.business.BookCopy;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class AddBookCopyStage extends Stage {
	public static final AddBookCopyStage INSTANCE = new AddBookCopyStage();

	private AddBookCopyStage() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("addbookcopy.fxml"));
			Parent root = (Parent) loader.load();

			AddBookCopyController controller = (AddBookCopyController) loader.getController();
			TableView<BookCopy> tableBookCopies = controller.getTableBookCopies();
			tableBookCopies.setEditable(false);

			TableColumn<BookCopy, String> bookCopyIdCol = new TableColumn<>(String.format("Copy Id"));
			bookCopyIdCol.setMinWidth(80);
			bookCopyIdCol.setEditable(false);
			bookCopyIdCol.setCellValueFactory(new PropertyValueFactory<BookCopy, String>("bookCopyIdString"));
			bookCopyIdCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<BookCopy, String> statusCol = new TableColumn<>(String.format("Status"));
			statusCol.setMinWidth(120);
			statusCol.setEditable(false);
			statusCol.setCellValueFactory(new PropertyValueFactory<BookCopy, String>("availableString"));
			statusCol.setCellFactory(TextFieldTableCell.forTableColumn());

			tableBookCopies.getColumns().addAll(bookCopyIdCol, statusCol);

			Scene scene = new Scene(root, 800, 600);
			setScene(scene);
			setTitle("Add Book Copy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
