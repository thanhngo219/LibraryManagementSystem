package com.library.business;

public class Member extends Person {
	private int memberId;

	private CheckoutRecord checkoutRecord = new CheckoutRecord();

	Member(int memberId) {
		this.memberId = memberId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}

	public void checkOut(BookCopy bookCopy) {
		checkoutRecord.addCheckoutEntry(new CheckoutEntry(bookCopy));
	}
}
