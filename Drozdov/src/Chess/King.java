package Chess;

import java.util.List;

public class King extends Figure {

	public King(int i, int j, boolean color) {
		super('k', i, j, color);
	}

	@Override
	public boolean canAttend(int i, int j) {
		if((i == this.x) && (j == this.y)) {
			return false;
		}
		if ((i >= this.x - 1) && (i <= this.x + 1) && (j >= this.y - 1)
				&& (j <= this.y + 1)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Coordinate> eraseDots(Coordinate figure, List<Coordinate> dotList2) {
		return dotList2;
		
	}

}
