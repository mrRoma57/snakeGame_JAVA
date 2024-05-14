package snakePackage;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class SnakeGame extends JPanel{
	int boardwidth;
	int boardheight;
	// to our constuctor //
	SnakeGame(int boardwidth , int boardheight){
		this.boardwidth = boardwidth;
		this.boardheight = boardheight;
		setPreferredSize(new Dimension(this.boardwidth,this.boardheight));
		setBackground(Color.DARK_GRAY);
	}

}
