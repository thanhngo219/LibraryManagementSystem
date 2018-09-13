package test;

import com.library.business.Role;
import com.library.business.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JavaObjectInputOutputStreamExample {

	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "\\src\\data\\user.txt";
	public static void main(String[] args) {
		

		try {

			// Store Serialized User Object in File
			FileOutputStream fileOutputStream = new FileOutputStream(
					OUTPUT_DIR);
			List<User> users = new ArrayList<>(Arrays.asList(
					new User("thanh", "1234", Role.LIBRARIAN),
					new User("thuy", "1234", Role.ADMIN),
					new User("tam", "1234", Role.BOTH)
			));

			ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
			output.writeObject(users);
			
			output.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		try {

			//Read from the stored file
			FileInputStream fileInputStream = new FileInputStream(new File(
					OUTPUT_DIR));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			List<User> user2 = (List<User>) input.readObject();
			for(User user: user2) {
				System.out.println(user.getUserName() + " " + user.getPassword() + " " + user.getRole());
			}
			input.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
