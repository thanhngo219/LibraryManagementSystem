package test;

import com.library.business.*;

import java.util.List;
import java.util.Map;


public class InitializeTestData {
	public static void main(String[] args) {
		Library.read();
		Map<String, Book> book = Library.getMapBooks();
		book.forEach((k, v) -> {
			System.out.println(k);
		});

		List<Author> authors = Library.getAuthors();
		for(Author author : authors) {
			System.out.println(author.toString());
		}
//		Member m1 = Library.newMember();
//		m1.setFirstName("Tam");
//		Member m2 = Library.newMember();
//		m2.setFirstName("Thuy");
//		Member m3 = Library.newMember();
//		m3.setFirstName("Thanh");
//
//		Author javaAuthor = Library.newAuthor("Kevin", "Wayne");
//		Author pythonAuthor = Library.newAuthor("Lemon", "Juice");
//
//		Book b1 = Library.newBook("1");
//		b1.setTitle("Java");
//		b1.setBookType(BookType.GENERAL);
//		b1.setBorrowDay(BorrowDay.SEVEN_DAYS);
//		b1.getAuthors().add(javaAuthor);
//
//		Book b2 = Library.newBook("2");
//		b2.setTitle("Python");
//		b2.setBookType(BookType.POPULAR);
//		b2.setBorrowDay(BorrowDay.TWENTY_ONE_DAYS);
//		b2.getAuthors().add(pythonAuthor);
//
//		BookCopy bc1_1 = new BookCopy();
//		bc1_1.setBookCopyId(1);
//		BookCopy bc1_2 = new BookCopy();
//		bc1_2.setBookCopyId(2);
//		BookCopy bc1_3 = new BookCopy();
//		bc1_3.setBookCopyId(3);
//
//		b1.addBookCopy(bc1_1);
//		b1.addBookCopy(bc1_2);
//		b1.addBookCopy(bc1_3);
//
//		BookCopy bc2_1 = new BookCopy();
//		bc2_1.setBookCopyId(1);
//		BookCopy bc2_2 = new BookCopy();
//		bc2_2.setBookCopyId(2);
//
//		b2.addBookCopy(bc2_1);
//		b2.addBookCopy(bc2_2);
//
//		{
//			CheckoutEntry ce1 = new CheckoutEntry();
//			ce1.setBookCopy(bc1_1);
//
//			m1.getCheckoutRecord().addCheckoutEntry(ce1);
//		}
//		{
//			CheckoutEntry ce1 = new CheckoutEntry();
//			ce1.setBookCopy(bc1_2);
//			m2.getCheckoutRecord().addCheckoutEntry(ce1);
//		}
//
//		Library.write();
	}
}
