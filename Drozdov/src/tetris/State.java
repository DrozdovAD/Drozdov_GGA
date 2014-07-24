package tetris;

public class State {

	public Field field;
	public Figure figure;
	public Figure futurefigure;
	public int figureRow;
	public int figureColumn;
	public long speed=300;
	int coltospeed=0;
	public long score=0;
	public State(int rows, int columns) {
		field = new Field(rows+5, columns);
	}

	public void init() {
		field.cleanField();
		futurefigure = Figure.generateRandom();
		nextFigure();
		speed=800;
		score=0;
	}
	
	public void nextFigure() {
		figure=futurefigure;
		futurefigure = Figure.generateRandom();
		figureRow = 0;
		figureColumn = field.box[0].length / 2
				- figure.data[0].length / 2;
	}

	public void moveFigureLeft() {
		figureColumn--;
	}

	public void moveFigureRight() {
		figureColumn++;
	}

	public void moveFigureDown() {
		figureRow++;
	}

	public boolean canMoveDown() {
		return !field.hasConflict(figureColumn, figureRow + 1, figure.data);
	}
	public boolean canMoveLeft() {
		return !field.hasConflict(figureColumn-1, figureRow, figure.data);
	}
	public boolean canMoveRight() {
		return !field.hasConflict(figureColumn+1, figureRow, figure.data);
	}
	public boolean canRotateFlip(int[][] rotateLeft) {
		return !field.hasConflict(figureColumn, figureRow, rotateLeft);
	}
	

	public void pasteFigure() {
		for (int r = 0; r < figure.data.length; r++) {
			for (int c = 0; c < figure.data[r].length; c++) {
				if (figure.data[r][c] != 0) {
					field.box[figureRow + r][figureColumn + c] =
							figure.data[r][c];
				}
			}
		}
		
			coltospeed++;
		score+=(802-speed);
		if(coltospeed==3){
			coltospeed=0;
			if(speed>200) {speed-=speed/10;}
			else {speed-=speed/5;}
		}
	}

	public Field getField() {
		return field;
	}

	public Figure getFigure() {
		return figure;
	}

	public void removeFullRows() {
		field.removeFullRows();
	}

	public boolean gameOver() {
		if(!canMoveDown()){
			for (int r = 0; r < figure.data.length; r++) {
				for (int c = 0; c < figure.data[r].length; c++) {
					if ((figure.data[r][c] != 0)&&(r+figureRow<=4)) {
								return true;
					}
				}
			}
		}
		return false;
	}


}
