package PongGame.src;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {

    private Random random;
    public int xVelocity;
    public int yVelocity;
    private int initialSpeed = 2;

    public Ball(int x, int y, int width, int height, int difficulty) {
        super(x, y, width, height);
        random = new Random();
		switch(difficulty) {
			case 1:
				initialSpeed = 2;
				break;
			case 2:
				initialSpeed = 3;
				break;
			case 3:
				initialSpeed = 6;
				break;
		}

        // randomly set initial x and y direction of ball
        int randomXDirection = random.nextBoolean() ? 1 : -1;
        int randomYDirection = random.nextBoolean() ? 1 : -1;

        setXVelocity(randomXDirection * initialSpeed);
        setYVelocity(randomYDirection * initialSpeed);
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void move() {
        // move the ball by its current velocity in both directions
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        // draw the ball as a filled circle
        g.setColor(Color.red);
        g.fillOval(x, y, width, height);
    }
}