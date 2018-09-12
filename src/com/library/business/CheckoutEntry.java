package com.library.business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {

	private LocalDate checkoutDate = LocalDate.now();

	private LocalDate dueDate = LocalDate.now();

	private BookCopy bookCopy;

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

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public String getBookTitle() {
		return bookCopy.getBook().getTitle();
	}

	public String getCheckoutDateString() {
		return checkoutDate.toString();
	}

	public String getDueDateString() {
		return dueDate.toString();
	}
}
