package test;

import com.library.business.Book;
import com.library.business.BookCopy;
import com.library.business.BookType;
import com.library.business.BorrowDay;
import com.library.business.CheckoutEntry;
import com.library.business.Library;
import com.library.business.Member;

public class InitializeTestData {
	public static void main(String[] args) {
		Member m1 = Library.newMember();
		m1.setFirstName("Tam");
		Member m2 = Library.newMember();
		m2.setFirstName("Thuy");
		Member m3 = Library.newMember();
		m3.setFirstName("Thanh");

		Book b1 = Library.newBook();
		b1.setTitle("Java");
		b1.setIsbn("1");
		b1.setBookType(BookType.GENERAL);
		b1.setBorrowDay(BorrowDay.SEVEN_DAYS);

		Book b2 = Library.newBook();
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

		Library.write();
	}
}
