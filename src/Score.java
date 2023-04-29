package PongGame.src;

import java.awt.*;

public class Score extends Rectangle {

    private static int screenWidth;
    private static int screenHeight;

    public int player1Score;
    public int player2Score;

    public Score(int screenWidth, int screenHeight) {
        Score.screenWidth = screenWidth;
        Score.screenHeight = screenHeight;
    }

    public void draw(Graphics g) {
        // set font and color for score display
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        
        // draw the center line of the court
        g.drawLine(screenWidth / 2, 0, screenWidth / 2, screenHeight);

		// draw the horizontal center line of the court
		g.drawLine(0, screenHeight / 2, screenWidth, screenHeight / 2);

		// draw the circle in the center of the court
		g.drawOval(screenWidth / 2 - 100, screenHeight / 2 - 100, 200, 200);

        // draw player 1's score on the left side of the court in orange
		g.setColor(Color.orange);
        g.drawString(String.format("%02d", player1Score), screenWidth / 2 - 100, 50);
        
        // draw player 2's score on the right side of the court in green
		g.setColor(Color.green);
        g.drawString(String.format("%02d", player2Score), screenWidth / 2 + 20, 50);
    }

    // getters and setters for player scores
    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }
}