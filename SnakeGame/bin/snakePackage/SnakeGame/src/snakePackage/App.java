package snakePackage;
import javax.swing.*;
public class App {

	public static void main(String[] args) throws Exception {
		int boardwidth = 600;
		int boardheight = boardwidth;
        // now we set the width + height of the window //
		// to build the window it self //
		JFrame frame = new JFrame("[*] Snake [*]"); // give the title //
		frame.setVisible(true);
		frame.setSize(boardwidth, boardheight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//now we need JPanel to draw our game //
		// then we ,ll create an instance of this class==> SnakeGame //
		SnakeGame snake = new SnakeGame(boardwidth , boardheight);
		frame.add(snake);
		frame.pack();
	}

}
