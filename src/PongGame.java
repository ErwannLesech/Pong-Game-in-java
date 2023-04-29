package PongGame.src;

import java.awt.*;
import javax.swing.*;


public class PongGame extends JFrame{

	public PongGame(int gamemode){
		GamePanel panel = new GamePanel(gamemode);
		add(panel);
		setTitle("Pong Game");
		setResizable(false);
		setBackground(Color.gray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
}