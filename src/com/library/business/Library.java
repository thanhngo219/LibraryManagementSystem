package com.library.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
	public static final String OUTPUT_LIBRARY = System.getProperty("user.dir") + "/library.txt";
	public static Library singleton = new Library();

	private List<Book> books = new ArrayList<>();
	private List<Member> members = new ArrayList<>();

	private Library() {

	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

//	public void addMember(Member member) {
//		members.add(member);
//	}
//
//	public void addBook(Book book) {
//		books.add(book);
//	}

	public static Book newBook() {
		Book book = new Book();
		singleton.books.add(book);
		return book;
	}

	public static Member newMember() {
		Member member = new Member();
		singleton.members.add(member);
		return member;
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
