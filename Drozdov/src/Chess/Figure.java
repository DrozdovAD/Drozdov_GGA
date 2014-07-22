package Chess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Figure {
	char name;
	int x;
	int y;
	boolean color;
	List<Coordinate> dotList = new ArrayList<>();
	List<Coordinate> dotList2 = new ArrayList<>();
	List<Coordinate> figList = new ArrayList<>();

	public Figure(char name, int i, int j, boolean color) {
		this.name = name;
		this.x = i;
		this.y = j;
		this.color = color;
	}

	public abstract boolean canAttend(int i, int j);

	public boolean canStateHere(boolean color) {
		if (color == this.color) {
			return false;
		} else {
			return true;
		}
	};

	public List<Coordinate> whereFigureCanState(List<Figure> figures,
			Field field) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// проверка куда может ходить этот вид фигур
				if (this.canAttend(i, j) == true) {
					dotList.add(new Coordinate(i, j));
				}
			}
		}

		// проверка можно ли съесть другую фигуру
		outer: for (Coordinate dot : dotList) {
			for (Figure fig : figures) {
				if ((fig.x == dot.x) && (fig.y == dot.y)) {
					if (this.canStateHere(fig.color)) {
						dotList2.add(dot);
					}
					figList.add(new Coordinate(fig.x, fig.y));

					continue outer;
				}
			}

			dotList2.add(dot);

		}
		
		//убираются недосягаемые из-зи фигур клетки
		for (Coordinate figure : figList) {
			dotList2 = this.eraseDots(figure, dotList2);
		}
		return dotList2;
	}

	public abstract List<Coordinate> eraseDots(Coordinate figure,
			List<Coordinate> dotList2);
}