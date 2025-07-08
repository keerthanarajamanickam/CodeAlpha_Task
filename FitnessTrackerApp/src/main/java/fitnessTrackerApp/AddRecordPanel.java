package fitnessTrackerApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;


public class AddRecordPanel extends JPanel{
	
	public AddRecordPanel() {
        setLayout(new GridLayout(6, 2));

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField();

        JLabel stepsLabel = new JLabel("Steps:");
        JTextField stepsField = new JTextField();

        JLabel workoutLabel = new JLabel("Workout Time (min):");
        JTextField workoutField = new JTextField();

        JLabel caloriesLabel = new JLabel("Calories Burned:");
        JTextField caloriesField = new JTextField();

        JButton submit = new JButton("Add Record");

        submit.addActionListener(e -> {
            try {
                Date date = Date.valueOf(dateField.getText());
                int steps = Integer.parseInt(stepsField.getText());
                int workout = Integer.parseInt(workoutField.getText());
                int calories = Integer.parseInt(caloriesField.getText());

                try (Connection conn = DBConnection.getConnection()) {
                    String sql = "INSERT INTO fitness_records (date, steps, workout_time, calories) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setDate(1, date);
                    ps.setInt(2, steps);
                    ps.setInt(3, workout);
                    ps.setInt(4, calories);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Record added successfully!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        add(dateLabel); add(dateField);
        add(stepsLabel); add(stepsField);
        add(workoutLabel); add(workoutField);
        add(caloriesLabel); add(caloriesField);
        add(new JLabel()); add(submit);
    }

}
