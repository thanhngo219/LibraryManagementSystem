package com.library.addmember;

import com.library.business.Address;
import com.library.business.CheckoutRecord;
import com.library.business.Library;
import com.library.business.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class MemberController {

    @FXML
    private TextField txtMemberID;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtState;

    @FXML
    private TextField txtZip;

    @FXML
    private Button btnAddMember;

    public void btnAddMemberOnClick(ActionEvent event) {

        if (txtFirstName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "First Name cannot be empty", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (txtLastName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Last Name cannot be empty", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (txtPhoneNumber.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Phone Number cannot be empty", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        Member member = Library.newMember();

        member.setFirstName(txtFirstName.getText());
        member.setLastName(txtLastName.getText());
        member.setPhoneNumber(txtPhoneNumber.getText());
        member.setAddress(new Address(txtStreet.getText(), txtCity.getText(),
                txtZip.getText(), txtState.getText()));
        member.setCheckoutRecord(new CheckoutRecord());

        Library.write();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Member is added", ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
        txtMemberID.setText(Integer.toString(member.getMemberId()));
    }


}
