package tetris;

public class Logic implements EventProcessor {

	public State state;

	public Logic(State state) {
		this.state = state;
	}

	@Override
	public void moveLeft() {
		if (state.canMoveLeft()) {
			state.moveFigureLeft();
		}
	}

	@Override
	public void moveRight() {
		if (state.canMoveRight()) {
			state.moveFigureRight();
		}
	}

	@Override
	public void drop() {
		while (state.canMoveDown()) {
			state.moveFigureDown();
		}
		state.pasteFigure();
		state.removeFullRows();
		state.nextFigure();
	}
 
	@Override
	public void rotate() {
		if (state.canRotateFlip(Figure.rotateLeft(state.figure.data))) {
		state.figure.data = Figure.rotateLeft(state.figure.data);}
	}

	@Override
	public void flip() {
		if (state.canRotateFlip(Figure.flip(state.figure.data))) {
		state.figure.data = Figure.flip(state.figure.data);}
	}

	@Override
	public void moveDown() { 
		if (!state.canMoveDown()) {
			state.pasteFigure();
			state.removeFullRows();
			state.nextFigure();
			return;
		}
		state.moveFigureDown();
	}

	@Override
	public void newGame() {
		state.init();
	}
	@Override
	public boolean gameOver() {
		return state.gameOver();
	}

}
