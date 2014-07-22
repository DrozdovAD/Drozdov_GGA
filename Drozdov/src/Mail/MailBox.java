package Mail;

import java.util.ArrayList;
import java.util.List;

class Letter {
	public String text;
	public int from;
	public int to;

	public Letter(int from, String text, int to) {
		this.from = from;
		this.to = to;
		this.text = text;

	}
};

public class MailBox {

	public String adress;
	public int id;
	public List<Letter> letters = new ArrayList<>();

	public MailBox(int id, String adress) {
		this.id = id;
		this.adress = adress;
	}

}
