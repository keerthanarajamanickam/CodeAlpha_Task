package flashCardQuizApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class FlashcardAppGUI extends JFrame{
	
	private ArrayList<FlashCard> flashcards;
    private int currentIndex = 0;
    private final String FILE_NAME = "flashcards.dat";

    private JLabel questionLabel = new JLabel("Question:");
    private JTextArea questionArea = new JTextArea(3, 20);
    private JButton showAnswerButton = new JButton("Show Answer");
    private JTextArea answerArea = new JTextArea(3, 20);
    private JButton nextButton = new JButton("Next");
    private JButton prevButton = new JButton("Previous");
    private JButton addButton = new JButton("Add");
    private JButton editButton = new JButton("Edit");
    private JButton deleteButton = new JButton("Delete");

    public FlashcardAppGUI() {
        setTitle("Flashcard Quiz App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        flashcards = loadFlashcards();

        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        questionArea.setLineWrap(true);
        answerArea.setLineWrap(true);
        answerArea.setEditable(false);

        centerPanel.add(questionLabel);
        centerPanel.add(new JScrollPane(questionArea));
        centerPanel.add(showAnswerButton);
        centerPanel.add(new JScrollPane(answerArea));

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(prevButton);
        bottomPanel.add(nextButton);
        bottomPanel.add(addButton);
        bottomPanel.add(editButton);
        bottomPanel.add(deleteButton);

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        showAnswerButton.addActionListener(e -> showAnswer());
        nextButton.addActionListener(e -> nextCard());
        prevButton.addActionListener(e -> prevCard());
        addButton.addActionListener(e -> addCard());
        editButton.addActionListener(e -> editCard());
        deleteButton.addActionListener(e -> deleteCard());

        updateCardDisplay();
        setVisible(true);
    }

    private void showAnswer() {
        if (!flashcards.isEmpty()) {
            answerArea.setText(flashcards.get(currentIndex).getAnswer());
        }
    }

    private void nextCard() {
        if (!flashcards.isEmpty()) {
            currentIndex = (currentIndex + 1) % flashcards.size();
            updateCardDisplay();
        }
    }

    private void prevCard() {
        if (!flashcards.isEmpty()) {
            currentIndex = (currentIndex - 1 + flashcards.size()) % flashcards.size();
            updateCardDisplay();
        }
    }

    private void addCard() {
        String question = JOptionPane.showInputDialog(this, "Enter question:");
        if (question == null || question.trim().isEmpty()) return;
        String answer = JOptionPane.showInputDialog(this, "Enter answer:");
        if (answer == null || answer.trim().isEmpty()) return;

        flashcards.add(new FlashCard(question, answer));
        currentIndex = flashcards.size() - 1;
        updateCardDisplay();
        saveFlashcards();
    }

    private void editCard() {
        if (flashcards.isEmpty()) return;
        FlashCard current = flashcards.get(currentIndex);
        String newQuestion = JOptionPane.showInputDialog(this, "Edit question:", current.getQuestion());
        if (newQuestion == null) return;
        String newAnswer = JOptionPane.showInputDialog(this, "Edit answer:", current.getAnswer());
        if (newAnswer == null) return;

        current.setQuestion(newQuestion);
        current.setAnswer(newAnswer);
        updateCardDisplay();
        saveFlashcards();
    }

    private void deleteCard() {
        if (flashcards.isEmpty()) return;
        flashcards.remove(currentIndex);
        if (currentIndex >= flashcards.size()) {
            currentIndex = flashcards.size() - 1;
        }
        updateCardDisplay();
        saveFlashcards();
    }

    private void updateCardDisplay() {
        if (flashcards.isEmpty()) {
            questionArea.setText("");
            answerArea.setText("");
        } else {
            FlashCard current = flashcards.get(currentIndex);
            questionArea.setText(current.getQuestion());
            answerArea.setText("");
        }
    }

    private ArrayList<FlashCard> loadFlashcards() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<FlashCard>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveFlashcards() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(flashcards);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving flashcards.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlashcardAppGUI());
    }

}
