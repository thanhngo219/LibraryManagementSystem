package com.library.business;

public class Member extends Person {
	private Role role;

	private CheckoutRecord checkoutRecord = new CheckoutRecord();

	Member() {

	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}

}
