package com.library.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Library implements Serializable {
	public static final String OUTPUT_LIBRARY = System.getProperty("user.dir") + "/library.txt";
	public static Library singleton = new Library();

	private Map<String, Book> books = new HashMap<String, Book>();
	private Map<Integer, Member> members = new HashMap<Integer, Member>();

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

	public static Book newBook(String isbn) {
		if (singleton.books.containsKey(isbn)) {
			return null;
		}
		Book book = new Book(isbn);
		singleton.books.put(isbn, book);
		return book;
	}

	public static Book getBook(String isbn) {
		return singleton.books.get(isbn);
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
