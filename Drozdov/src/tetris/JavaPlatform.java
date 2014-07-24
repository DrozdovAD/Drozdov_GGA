package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JavaPlatform  implements Platform {
	
	
	private static Color[] colors = {Color.black, Color.blue, Color.red, Color.green, Color.yellow, Color.orange,Color.CYAN,Color.gray,Color.LIGHT_GRAY,Color.magenta,Color.pink};
	
	private JFrame _frame;
	private JPanel _panel;
	private PlatformKeyListener _keyListener;
	private Font f;

	public void init(JFrame frame, PlatformKeyListener keyListener) {
		_frame = frame;
		_keyListener = keyListener;

		_panel = new JPanel();
		_panel.setPreferredSize(new Dimension(500, 700));
		f = new Font(
                "Monospaced", Font.ITALIC, 30);
     _panel.setFont(f);
		frame.add(_panel);

		_frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					_keyListener.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					_keyListener.moveRight();
					break;
				case KeyEvent.VK_UP:
					_keyListener.rotate();
					break;
				case KeyEvent.VK_DOWN:
					_keyListener.drop();
					break;
				case KeyEvent.VK_SPACE:
					_keyListener.flip();
					break;
				case KeyEvent.VK_ENTER:
					_keyListener.newGame();
					break;
				}
			}
		});

	}

	@Override
	public void clearArea() {
		Graphics2D g = (Graphics2D) _panel.getGraphics();
		g.clearRect(0, 0, _panel.getWidth(), _panel.getHeight());
		
	}

	@Override
	public int backgroundColorIndex() {
		return 0;
	}

	@Override
	public void fillRect(int colorIndex, int x, int y, int width,
			int height) {
		Graphics2D g = (Graphics2D) _panel.getGraphics();
		g.setColor(colors[colorIndex]);
		g.fillRect(x, y, width, height);
	}

	@Override
	public int getWidth() {
		return _panel.getWidth();
	}

	@Override
	public int getHeight() {
		return _panel.getHeight();
	}

	@Override
	public void setKeyListener(PlatformKeyListener listener) {
		_keyListener = listener;
	}

	@Override
	public void gameOver() {
		Graphics2D g = (Graphics2D) _panel.getGraphics();
		g.setColor(Color.black);
		
		FontMetrics fm   = g.getFontMetrics(f);
		String s="Game Over:";
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(s, g);

		int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());

		g.drawString(s, (_frame.getWidth()-textWidth)/2, (_frame.getHeight()+textHeight-_panel.getHeight())/2);
		s="press ENTER";
		rect = fm.getStringBounds(s, g);

		textHeight = (int)(rect.getHeight()); 
		textWidth  = (int)(rect.getWidth());

		g.drawString(s, (_frame.getWidth()-textWidth)/2, (_frame.getHeight()-textHeight+_panel.getHeight())/2);
	}

	@Override
	public void drawScore(long score) {
		Graphics2D g = (Graphics2D) _panel.getGraphics();
		g.setColor(Color.black);
		
		FontMetrics fm   = g.getFontMetrics(f);
		String s=Long.toString(score);
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(s, g);

		int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());

		g.drawString(s, _frame.getWidth()*4/5-(textWidth)/2, (_frame.getHeight()+textHeight-_panel.getHeight())/2);		
	}
	
}
