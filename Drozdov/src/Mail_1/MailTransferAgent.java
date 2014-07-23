package Mail_1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MailTransferAgent {
	private Object mutex = new Object();
	private static List<MailBox> list = new ArrayList<>();
	
	

	{
		initMailBoxes();
	}

	public void Sending(final String from, final String text, final String to) {

		new Thread(new Runnable() {

			@Override
			public void run() {

				pause();
				InputStream resourseAsStream;
				try {
					resourseAsStream = Resources.getResourceAsStream("Mail_1/config.xml");
					SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(resourseAsStream);
					factory.getConfiguration().addMapper(mailBoxMapper.class);
					factory.getConfiguration().addMapper(LetterMapper.class);
					
					SqlSession session =factory.openSession();
					
					
					mailBoxMapper mapper=session.getMapper(mailBoxMapper.class);

					Letter letter = fillLetter(text, to, from, mapper);

					synchronized (mutex) {
						addLetter(letter,mapper);
						for (MailBox box : list) {
						if(box.id==letter.to) {
							box.letters.add(letter);
						}
						if(box.id==letter.from) {
							box.letters.add(letter);
						}
						}
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Sending " + "from " + from + " to " + to
						+ ": " + text);
			}
		}).start();
	}
			private Letter fillLetter(final String text, String to,
					String from, mailBoxMapper mapper)  {
				int toid = -1, fromid = -1;
				List<MailBox> listmb=mapper.selectAllBoxes();
				for (MailBox mailBox : listmb) {
					if ((mailBox.adress.equals(from))) {
						fromid = mailBox.id;
					}

					if ((mailBox.adress.equals(to))) {
						toid = mailBox.id;
					}

				}


				// если не нашлось такого адрееса, то добавим его
				if (fromid == -1) {
					fromid = addUser(from, mapper);
					addMailBox(fromid,from);

				}
				if (toid == -1) {
					toid = addUser(to, mapper);
					addMailBox(toid,to);
				}

				return new Letter(fromid, text, toid);
			}

		

	

	private int addUser(final String adress, mailBoxMapper mapper)
			{
		
		int count=mapper.selectCountBoxes();
		mapper.InsertIntoMailBox(count, adress);
		
		return count;
	}

	private void addLetter(Letter letter, mailBoxMapper mapper)
			 {
	mapper.InsertIntoLetters(letter.from, letter.text, letter.to);

	}

	private void pause() {
		try {
			Random randomTime = new Random();

			Thread.currentThread().sleep(randomTime.nextInt(5000 + 1));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showLetters() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				synchronized (mutex) {
					for (MailBox mailBox : list) {
						System.out.println("MailBox # " +mailBox.id+" :"+ mailBox.adress+" ");

						for (Letter letters : mailBox.letters) {
							if (mailBox.id == letters.from) {
								System.out.print("Send:     ");
							} else {
								System.out.print("Recieved: ");
							}

							System.out.println(letters.text + ", from:"
									+ letters.from + ", to:" + letters.to);
						}
						System.out.println();

					}
				}
			}
		}).start();
	}

	private void addMailBox(int id, final String adress) {

		list.add(new MailBox(id, adress));

	}

	private void initMailBoxes() {
		 InputStream resourseAsStream;
		try {
			resourseAsStream = Resources.getResourceAsStream("Mail_1/config.xml");
			SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(resourseAsStream);
			factory.getConfiguration().addMapper(mailBoxMapper.class);
			factory.getConfiguration().addMapper(LetterMapper.class);
			
			SqlSession session =factory.openSession();
			
			
			mailBoxMapper mapper=session.getMapper(mailBoxMapper.class);
			
			list=mapper.selectAllBoxes();
			for(MailBox mb:list){
				SqlSession session1 =factory.openSession();
				LetterMapper mapper1=session1.getMapper(LetterMapper.class);
				mb.letters.addAll(mapper1.loadLetters());
				session1.close();
			}
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void deleteAll() {
		InputStream resourseAsStream;
		try {
			resourseAsStream = Resources.getResourceAsStream("Mail_1/config.xml");
			SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(resourseAsStream);
			factory.getConfiguration().addMapper(mailBoxMapper.class);
			factory.getConfiguration().addMapper(LetterMapper.class);
			
			SqlSession session =factory.openSession();
			
			
			mailBoxMapper mapper=session.getMapper(mailBoxMapper.class);
			
		//	mapper.deleteAll("MailBox");
			mapper.deleteFromLetters();
			mapper.deleteFromMailBox();
			
		System.out.println("deleted");
		} catch (
				IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}