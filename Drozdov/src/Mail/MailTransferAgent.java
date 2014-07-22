package Mail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
				try {

					Connection connection = DriverManager
							.getConnection("jdbc:mysql://localhost/Mail?user=root");

					Letter letter = fillLetter(text, to, from, connection);

					synchronized (mutex) {
						for (MailBox box : list) {
						if(box.id==letter.to) {
							box.letters.add(letter);
						}
						addLetter(letter, connection);
						if(box.id==letter.from) {
							box.letters.add(letter);
						}
						}
					}
					connection.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Sending " + "from " + from + " to " + to
						+ ": " + text);
			}
		}).start();
	}
			private Letter fillLetter(final String text, String to,
					String from, Connection connection) throws SQLException {
				Statement st = connection.createStatement();
				int toid = -1, fromid = -1;
				ResultSet resultSet = st.executeQuery("select * from Users");
				while (resultSet.next()) {

					if ((resultSet.getString("adress").equals(from))) {
						fromid = Integer.parseInt(resultSet.getString("id"));
					}

					if ((resultSet.getString("adress").equals(to))) {
						toid = Integer.parseInt(resultSet.getString("id"));
					}

				}

				resultSet.close();

				// если не нашлось такого адрееса, то добавим его
				if (fromid == -1) {
					fromid = addUser(from, connection);
					addMailBox(fromid,from);

				}
				if (toid == -1) {
					toid = addUser(to, connection);
					addMailBox(toid,to);
				}

				resultSet.close();
				st.close();

				return new Letter(fromid, text, toid);
			}

		

	

	private int addUser(final String adress, Connection connection)
			throws SQLException {
		String query = " insert into Users (id, adress)" + " values (?, ?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		Statement st = connection.createStatement();
		ResultSet resultSet = st.executeQuery("select * from Users");
		int id = 0;

		if (resultSet.isBeforeFirst()) {
			resultSet = st.executeQuery("select max(id) from Users");
			while (resultSet.next()) {
				id = resultSet.getInt("max(id)");
				id++;
			}
		}

		preparedStmt.setInt(1, id);
		preparedStmt.setString(2, adress);
		preparedStmt.execute();
		return id;
	}

	private void addLetter(Letter letter, Connection connection)
			throws SQLException {
		Statement st = connection.createStatement();
		String query = " insert into Letters (fromid, text,toid)"
				+ " values (? ,?, ?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		preparedStmt.setInt(1, letter.from);
		preparedStmt.setString(2, letter.text);
		preparedStmt.setInt(3, letter.to);
		preparedStmt.execute();

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
		try {
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost/Mail?user=root");
			Statement st = connection.createStatement();

			ResultSet resultSet = st.executeQuery("select * from Users");

			while (resultSet.next()) {
				addMailBox(resultSet.getInt("id"),resultSet.getString("adress"));
			}

			resultSet.close();

			

				resultSet = st
						.executeQuery("select * from Letters ");
				while (resultSet.next()) {
					
					
					list.get(resultSet.getInt("fromid")).letters.add(new Letter(resultSet.getInt("fromid"),
							resultSet.getString("text"), resultSet
									.getInt("toid")));
					list.get(resultSet.getInt("toid")).letters.add(new Letter(resultSet.getInt("fromid"),
							resultSet.getString("text"), resultSet
							.getInt("toid")));
				}
				resultSet.close();
			

			st.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteAll() {
		try {
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost/Mail?user=root");
		Statement st = connection.createStatement();
		String query = "delete from Users";	
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.execute();
		query = "delete from Letters";	
		preparedStmt = connection.prepareStatement(query);
		preparedStmt.execute();
		System.out.println("deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}