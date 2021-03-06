package com.library.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {

	private static final long serialVersionUID = 3L;

	private String title;

	private String isbn;

	private BorrowDay borrowDay = BorrowDay.SEVEN_DAYS;

	private BookType bookType = BookType.GENERAL;

	private List<BookCopy> bookCopies = new ArrayList<>();

	private List<Author> authors = new ArrayList<>();

	Book(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BorrowDay getBorrowDay() {
		return borrowDay;
	}

	public void setBorrowDay(BorrowDay borrowDay) {
		this.borrowDay = borrowDay;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public List<BookCopy> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(List<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public void addBookCopy(BookCopy bookCopy) {
		this.bookCopies.add(bookCopy);
		bookCopy.setBook(this);
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public BookCopy getAvailableBookCopy() {
		for (BookCopy bookCopy : bookCopies) {
			if (bookCopy.isAvailable()) {
				return bookCopy;
			}
		}

		return null;
	}

	public int countAvailableBookCopies() {
		int count = 0;
		for (BookCopy bookCopy : bookCopies) {
			if (bookCopy.isAvailable()) {
				count++;
			}
		}

		return count;
	}

	public String getAuthorName() {
		StringBuilder sb = new StringBuilder();
		for(Author author : authors) {
			sb.append(author.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	public String getStrBorrowDay() {
		return Integer.toString(this.borrowDay.getDay());
	}

	public String getStrBookType() {
		return this.bookType.toString();
	}
}
