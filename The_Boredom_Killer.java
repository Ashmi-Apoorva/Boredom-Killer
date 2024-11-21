/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package the_boredom_killer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author ashmi
 */
public class The_Boredom_Killer {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(The_Boredom_Killer::new);
    }

    public The_Boredom_Killer() {
        // Create main frame
        JFrame launcherFrame = new JFrame("Boredom Killer - Game Launcher");
        launcherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launcherFrame.setSize(400, 300);
        launcherFrame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("THE BOREDOM KILLER", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        launcherFrame.add(titleLabel, BorderLayout.NORTH);

        // Buttons for games
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton rpsButton = new JButton("Rock-Paper-Scissors");
        JButton cricketButton = new JButton("Cricket");
        JButton quizButton = new JButton("Quiz Game");
        buttonPanel.add(rpsButton);
        buttonPanel.add(cricketButton);
        buttonPanel.add(quizButton);
        launcherFrame.add(buttonPanel, BorderLayout.CENTER);

        // Footer
        JLabel footerLabel = new JLabel("Welcome! Choose a game to start!", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        launcherFrame.add(footerLabel, BorderLayout.SOUTH);

        // Add listeners to buttons
        rpsButton.addActionListener(e -> SwingUtilities.invokeLater(RockPaperScissors::new));
        cricketButton.addActionListener(e -> SwingUtilities.invokeLater(Cricket::new));
        quizButton.addActionListener(e -> SwingUtilities.invokeLater(QuizGame::new));

        // Display the launcher
        launcherFrame.setVisible(true);
    }
   
    
}
