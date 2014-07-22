package Mail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Server {
	public static void main(String[] args) {


		
		
	
		
		
		MailTransferAgent mta = new MailTransferAgent();

		mta.Sending("example@gmail.commm", "1", "example@gail.com");
		mta.Sending("example@gmail.cm", "2", "example@gail.com");
		mta.Sending("example@gmail.com", "3", "example@gail.com");
		mta.Sending("example@gmail.cm", "4", "example@gail.com");
		mta.Sending("example@gmail.com", "5", "example@gail.com");
		mta.Sending("example@gmail.cm", "6", "example@gail.com");
//		mta.showLetters();
//		try {
//			Random randomTime = new Random();
//		    
//			Thread.currentThread().sleep(randomTime.nextInt(5000 + 1));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mta.showLetters();


		try {
			
			Thread.currentThread().sleep(5500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mta.showLetters();
		
		
		mta.deleteAll();


	}
}
