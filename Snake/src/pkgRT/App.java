package pkgRT;
import javax.swing.*;
public class App {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int boardWidth = 600;
		int boardHeight = boardWidth;
		// let,s create our frame 
		JFrame frame = new JFrame("[~ Snake ~]");
		frame.setVisible(true);
		frame.setSize(boardWidth , boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// now we need Jpanel to draw our game //
		SnakeGame snake = new SnakeGame(boardWidth, boardHeight);
		frame.add(snake);
		frame.pack();
        snake.requestFocus();
	}

}
