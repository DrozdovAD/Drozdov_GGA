package tetris;

public interface PlatformKeyListener {

	void moveLeft();

	void moveRight();
	
	void drop();
	
	void rotate();

	void flip();

	void newGame();

}
