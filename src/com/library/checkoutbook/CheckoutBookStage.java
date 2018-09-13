package com.library.checkoutbook;

import com.library.business.CheckoutEntry;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class CheckoutBookStage extends Stage {
	public static final CheckoutBookStage INSTANCE = new CheckoutBookStage();

	private CheckoutBookStage() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("checkoutbook.fxml"));
			Parent root = (Parent) loader.load();

			CheckoutBookController controller = (CheckoutBookController) loader.getController();
			TableView<CheckoutEntry> tableCheckoutEntries = controller.getTableCheckoutEntries();
			tableCheckoutEntries.setEditable(false);

			TableColumn<CheckoutEntry, String> bookTitleCol = new TableColumn<>(String.format("Book Title"));
			bookTitleCol.setMinWidth(400);
			bookTitleCol.setEditable(false);
			bookTitleCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("bookTitle"));
			bookTitleCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<CheckoutEntry, String> bookISBNCol = new TableColumn<>(String.format("ISBN"));
			bookISBNCol.setMinWidth(80);
			bookISBNCol.setEditable(false);
			bookISBNCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("bookISBN"));
			bookISBNCol.setCellFactory(TextFieldTableCell.forTableColumn());
			
			TableColumn<CheckoutEntry, String> bookCopyIdCol = new TableColumn<>(String.format("Copy Id"));
			bookCopyIdCol.setMinWidth(80);
			bookCopyIdCol.setEditable(false);
			bookCopyIdCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("bookCopyId"));
			bookCopyIdCol.setCellFactory(TextFieldTableCell.forTableColumn());
			
			TableColumn<CheckoutEntry, String> checkoutDateCol = new TableColumn<>(String.format("Checkout Date"));
			checkoutDateCol.setMinWidth(120);
			checkoutDateCol.setEditable(false);
			checkoutDateCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("checkoutDateString"));
			checkoutDateCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<CheckoutEntry, String> dueDateCol = new TableColumn<>(String.format("Due Date"));
			dueDateCol.setMinWidth(120);
			dueDateCol.setEditable(false);
			dueDateCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("dueDateString"));
			dueDateCol.setCellFactory(TextFieldTableCell.forTableColumn());

			tableCheckoutEntries.getColumns().addAll(bookTitleCol, bookISBNCol, bookCopyIdCol, checkoutDateCol, dueDateCol);

			Scene scene = new Scene(root, 800, 600);
			setScene(scene);
			setTitle("Check out Book");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
