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

public class QuizGame{
    private int currentQuestion = 0;
    private int score = 0;

    private final String[] questions = {"Who has been the only woman president of India?",
        "What kind of chips are used in computers?",
        "When did Alexander invade India?",
        "Which Fascist leader was called 'Duce' by his followers?", 
        "Who is the only person to win a Noble prize in two different sciences?",
        "Who was the first woman to travel into space?", 
        "Eritrea, which became the 182nd member of the UN in 1993, is in the continent of", 
        "Ode to Joy the National Anthem of the European Union was originally a musical symphony of-",
        "Who invented the BALLPOINT PEN?", 
        "Which Brazilian president was impeached last year?",
        "In January 2017, Scientists at Harvard University successfully created a metallic form of which element?"};
 

    private final String[][] options = {{"Indira Gandhi", "Meera Kumar", "Sonia Gandhi", "Pratibha Patil"},
    {"Copper", "Potato", "Silicon", "Iron"},
    {"350 BC", "326 BC", "226 BC", "327 BC"},
    {"Josef Stalin", "Engelbert Dollfuss", "Adolf Hitler", "Benito Mussolini"},
    {"Marie Curie", "Albert Einstein", "Alfred Noble", "Henri Becquerel"},
    {"Sunita Williams", "Kalpana Chawla", "Valentina Tereshkova", "Yuri Gargarin"},
    {"Asia", "Africa", "Europe", "Australia"},
    {"Bach", "Beethoven", "Brahms", "Shakespeare"},
    {"Biro Brothers", "Waterman Brothers", "Bicc Brothers", "Wright Brothers"},
    {"Silas Rondeau", "Jose Dirceu", "Francisco Gomide", "Dilma Rousseff"},
    {"Chlorine", "Neon", "Hydrogen", "Xenon"}};
 

    private final int[] correctAnswers = {3,2,1,3,0,2,1,1,0,3,2}; // Correct option indices
    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizGame::new);
    }

    public QuizGame() {
        JFrame frame = new JFrame("Quiz Game");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JLabel questionLabel = new JLabel(questions[currentQuestion], JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        JRadioButton[] optionsButtons = new JRadioButton[4];
        ButtonGroup group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionsButtons[i] = new JRadioButton(options[currentQuestion][i]);
            group.add(optionsButtons[i]);
            optionsPanel.add(optionsButtons[i]);
        }
        frame.add(optionsPanel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Next");
        frame.add(nextButton, BorderLayout.SOUTH);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = -1;
                for (int i = 0; i < questions.length; i++) {
                    if (optionsButtons[i].isSelected()) {
                        selectedIndex = i;
                        break;
                    }
                }

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select an option!");
                    return;
                }

                if (selectedIndex == correctAnswers[currentQuestion]) {
                    score += 10;
                }

                currentQuestion++;
                if (currentQuestion < questions.length) {
                    questionLabel.setText(questions[currentQuestion]);
                    group.clearSelection();
                    for (int i = 0; i <questions.length ; i++) {
                        optionsButtons[i].setText(options[currentQuestion][i]);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Quiz Over! Your Score: " + score+" out of "+(questions.length*10));
                    frame.dispose();
                }
            }
        });

        frame.setVisible(true);
    }
}

