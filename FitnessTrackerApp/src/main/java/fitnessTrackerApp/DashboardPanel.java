package fitnessTrackerApp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class DashboardPanel extends JPanel{
	
	private JTextArea summaryArea;

    public DashboardPanel() {
        setLayout(new BorderLayout());
        summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        add(new JScrollPane(summaryArea), BorderLayout.CENTER);
        loadSummary();
    }

    private void loadSummary() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT date, steps, workout_time, calories FROM fitness_records ORDER BY date DESC LIMIT 7";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            StringBuilder sb = new StringBuilder("Last 7 Days:\n\n");
            while (rs.next()) {
                sb.append(rs.getDate("date"))
                  .append(" - Steps: ").append(rs.getInt("steps"))
                  .append(", Workout: ").append(rs.getInt("workout_time"))
                  .append(" mins, Calories: ").append(rs.getInt("calories"))
                  .append("\n");
            }
            summaryArea.setText(sb.toString());
        } catch (Exception ex) {
            summaryArea.setText("Error loading summary: " + ex.getMessage());
        }
    }

}
