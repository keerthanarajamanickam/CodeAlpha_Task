package fitnessTrackerApp;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
	
	public MainFrame() {
        setTitle("Fitness Tracker App");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Add Record", new AddRecordPanel());
        tabs.addTab("Dashboard", new DashboardPanel());

        add(tabs);
    }

}
