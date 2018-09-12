package com.library.gui;

import com.library.business.CheckoutEntry;
import com.library.business.Member;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class MemberController {
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
		lblFirstName.setText(member.getFirstName());
		lblLastName.setText(member.getLastName());
		tableCheckoutEntries
				.setItems(FXCollections.observableArrayList(member.getCheckoutRecord().getCheckoutEntries()));
	}

	@FXML
	private Label lblFirstName;

	@FXML
	private Label lblLastName;

	@FXML
	private TableView<CheckoutEntry> tableCheckoutEntries;

	@FXML
	private Button btnOK;

	public TableView<CheckoutEntry> getTableCheckoutEntries() {
		return tableCheckoutEntries;
	}

	public void setTableCheckoutEntries(TableView<CheckoutEntry> tableCheckoutEntries) {
		this.tableCheckoutEntries = tableCheckoutEntries;
	}

	public void clickOK(ActionEvent event) {
		MemberStage.INSTANCE.hide();
	}

}
