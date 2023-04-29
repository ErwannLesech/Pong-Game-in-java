package PongGame.src;

import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{
    private final int id;
    public int yVelocity;
    private final int speed = 10;
	public boolean isAi = false;

    public Paddle(int x, int y, int width, int height, int id){
        super(x, y, width, height);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch(id) {
            case 1:
                if(keyCode == KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if(keyCode == KeyEvent.VK_S) {
                    setYDirection(speed);
                }
                break;

            case 2:
                if(keyCode == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if(keyCode == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                }
                break;
				
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch(id) {
            case 1:
                if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S) {
                    setYDirection(0);
                }
                break;

            case 2:
                if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
        }
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        y += yVelocity;
    }

	public void setAi(boolean ai){
		this.isAi = ai;
	}

    public void draw(Graphics g) {
        if(id == 1) {
            g.setColor(Color.orange);
        } else {
            g.setColor(Color.green);
        }
        g.fillRect(x, y, width, height);
    }
}
