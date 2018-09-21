package com.library.searchbook;

import com.library.business.SearchBook;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class SearchBookStage extends Stage {

	public static final SearchBookStage INSTANCE = new SearchBookStage();

	private SearchBookStage() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("searchbook.fxml"));
			Parent root = (Parent) loader.load();

			SearchBookController controller = (SearchBookController) loader.getController();
			TableView<SearchBook> tableCheckoutEntries = controller.getTableSearchBooks();
			tableCheckoutEntries.setEditable(false);

//			TableColumn<SearchBook, String> bookISBNCol = new TableColumn<>(String.format("ISBN"));
//			bookISBNCol.setMinWidth(60);
//			bookISBNCol.setEditable(false);
//			bookISBNCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("isbn"));
//			bookISBNCol.setCellFactory(TextFieldTableCell.forTableColumn());
//
//			TableColumn<SearchBook, String> bookTitleCol = new TableColumn<>(String.format("Book Title"));
//			bookTitleCol.setMinWidth(300);
//			bookTitleCol.setEditable(false);
//			bookTitleCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("title"));
//			bookTitleCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<SearchBook, String> bookCopyIdCol = new TableColumn<>(String.format("Copy Id"));
			bookCopyIdCol.setMinWidth(80);
			bookCopyIdCol.setEditable(false);
			bookCopyIdCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("copyId"));
			bookCopyIdCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<SearchBook, String> memberFirstNameCol = new TableColumn<>(String.format("First Name"));
			memberFirstNameCol.setMinWidth(150);
			memberFirstNameCol.setEditable(false);
			memberFirstNameCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("memberFirstName"));
			memberFirstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<SearchBook, String> memberLastNameCol = new TableColumn<>(String.format("Last Name"));
			memberLastNameCol.setMinWidth(150);
			memberLastNameCol.setEditable(false);
			memberLastNameCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("memberLastName"));
			memberLastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<SearchBook, String> checkoutDateCol = new TableColumn<>(String.format("Checkout Date"));
			checkoutDateCol.setMinWidth(120);
			checkoutDateCol.setEditable(false);
			checkoutDateCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("checkoutDateString"));
			checkoutDateCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<SearchBook, String> dueDateCol = new TableColumn<>(String.format("Due Date"));
			dueDateCol.setMinWidth(120);
			dueDateCol.setEditable(false);
			dueDateCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("dueDateString"));
			dueDateCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<SearchBook, String> overdueCol = new TableColumn<>(String.format("Overdue?"));
			overdueCol.setMinWidth(120);
			overdueCol.setEditable(false);
			overdueCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("OverDueString"));
			overdueCol.setCellFactory(TextFieldTableCell.forTableColumn());

			tableCheckoutEntries.getColumns().addAll(bookCopyIdCol, memberFirstNameCol, memberLastNameCol,
					checkoutDateCol, dueDateCol, overdueCol);

			Scene scene = new Scene(root, 800, 600);
			setScene(scene);
			setTitle("Search Overdue Book");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
