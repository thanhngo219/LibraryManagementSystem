package com.library.business;

import java.time.LocalDate;

public class SearchBook {
	private String isbn;
	private String title;
	private String copyId;
	private String memberFirstName;
	private String memberLastName;
	private LocalDate checkoutDate;
	private LocalDate dueDate;

	SearchBook(String isbn, String title, String copyId, String memberFirstName, String memberLastName,
			LocalDate checkoutDate, LocalDate dueDate) {
		this.isbn = isbn;
		this.title = title;
		this.copyId = copyId;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCopyId() {
		return copyId;
	}

	public void setCopyId(String copyId) {
		this.copyId = copyId;
	}

	public String getMemberFirstName() {
		return memberFirstName;
	}

	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}

	public String getMemberLastName() {
		return memberLastName;
	}

	public void setMemberLastName(String memberLastName) {
		this.memberLastName = memberLastName;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getCheckoutDateString() {
		return checkoutDate.toString();
	}

	public String getDueDateString() {
		return dueDate.toString();
	}

	public String getOverDueString() {
		return dueDate.isBefore(LocalDate.now()) ? "Yes" : "No";
	}
}
