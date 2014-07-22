package Chess;

import java.util.ArrayList;
import java.util.List;



public class Location {

	public static void main(String[] args) {
		List<Figure> figures= new ArrayList<>();
		
//		0 король
//		1 ферзь
//		2 тура
//		3 слон
//		4 лошадь
//		5 пешка
		
		
		figures.add(new King(1,2,false));
		figures.add(new Queen(3,4,true));
		figures.add(new King(6,1,true));
		figures.add(new Ladia(2,5,true));
		figures.add(new Queen(5,6,true));
		Logic logic=new Logic(figures);
	}
	
}
