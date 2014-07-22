package tetris;

public interface EventProcessor {

	void moveLeft();

	void moveRight();

	void drop();

	void rotate();

	void moveDown();

	void newGame();

	void flip();

	boolean gameOver();

}