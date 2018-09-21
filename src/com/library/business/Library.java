package com.library.business;

import java.io.*;
import java.util.*;

public class Library implements Serializable {
	public static final String OUTPUT_LIBRARY = System.getProperty("user.dir") + "/library.txt";
	public static Library singleton = new Library();

	private Map<String, Book> books = new HashMap<String, Book>();
	private Map<Integer, Member> members = new HashMap<Integer, Member>();
	private List<Author> authors = new ArrayList<>();

	private Library() {

	}

//	public Map<String, Book> getBooks() {
//		return books;
//	}
//
//	public void setBooks(Map<String, Book> books) {
//		this.books = books;
//	}
//
//	public Map<Integer, Member> getMembers() {
//		return members;
//	}
//
//	public void setMembers(Map<Integer, Member> members) {
//		this.members = members;
//	}

//	public void addMember(Member member) {
//		members.add(member);
//	}
//
//	public void addBook(Book book) {
//		books.add(book);
//	}

	public static Map<Integer, Member> getMapMembers() {
		return singleton.members;
	}

	public static List<Author> getAuthors() {
		return singleton.authors;
	}

	public static Map<String, Book> getMapBooks() {
		return singleton.books;
	}

	public static List<Book> getBooks() {
		return new ArrayList<>(singleton.books.values());
	}

	public static Book newBook(String isbn) {
		if (singleton.books.containsKey(isbn)) {
			return null;
		}
		Book book = new Book(isbn);
		singleton.books.put(isbn, book);
		return book;
	}

	public static BookCopy newBookCopy(Book book) {
		List<BookCopy> bookCopies = book.getBookCopies();
		Comparator<BookCopy> comparator = Comparator.comparing(BookCopy::getBookCopyId);
		int nextId;
		if (bookCopies.isEmpty()) {
			nextId = 1;
		} else {
			BookCopy latestBookCopy = bookCopies.stream().max(comparator).get();
			nextId = latestBookCopy.getBookCopyId() + 1;
		}

		BookCopy newBookCopy = new BookCopy();
		newBookCopy.setBookCopyId(nextId);
		newBookCopy.setBook(book);
		bookCopies.add(newBookCopy);

		return newBookCopy;
	}

	public static Book getBook(String isbn) {
		return singleton.books.get(isbn);
	}

	public static Author newAuthor(String fName, String lName) {
		Author author = new Author();
		author.setFirstName(fName);
		author.setLastName(lName);
		singleton.authors.add(author);
		return author;
	}

	public static Member newMember() {
		int max = 0;
		for (int key : singleton.members.keySet()) {
			if (key > max) {
				max = key;
			}
		}
		max++;
		Member member = new Member(max);
		singleton.members.put(max, member);
		return member;
	}

	public static Member getMember(int memberId) {
		return singleton.members.get(memberId);
	}

	public static List<SearchBook> searchBook(String isbn) {
		List<SearchBook> result = new ArrayList<>();
		for (Member member : singleton.members.values()) {
			List<CheckoutEntry> checkoutEntries = member.getCheckoutRecord().getCheckoutEntries();
			for (CheckoutEntry checkoutEntry : checkoutEntries) {
				BookCopy bookCopy = checkoutEntry.getBookCopy();
				Book book = bookCopy.getBook();
				if (book.getIsbn().equals(isbn) && bookCopy.isAvailable() == false) {
					SearchBook searchBook = new SearchBook(book.getIsbn(), book.getTitle(),
							"" + bookCopy.getBookCopyId(), member.getFirstName(), member.getLastName(),
							checkoutEntry.getCheckoutDate(), checkoutEntry.getDueDate());
					result.add(searchBook);
				}
			}
		}

		return result;
	}

	public static void read() {
		try {
			// Read from the stored file
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_LIBRARY));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			singleton = (Library) input.readObject();
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void write() {
		try {
			// Store Serialized User Object in File
			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_LIBRARY);
			ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
			output.writeObject(singleton);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
