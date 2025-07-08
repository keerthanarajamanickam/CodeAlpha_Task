package langauageLearningApp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
public class QuizPanel extends JPanel{
	
	private JLabel wordLabel;
    private JTextField answerField;
    private JButton checkButton;
    private JTextArea resultArea;
    private java.util.List<String[]> flashcards;
    private int currentIndex = 0;

    public QuizPanel() {
        setLayout(new BorderLayout());
        wordLabel = new JLabel("Word: ");
        answerField = new JTextField();
        checkButton = new JButton("Check");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JPanel top = new JPanel(new GridLayout(3, 1));
        top.add(wordLabel);
        top.add(answerField);
        top.add(checkButton);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        loadFlashcards();

        checkButton.addActionListener(e -> checkAnswer());
        
    }

    private void loadFlashcards() {
        flashcards = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT word, translation FROM flashcards";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                flashcards.add(new String[]{rs.getString("word"), rs.getString("translation")});
            }
            Collections.shuffle(flashcards);
            if (!flashcards.isEmpty()) {
                wordLabel.setText("Word: " + flashcards.get(0)[0]);
            }
        } catch (Exception e) {
            resultArea.setText("Error loading flashcards: " + e.getMessage());
        }
    }
    
    private void checkAnswer() {
        if (flashcards == null || flashcards.isEmpty()) return;

        String correct = flashcards.get(currentIndex)[1];
        String userAnswer = answerField.getText().trim();

        if (userAnswer.equalsIgnoreCase(correct)) {
            resultArea.append("Correct!\n");
        } else {
            resultArea.append("Incorrect. Correct answer: " + correct + "\n");
        }

        currentIndex++;
        if (currentIndex >= flashcards.size()) {
            resultArea.append("\nQuiz completed!\n");
            checkButton.setEnabled(false);
        } else {
            wordLabel.setText("Word: " + flashcards.get(currentIndex)[0]);
            answerField.setText("");
        }
    }

    


}
