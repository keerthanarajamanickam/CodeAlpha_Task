package langauageLearningApp;

import javax.swing.*;
public class MainFrame extends JFrame{
	
	public MainFrame() {
        setTitle("Language Learning App");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Add Flashcard", new AddFlashcardPanel());
        tabs.addTab("Quiz", new QuizPanel());
        tabs.addTab("Lessons", new LessonPanel());

        add(tabs);
    }

}
