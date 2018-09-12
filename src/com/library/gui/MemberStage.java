package com.library.gui;

import com.library.business.CheckoutEntry;
import com.library.business.Member;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class MemberStage extends Stage {
	public static final MemberStage INSTANCE = new MemberStage();
	private MemberController controller;

	private MemberStage() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("member.fxml"));
			Parent root = (Parent) loader.load();

			controller = (MemberController) loader.getController();
			TableView<CheckoutEntry> tableCheckoutEntries = controller.getTableCheckoutEntries();
			tableCheckoutEntries.setEditable(false);

			TableColumn<CheckoutEntry, String> bookTitleCol = new TableColumn<>(String.format("Book Title"));
			bookTitleCol.setMinWidth(80);
			bookTitleCol.setEditable(false);
			bookTitleCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("bookTitle"));
			bookTitleCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<CheckoutEntry, String> checkoutDateCol = new TableColumn<>(String.format("Checkout Date"));
			checkoutDateCol.setMinWidth(80);
			checkoutDateCol.setEditable(false);
			checkoutDateCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("checkoutDateString"));
			checkoutDateCol.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<CheckoutEntry, String> dueDateCol = new TableColumn<>(String.format("Due Date"));
			dueDateCol.setMinWidth(80);
			dueDateCol.setEditable(false);
			dueDateCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("dueDateString"));
			dueDateCol.setCellFactory(TextFieldTableCell.forTableColumn());

			tableCheckoutEntries.getColumns().addAll(bookTitleCol, checkoutDateCol, dueDateCol);

			Scene scene = new Scene(root, 800, 600);
			setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setMember(Member member) {
		controller.setMember(member);
	}

}
