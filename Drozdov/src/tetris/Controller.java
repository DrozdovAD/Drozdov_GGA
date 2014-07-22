package tetris;

public class Controller implements ModelListener, EventProcessor {

	private Model _model;
	private View _view;

	public Controller(Model model, View view) {
		_model = model;
		_view = view;
	}

	@Override
	public void updateState(State state) {
		_view.updateState(state);
		if (_model.gameOver()) {
			_view.gameOver();
		}
	}

	@Override
	public void moveLeft() {
		if (!_model.gameOver()) {
			_model.moveLeft();
		}
	}

	@Override
	public void moveRight() {
		if (!_model.gameOver()) {
			_model.moveRight();
		}
	}

	@Override
	public void drop() {
		if (!_model.gameOver()) {
			_model.drop();
		}
	}

	@Override
	public void rotate() {
		if (!_model.gameOver()) {
			_model.rotate();
		}
	}

	@Override
	public void moveDown() {
		if (!_model.gameOver()) {
			_model.moveDown();
		}
	}

	@Override
	public void newGame() {
		_model.newGame();
	}

	@Override
	public void flip() {
		if (!_model.gameOver()) {
			_model.flip();
		}
	}

	@Override
	public boolean gameOver() {
		return _model.gameOver();
	}

}
