/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_boredom_killer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author ashmi
 */
public class RockPaperScissors {
    private int userScore = 0;
    private int computerScore = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RockPaperScissors::new);
    }

    public RockPaperScissors() {
        // Create the main frame
        JFrame frame = new JFrame("Rock-Paper-Scissors Game");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Game Title
        JLabel titleLabel = new JLabel("Rock-Paper-Scissors", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Buttons for user input
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Result display
        JLabel resultLabel = new JLabel("Choose Rock, Paper, or Scissors!", JLabel.CENTER);
        frame.add(resultLabel, BorderLayout.SOUTH);

        // Action listeners for buttons
        ActionListener buttonListener = e -> {
            String userChoice = e.getActionCommand().toLowerCase();
            String computerChoice = getRandomChoice();
            int result = determineWinner(userChoice, computerChoice);

            // Update scores and result
            if (result > 0) {
                userScore += 10;
                resultLabel.setText("You Win! Computer chose: " + computerChoice);
            } else if (result < 0) {
                computerScore += 10;
                resultLabel.setText("You Lose! Computer chose: " + computerChoice);
            } else {
                resultLabel.setText("It's a Tie! Computer also chose: " + computerChoice);
            }

            // Update frame title with scores
            frame.setTitle("Rock-Paper-Scissors | You: " + userScore + " | Computer: " + computerScore);
        };

        // Attach listeners to buttons
        rockButton.addActionListener(buttonListener);
        paperButton.addActionListener(buttonListener);
        scissorsButton.addActionListener(buttonListener);

        // Display the frame
        frame.setVisible(true);
    }

    private String getRandomChoice() {
        String[] choices = {"rock", "paper", "scissors"};
        int index = (int) (Math.random() * choices.length);
        return choices[index];
    }

    private int determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) return 0; // Tie
        if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
            (userChoice.equals("scissors") && computerChoice.equals("paper")) ||
            (userChoice.equals("paper") && computerChoice.equals("rock"))) {
            return 1; // User wins
        }
        return -1; // Computer wins
    }
}
    
