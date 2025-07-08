package langauageLearningApp;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddFlashcardPanel extends JPanel{
	
	public AddFlashcardPanel() {
        setLayout(new GridLayout(3, 2));

        JLabel wordLabel = new JLabel("Word:");
        JTextField wordField = new JTextField();

        JLabel translationLabel = new JLabel("Translation:");
        JTextField translationField = new JTextField();
        
        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<String> categoryBox = new JComboBox<>(new String[] {"Vocabulary", "Grammar", "Phrases"});

        JButton submit = new JButton("Add Flashcard");

        submit.addActionListener(e -> {
            String word = wordField.getText();
            String translation = translationField.getText();
            String category = (String) categoryBox.getSelectedItem();


            try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO flashcards (word, translation, category) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, word);
                ps.setString(2, translation);
                ps.setString(3, category);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Flashcard added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        add(wordLabel); add(wordField);
        add(translationLabel); add(translationField);
        add(categoryLabel); add(categoryBox);
        add(new JLabel()); add(submit);
    }


}
