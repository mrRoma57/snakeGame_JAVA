package pkgRT;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // to insert a new tile to body list of the snake
import java.util.Random; // to generate the random food of the snake  
import javax.swing.*; // to use Jpanel 
// before we make any functionality in game we need to make our game loop
// simply if we want to make snake move ; just change x,y coord.
public class SnakeGame extends JPanel implements ActionListener,KeyListener{
	int boardWidth;
	int boardHeight;
	int tileSize = 25;
    // as declare our variables 
    // let,s build our constructor 
	// the only way to supply our idea about tilewidth/height
	// is to identify x coor. and y coor. first as x,y 
	private class Tile{
		int x;
		int y;
		// add the tile constructor //
		Tile(int x,int y){
			this.x = x;
			this.y = y;
			// these x , y , widthTile ,heightTile dealing with only one unit == pixels
			// example : x * 25 = pixels units as the same y 
	        // after we finish everything in console we next want to draw it on panel	
		}
	}
	
	//snake
	Tile snakeHead;
	ArrayList<Tile> snakeBody;
	//food
	Tile Food;
	Random random;
	//game logic
	Timer gameLoop;
	int VelocityX;
	int VelocityY;
	boolean gameOver = false;
	SnakeGame(int boardWidth,int boardHeight){ // container that allocate every thing consider about snake //
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		setPreferredSize(new Dimension(this.boardWidth,this.boardHeight));
		setBackground(Color.darkGray);
		addKeyListener(this);
		setFocusable(true);
		snakeHead = new Tile(5,5);
		snakeBody = new ArrayList<Tile>();
		Food = new Tile(10,10);
		random = new Random();
		placeFood();
		gameLoop = new Timer(100, this);
		gameLoop.start();
		
		VelocityX = 0;
		VelocityY = 0;
		
		
		
	}
	
	// the method that focus in drawing 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g); // which we need to create 
	}
	
	public void draw(Graphics g) {
		//for glad 
//		for(int i = 0;i < boardWidth/tileSize;i++) {
//		 g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
//		 g.drawLine(0,i*tileSize,boardWidth , i*tileSize);
//		}
		//for Food
		g.setColor(Color.red);
		g.fill3DRect(Food.x * tileSize, Food.y * tileSize, tileSize, tileSize,true);
		// for SnakeHead
		g.setColor(Color.green);
		// we need to specify the x,y coordinates 
		g.fill3DRect(snakeHead.x * tileSize,snakeHead.y * tileSize, tileSize, tileSize,true);
	    // for snakeBody
		for(int i = 0 ;i < snakeBody.size(); i++) {
			Tile part = snakeBody.get(i);
			g.fill3DRect(part.x * tileSize, part.y * tileSize, tileSize, tileSize,true);
		}
		//score
		g.setFont(new Font("Arial" , Font.PLAIN , 16));
		if(gameOver) {
			g.setColor(Color.green);
			g.drawString("Game Over ~> your score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
		}
		else {
			g.drawString("Score: " + String.valueOf(snakeBody.size()) , tileSize - 16,tileSize);
		}
	} 	
	public void placeFood() {
		Food.x = random.nextInt(boardWidth/tileSize);
		Food.y = random.nextInt(boardHeight/tileSize);
	}

	// this function checks the condition of matching form of both snakeHead, Food
	public boolean collision(Tile tile1, Tile tile2) {
		return tile1.x == tile2.x && tile1.y == tile2.y;
	}
	
	public void move() {
		
		if(collision(snakeHead,Food)) {
			snakeBody.add(new Tile(Food.x,Food.y));
			placeFood();
		}
		
		for(int i = snakeBody.size()-1;i >= 0;i--) {
			Tile snakePart = snakeBody.get(i);
			if(i == 0) {
				snakePart.x = snakeHead.x;
				snakePart.y = snakeHead.y;
			}
			else {
				Tile prevTilePart = snakeBody.get(i-1);
				snakePart.x = prevTilePart.x;
				snakePart.y = prevTilePart.y;
			}
		}
		
		
		snakeHead.x += VelocityX;
		snakeHead.y += VelocityY;
		
		
		for(int i = 0;i < snakeBody.size() ;i++) {
			Tile snakePart = snakeBody.get(i);
			if(collision(snakeHead,snakePart)) {
				gameOver = true;
			}
		}
		
		if(snakeHead.x * tileSize < 0 || snakeHead.x * tileSize > boardWidth || snakeHead.y * tileSize < 0 || snakeHead.y * tileSize > boardHeight) {
			gameOver = true;
		}
		
		
		
	} 
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
        repaint();
		if(gameOver) {
			gameLoop.stop();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
	   if(e.getKeyCode() == KeyEvent.VK_UP && VelocityY != 1) {
		   VelocityX = 0;
		   VelocityY = -1;
	   }
	   else if(e.getKeyCode() == KeyEvent.VK_DOWN && VelocityY != -1) {
		   VelocityX = 0;
		   VelocityY = 1;
	   }
	   else if(e.getKeyCode() == KeyEvent.VK_LEFT && VelocityX != 1) {
		   VelocityX = -1;
		   VelocityY = 0;
	   }
	   else if(e.getKeyCode() == KeyEvent.VK_RIGHT && VelocityX != -1) {
		   VelocityX = 1;
		   VelocityY = 0;
	   }
	   else {
		   return;
	   }
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
}
