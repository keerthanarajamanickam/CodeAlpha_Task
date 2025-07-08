package langauageLearningApp;

import javax.swing.*;
import java.awt.*;

public class LessonPanel extends JPanel{
	
	public LessonPanel() {
        setLayout(new BorderLayout());

        JTextArea lessonArea = new JTextArea();
        lessonArea.setEditable(false);
        lessonArea.setText("Lesson 1: Basic Greetings\n\nHello = Hola\nGood Morning = Buenos días\nGood Night = Buenas noches\nHow are you? = ¿Cómo estás?\nI'm fine = Estoy bien\n\nPractice saying them out loud!");

        add(new JScrollPane(lessonArea), BorderLayout.CENTER);
    }

}
