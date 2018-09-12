package com.library.addmember;

import com.library.business.Address;
import com.library.business.CheckoutRecord;
import com.library.business.Library;
import com.library.business.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
        Member member = Library.newMember();

        member.setFirstName(txtFirstName.getText());
        member.setLastName(txtLastName.getText());
        member.setPhoneNumber(txtPhoneNumber.getText());
        member.setAddress(new Address(txtStreet.getText(), txtCity.getText(),
                txtZip.getText(), txtState.getText()));
        member.setCheckoutRecord(new CheckoutRecord());

        Library.write();
    }


}
