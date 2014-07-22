package tetris;

import java.util.LinkedList;
import java.util.List;

public class Model implements EventProcessor {

	private static final int WIDTH = 10;
	private static final int HEIGHT = 20;
	Logic logic;
	State state;
	private List<ModelListener> _listeners;
	
	public Model() {
		_listeners = new LinkedList<>();
		state = new State(HEIGHT, WIDTH);
		logic = new Logic(state);
	}
	
	public void init() {
		state.init();
	}

	@Override
	public void moveLeft() {
		logic.moveLeft();
		updateState();
	}

	@Override
	public void moveRight() {
		logic.moveRight();
		updateState();
	}

	@Override
	public void drop() {
		logic.drop();
		updateState();
	}

	@Override
	public void rotate() {
		logic.rotate();
		updateState();
	}

	@Override
	public void moveDown() {
		logic.moveDown();
		updateState();
	}

	@Override
	public void flip() {
		logic.flip();
		updateState();		
	}
	@Override
	public void newGame() {
		logic.newGame();
		updateState();
	}

	private void updateState() {
		for (ModelListener listener : _listeners) {
			listener.updateState(state);
		}
	}

	public void addListener(ModelListener listener) {
		_listeners.add(listener);
	}

	@Override
	public boolean gameOver() {
		return logic.gameOver();
	}

	
}
