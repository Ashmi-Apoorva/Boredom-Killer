/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_boredom_killer;

/**
 *
 * @author ashmi
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cricket {
    private int userScore = 0;
    private int computerScore = 0;
    private boolean userBatting = false;
    private boolean computerBatting = false;
    private boolean userCompletedBatting = false;
    private boolean computerCompletedBatting = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cricket::new);
    }

    public Cricket() {
        // Create the main frame
        JFrame frame = new JFrame("Cricket Game");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Hand Cricket Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Status area
        JTextArea statusArea = new JTextArea();
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel();
        JLabel inputLabel = new JLabel("Enter your choice: ");
        JTextField inputField = new JTextField(5);
        JButton submitButton = new JButton("Submit");
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(submitButton);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Game Introduction and Toss
        statusArea.append("Welcome to Hand Cricket!\n");
        statusArea.append("Rules:\n - Win the toss to choose Batting or Bowling.\n");
        statusArea.append(" - If your input matches the computer's number, the turn ends.\n");
        statusArea.append("Toss Time! Enter 'Heads' or 'Tails':\n");

        submitButton.addActionListener(new ActionListener() {
            private String tossResult = Math.random() < 0.5 ? "heads" : "tails";
            private boolean tossComplete = false;
            private boolean userTurnToChoose = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText().trim().toLowerCase();

                if (!tossComplete) {
                    // Handle toss logic
                    if (userInput.equals("heads") || userInput.equals("tails")) {
                        tossComplete = true;
                        if (userInput.equals(tossResult)) {
                            statusArea.append("You won the toss! Choose 'Batting' or 'Bowling':\n");
                            userTurnToChoose = true;
                        } else {
                            statusArea.append("Computer won the toss!\n");
                            if (Math.random() < 0.5) {
                                statusArea.append("Computer chose Batting.\n");
                                computerBatting = true;
                                playComputerBatting(statusArea, inputField);
                            } else {
                                statusArea.append("Computer chose Bowling.\n");
                                userBatting = true;
                                playUserBatting(statusArea, inputField);
                            }
                        }
                    } else {
                        statusArea.append("Invalid input. Please enter 'Heads' or 'Tails':\n");
                    }
                } else if (userTurnToChoose) {
                    // User chooses Batting or Bowling
                    if (userInput.equals("batting")) {
                        userTurnToChoose = false;
                        userBatting = true;
                        statusArea.append("You chose Batting. Enter a number (1-9):\n");
                    } else if (userInput.equals("bowling")) {
                        userTurnToChoose = false;
                        computerBatting = true;
                        statusArea.append("You chose Bowling. Enter a number (1-9):\n");
                        playComputerBatting(statusArea, inputField);
                    } else {
                        statusArea.append("Invalid choice. Enter 'Batting' or 'Bowling':\n");
                    }
                } else if (userBatting) {
                    playUserBatting(statusArea, inputField);
                } else if (computerBatting) {
                    playComputerBatting(statusArea, inputField);
                }

                inputField.setText("");
            }

            private void playUserBatting(JTextArea statusArea, JTextField inputField) {
                String userInput = inputField.getText().trim();
                try {
                    int userRun = Integer.parseInt(userInput);
                    if (userRun < 1 || userRun > 9) {
                        statusArea.append("Enter a number between 1 and 9.\n");
                        return;
                    }
                    int computerRun = (int) (Math.random() * 9) + 1;
                    statusArea.append("Computer bowls: " + computerRun + "\n");

                    if (userRun == computerRun) {
                        userBatting = false;
                        userCompletedBatting = true;
                        statusArea.append("You're OUT! Your score: " + userScore + "\n");
                        if (!computerCompletedBatting) {
                            computerBatting = true;
                            playComputerBatting(statusArea, inputField);
                        } else {
                            declareWinner(statusArea);
                        }
                    } else {
                        userScore += userRun;
                        statusArea.append("Your score: " + userScore + "\n");
                    }
                } catch (NumberFormatException ex) {
                    statusArea.append("Enter a number between 1 and 9.\n");
                }
            }

            private void playComputerBatting(JTextArea statusArea, JTextField inputField) {
                String userInput = inputField.getText().trim();
                try {
                    int userGuess = Integer.parseInt(userInput);
                    if (userGuess < 1 || userGuess > 9) {
                        statusArea.append("Invalid input! Enter a number between 1 and 9.\n");
                        return;
                    }
                    int computerRun = (int) (Math.random() * 9) + 1;
                    statusArea.append("Computer plays: " + computerRun + "\n");

                    if (userGuess == computerRun) {
                        computerBatting = false;
                        computerCompletedBatting = true;
                        statusArea.append("Computer is OUT! Computer's score: " + computerScore + "\n");
                        if (!userCompletedBatting) {
                            userBatting = true;
                            playUserBatting(statusArea, inputField);
                        } else {
                            declareWinner(statusArea);
                        }
                    } else {
                        computerScore += computerRun;
                        statusArea.append("Computer's score: " + computerScore + "\n");
                    }
                } catch (NumberFormatException ex) {
                    statusArea.append("Invalid input! Enter a number between 1 and 9.\n");
                }
            }

            private void declareWinner(JTextArea statusArea) {
                statusArea.append("\nFinal Scores:\n");
                statusArea.append("Your Score: " + userScore + "\n");
                statusArea.append("Computer's Score: " + computerScore + "\n");

                if (userScore > computerScore) {
                    statusArea.append("Congratulations! You WIN!\n");
                } else if (userScore < computerScore) {
                    statusArea.append("Computer Wins! Better luck next time.\n");
                } else {
                    statusArea.append("It's a TIE!\n");
                }
            }
        });

        frame.setVisible(true);
    }
}
