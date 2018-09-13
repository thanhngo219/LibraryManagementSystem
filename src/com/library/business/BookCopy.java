package com.library.business;

import java.io.Serializable;

public class BookCopy implements Serializable {

	private static final long serialVersionUID = 4L;

	private Integer bookCopyId;

	private boolean available = true;

	private Book book;

	public Integer getBookCopyId() {
		return bookCopyId;
	}

	public void setBookCopyId(Integer bookCopyId) {
		this.bookCopyId = bookCopyId;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getBookCopyIdString() {
		return "" + bookCopyId;
	}

	public String getAvailableString() {
		return available ? "Available" : "Not available";
	}
}
