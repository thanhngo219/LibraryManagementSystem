package com.library.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.library.business.Book;
import com.library.business.BookCopy;
import com.library.business.BookType;
import com.library.business.BorrowDay;
import com.library.business.CheckoutEntry;
import com.library.business.Library;
import com.library.business.Member;

public class DataAccessUtil {
	public static final String OUTPUT_LIBRARY = System.getProperty("user.dir") + "/library.txt";
	public static final String OUTPUT_MEMBER = System.getProperty("user.dir") + "/member.txt";
	public static final String OUTPUT_BOOK = System.getProperty("user.dir") + "/book.txt";

	public static Library read() {
		Library result = null;
		try {

			// Read from the stored file
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_LIBRARY));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			result = (Library) input.readObject();

			input.close();
			fileInputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void write(Library library) {
		try {

			// Store Serialized User Object in File
			FileOutputStream fileOutputStream = new FileOutputStream(new File(OUTPUT_LIBRARY));
			ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
			output.writeObject(library);

			output.close();
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Member> readMembers() {
		List<Member> result = null;
		try {

			// Read from the stored file
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_MEMBER));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			result = (List<Member>) input.readObject();
			input.close();
			fileInputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void writeMembers(List<Member> list) {
		try {

			// Store Serialized User Object in File
			FileOutputStream fileOutputStream = new FileOutputStream(new File(OUTPUT_MEMBER));
			ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
			output.writeObject(list);

			output.close();
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Book> readBooks() {
		List<Book> result = null;
		try {

			// Read from the stored file
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_BOOK));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			result = (List<Book>) input.readObject();
			input.close();
			fileInputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void writeBooks(List<Book> list) {
		try {

			// Store Serialized User Object in File
			FileOutputStream fileOutputStream = new FileOutputStream(new File(OUTPUT_BOOK));
			ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
			output.writeObject(list);

			output.close();
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Member m1 = new Member();
		m1.setFirstName("Tam");
		Member m2 = new Member();
		m2.setFirstName("Thuy");
		Member m3 = new Member();
		m3.setFirstName("Thanh");

		Book b1 = new Book();
		b1.setTitle("Java");
		b1.setIsbn("1");
		b1.setBookType(BookType.GENERAL);
		b1.setBorrowDay(BorrowDay.SEVEN_DAYS);

		Book b2 = new Book();
		b2.setTitle("Python");
		b2.setIsbn("2");
		b2.setBookType(BookType.POPULAR);
		b2.setBorrowDay(BorrowDay.TWENTY_ONE_DAYS);

		BookCopy bc1_1 = new BookCopy();
		bc1_1.setBookId(1);
		BookCopy bc1_2 = new BookCopy();
		bc1_2.setBookId(2);
		BookCopy bc1_3 = new BookCopy();
		bc1_3.setBookId(3);

		b1.addBookCopy(bc1_1);
		b1.addBookCopy(bc1_2);
		b1.addBookCopy(bc1_3);

		BookCopy bc2_1 = new BookCopy();
		bc2_1.setBookId(1);
		BookCopy bc2_2 = new BookCopy();
		bc2_2.setBookId(2);

		b2.addBookCopy(bc2_1);
		b2.addBookCopy(bc2_2);

		{
			CheckoutEntry ce1 = new CheckoutEntry();
			ce1.setBookCopy(bc1_1);

			m1.getCheckoutRecord().addCheckoutEntry(ce1);
		}
		{
			CheckoutEntry ce1 = new CheckoutEntry();
			ce1.setBookCopy(bc1_2);
			m2.getCheckoutRecord().addCheckoutEntry(ce1);
		}

		Library library = new Library();
		library.addBook(b1);
		library.addBook(b2);
		library.addMember(m1);
		library.addMember(m2);
		library.addMember(m3);

		write(library);

		Library l1 = read();
		l1.getBooks().get(0).setTitle("C#");
		System.out.println(l1);

		writeMembers(library.getMembers());
		writeBooks(library.getBooks());

		List<Book> books = readBooks();
		List<Member> members = readMembers();
		books.get(0).setTitle("C++");
		System.out.println();
	}
}
