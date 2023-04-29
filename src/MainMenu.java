package PongGame.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.util.*;
import java.io.*;

public class MainMenu extends JFrame implements ActionListener {

    public MainMenu() {
        // Set up the JFrame
        setTitle("SUPER PONG GAME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Create a JPanel to hold the title
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("SUPER PONG GAME");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        add(buttonPanel, BorderLayout.CENTER);

        // Create the buttons
        JButton progressiveButton = new JButton("Progressive Mode");
        progressiveButton.addActionListener(this);
        progressiveButton.setBackground(Color.BLUE);
        buttonPanel.add(progressiveButton);

        JButton easyButton = new JButton("Easy Mode");
        easyButton.addActionListener(this);
        easyButton.setBackground(Color.GREEN);
        buttonPanel.add(easyButton);

        JButton hardButton = new JButton("Hard Mode");
        hardButton.addActionListener(this);
        hardButton.setBackground(Color.RED);
        buttonPanel.add(hardButton);

        // Create a JPanel to hold the bottom buttons
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomPanel.setBackground(Color.WHITE);
        add(bottomPanel, BorderLayout.SOUTH);

        // Create the quit button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        bottomPanel.add(quitButton);

        // Create the info button
        JButton infoButton = new JButton("Information");
        infoButton.addActionListener(this);
        bottomPanel.add(infoButton);

        // Display the JFrame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Progressive Mode")) {
            // Start the progressive mode game
            startProgressiveGame();
        } else if (command.equals("Easy Mode")) {
            // Start the easy mode game
            startEasyGame();
        } else if (command.equals("Hard Mode")) {
            // Start the hard mode game
            startHardGame();
        } else if (command.equals("Quit")) {
            // Quit the application
            System.exit(0);
        } else if (command.equals("Information")) {
            // Display the readme page
            displayReadme();
        }
    }

    private void startProgressiveGame() {
        new PongGame(1);
        System.out.println("Starting progressive mode game...");
        // quit the main menu
        dispose();
    }

    private void startEasyGame() {
        new PongGame(2);
        System.out.println("Starting easy mode game...");
        dispose();
    }

    private void startHardGame() {
        new PongGame(3);
        System.out.println("Starting hard mode game...");
        dispose();
    }

    private void displayReadme() {
        System.out.println("Displaying readme page in console...");
        // Open the readme file
        try {
            File readme = new File("README.md");
            Scanner scanner = new Scanner(readme);
            // Read the readme file line by line
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("README.md not found");
        }


    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
