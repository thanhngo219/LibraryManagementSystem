package test;

import com.library.business.Book;
import com.library.business.BookCopy;
import com.library.business.BookType;
import com.library.business.BorrowDay;
import com.library.business.Library;
import com.library.business.Member;

public class InitializeTestData {
	public static void main(String[] args) {
		Member m1 = Library.newMember();
		m1.setFirstName("Tam");
		m1.setLastName("Nguyen");
		Member m2 = Library.newMember();
		m2.setFirstName("Thuy");
		m2.setLastName("Doan");
		Member m3 = Library.newMember();
		m3.setFirstName("Thanh");
		m3.setLastName("Ngo");

		Book b1 = Library.newBook("1");
		b1.setTitle("Java");
		b1.setBookType(BookType.GENERAL);
		b1.setBorrowDay(BorrowDay.SEVEN_DAYS);

		Book b2 = Library.newBook("2");
		b2.setTitle("Python");
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

		m1.checkOut(bc1_1);
		m2.checkOut(bc1_2);
		m2.checkOut(bc2_1);

		Library.write();
	}
}
