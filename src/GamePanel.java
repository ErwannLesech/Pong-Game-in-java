package PongGame.src;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

	// Set up the game screen
    private static final int screenWidth = 1000;
    private static final int screenHeight = (int) (screenWidth * 0.5555);
    private static final Dimension SCREEN_SIZE = new Dimension(screenWidth, screenHeight);
    
	// Set up the ball and paddles sizes
	private static final int ballSize = 20;
    private static final int paddleWidth = 25;
    private static final int paddleHeight = 100;

	private boolean isOver = false;
	private int gamemode = 0;

    private Thread gameThread;
    private Image image;
    private Graphics graphics;
    private Random random;
    
	// Create the paddles, ball, and score
	private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Score score;

    public GamePanel(int gamemode) {
		this.gamemode = gamemode;
        newPaddles();
        newBall();
        score = new Score(screenWidth, screenHeight);

        setFocusable(true);
        addKeyListener(new AL());
        setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((screenWidth / 2) - (ballSize / 2), random.nextInt(screenHeight - ballSize),
                ballSize, ballSize, this.gamemode);
    }

	public void newPaddles() {
		int paddle1YPosition = (screenHeight / 2) - (paddleHeight / 2);
		int paddle2XPosition = screenWidth - paddleWidth;
		int paddle2YPosition = (screenHeight / 2) - (paddleHeight / 2);
	
		paddle1 = new Paddle(0, paddle1YPosition, paddleWidth, paddleHeight, 1);
		paddle2 = new Paddle(paddle2XPosition, paddle2YPosition, paddleWidth, paddleHeight, 2);
	}

	public void paint(Graphics g) {

		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);

	}

	public void draw(Graphics g) {
		score.draw(g);
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		Toolkit.getDefaultToolkit().sync(); // Ensures that the display is up-to-date
	}

	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}

	public void checkCollision() {

		// Bounce ball off top and bottom window edges
		if (ball.y <= 0) {
			ball.setYVelocity(-ball.yVelocity);
		}

		if (ball.y >= screenHeight - ballSize) {
			ball.setYVelocity(-ball.yVelocity);
		}

		// Bounce ball off paddles
		if (ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			if(this.gamemode == 1)
				ball.xVelocity++; 
			if (ball.yVelocity > 0 && this.gamemode == 1)
				ball.yVelocity++; 
			else
			{
				if(this.gamemode == 1)
					ball.yVelocity--;
			}
			ball.setXVelocity(ball.xVelocity);
			ball.setYVelocity(ball.yVelocity);
		}

		if (ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			if(this.gamemode == 1)
				ball.xVelocity++; 
			if (ball.yVelocity > 0 && gamemode == 1)
				ball.yVelocity++; 
			else
			{
				if(this.gamemode == 1)
					ball.yVelocity--;
			}
			ball.setXVelocity(-ball.xVelocity);
			ball.setYVelocity(ball.yVelocity);
		}

		// Give a player 1 point and create new paddles and ball
		if (ball.x <= 0) {
			score.player2Score++;
			newPaddles();
			newBall();
			System.out.println("Player 2: " + score.player2Score);
		}

		if (ball.x >= screenWidth - ballSize) {
			score.player1Score++;
			newPaddles();
			newBall();
			System.out.println("Player 1: " + score.player1Score);
		}

		// Stop paddles at window edges
		if (paddle1.y <= 0)
			paddle1.y = 0;
		if (paddle1.y >= (screenHeight - paddleHeight))
			paddle1.y = screenHeight - paddleHeight;
		if (paddle2.y <= 0)
			paddle2.y = 0;
		if (paddle2.y >= (screenHeight - paddleHeight))
			paddle2.y = screenHeight - paddleHeight;
	}

	public void run() {

		//game loop
		long lastTime = System.nanoTime();
		double framepersecond = 60.0;
		double ns = 1000000000 / framepersecond;
		double delta = 0;

		while(!isOver) {
			
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;

			if(delta >= 1) {
				
				if(paddle1.isAi) {
					// position of the paddle is the position of the ball minus half of the paddle height
					paddle1.y = ball.y - (paddle1.height/2);
				}
				move();
				checkCollision();
				repaint();

				delta--;
			}

			// End game if a player reaches 3 points
			if (score.player1Score == 3 || score.player2Score == 3) {
				isOver = true;
				int winner = score.player1Score == 3 ? 3 : 2;

				int option = JOptionPane.showOptionDialog(
					null,                           
					"The winner is: player " + winner,    
					"Game Over",                    
					JOptionPane.YES_NO_OPTION,      
					JOptionPane.PLAIN_MESSAGE,     
					null,                           
					new String[] {"Replay", "Main Menu"},
					null                            
				);

				if (option == JOptionPane.YES_OPTION) {
					// Replay the game
					score.player1Score = 0;
					score.player2Score = 0;
					newPaddles();
					newBall();
					isOver = false;
				} 
				else {
					// Return to the main menu
					new MainMenu();
				}
			}
		}
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_R) {
				if(paddle1.isAi) {
					System.out.println("ai mode deactivated");
					paddle1.setAi(false);
				}
				else {
					System.out.println("ai mode activated");
					paddle1.setAi(true);
				}
			}
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
