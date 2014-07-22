package Chess;

import java.util.List;

public class Logic {
	public Logic(List<Figure> figures) {
		
for (Figure figure : figures) {
		Field field=new Field();
		field=fillfield(figures,figure,field);
		field.show();
}

	}
	
	private Field fillfield(List<Figure> figures, Figure mainfigure, Field field) {
		//вывод всех фигур
for (Figure figure : figures) {field.field[figure.x][figure.y]=figure.name;}

List<Coordinate> dotList=mainfigure.whereFigureCanState(figures, field);
for (Coordinate dot : dotList) {
	field.field[dot.x][dot.y]=1;
}
		return field;
	}



}
