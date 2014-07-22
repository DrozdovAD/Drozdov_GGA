package Chess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Figure {

	public Queen(int i, int j, boolean color) {
		super('q', i, j, color);
	}

	@Override
	public boolean canAttend(int i, int j) {
		if ((i == this.x) && (j == this.y)) {
			return false;
		}
		if ((this.x - i == this.y - j) || (this.x - i == j - this.y)
				|| (this.x == i) || (this.y == j)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Coordinate> eraseDots(Coordinate figure,
			List<Coordinate> dotList) {
		List<Integer> list = new ArrayList<>();
		if (this.x == figure.x) {
			if (this.y < figure.y) {
				for (int i = 0; i < dotList.size(); i++) {
					if ((figure.y < dotList.get(i).y)
							&& (dotList.get(i).x == figure.x)) {
						list.add(i);
					}
				}
			}
			if (this.y > figure.y) {
				for (int i = 0; i < dotList.size(); i++) {
					if ((figure.y > dotList.get(i).y)
							&& (dotList.get(i).x == figure.x)) {
						list.add(i);
					}
				}
			}

		}
		if (this.y == figure.y) {
			if (this.x < figure.x) {
				for (int i = 0; i < dotList.size(); i++) {
					if ((figure.x < dotList.get(i).x)
							&& (dotList.get(i).y == figure.y)) {
						list.add(i);
					}
				}
			}
			if (this.x > figure.x) {
				for (int i = 0; i < dotList.size(); i++) {
					if ((figure.x > dotList.get(i).x)
							&& (dotList.get(i).y == figure.y)) {
						list.add(i);
					}
				}
			}

		}

		if ((this.x - figure.x) == (this.y - figure.y)) {
			if (this.x < figure.x) {
				for (int i = 0; i < dotList.size(); i++) {
					if ((figure.x < dotList.get(i).x)
							&& (figure.y < dotList.get(i).y)
							&& ((dotList.get(i).x- figure.x) == (dotList.get(i).y - figure.y))) {
						list.add(i);
						System.out.println(i);
					}
				}
			}
			if (this.x > figure.x) {
				for (int i = 0; i < dotList.size(); i++) {
					if ((figure.x > dotList.get(i).x)
							&& (figure.y > dotList.get(i).y)
							&& ((dotList.get(i).x- figure.x) == (dotList.get(i).y - figure.y))) {
						list.add(i);
						System.out.println(i);
					}
				}
			}
		}
			if ((this.x - figure.x) == ( figure.y-this.y)) {
				if (this.x < figure.x) {
					for (int i = 0; i < dotList.size(); i++) {
						if ((figure.x < dotList.get(i).x)
								&& (figure.y > dotList.get(i).y)
								&& ((dotList.get(i).x- figure.x) == figure.y-(dotList.get(i).y))) {
							list.add(i);
							System.out.println(i);
						}
					}
				}
				if (this.x > figure.x) {
					for (int i = 0; i < dotList.size(); i++) {
						if ((figure.x > dotList.get(i).x)
								&& (figure.y < dotList.get(i).y)
								&& ((dotList.get(i).x- figure.x) == figure.y-(dotList.get(i).y))) {
							list.add(i);
							System.out.println(i);
						}
					}
				}
			

		}
		System.out.println(list);
		for (Integer i = 0; i < list.size(); i++) {
			System.out.println("del"+dotList.get(list.get(i)).x+" "+dotList.get(list.get(i)).y);
			dotList.remove(dotList.get(list.get(i)));
			List<Integer> list1 = new ArrayList<>();
			list1.addAll(list);
			list.removeAll(list);
			for (Integer intlist : list1) {

				list.add(intlist - 1);
			}
		}
		return dotList;
	}
}