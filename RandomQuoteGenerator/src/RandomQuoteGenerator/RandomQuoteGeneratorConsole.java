package RandomQuoteGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class RandomQuoteGeneratorConsole extends JFrame{
	
	private static final String[][] quotes = {
	        {"The only limit to our realization of tomorrow is our doubts of today.", "Franklin D. Roosevelt"},
	        {"Do not wait for leaders; do it alone, person to person.", "Mother Teresa"},
	        {"In the middle of difficulty lies opportunity.", "Albert Einstein"},
	        {"Success is not final, failure is not fatal: It is the courage to continue that counts.", "Winston Churchill"},
	        {"What you do today can improve all your tomorrows.", "Ralph Marston"}
	    };

	    private JLabel quoteLabel;
	    private JLabel authorLabel;
	    private JButton newQuoteButton;
	    private Random random;

	    public RandomQuoteGeneratorConsole() {
	        setTitle("Random Quote Generator");
	        setSize(500, 200);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setLayout(new BorderLayout(10, 10));

	        random = new Random();

	        // Quote display panel
	        JPanel quotePanel = new JPanel();
	        quotePanel.setLayout(new BoxLayout(quotePanel, BoxLayout.Y_AXIS));
	        quoteLabel = new JLabel("", SwingConstants.CENTER);
	        quoteLabel.setFont(new Font("Serif", Font.PLAIN, 16));
	        authorLabel = new JLabel("", SwingConstants.CENTER);
	        authorLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
	        quotePanel.add(quoteLabel);
	        quotePanel.add(Box.createRigidArea(new Dimension(0, 5)));
	        quotePanel.add(authorLabel);
	        quotePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

	        // Button panel
	        newQuoteButton = new JButton("New Quote");
	        newQuoteButton.setFocusPainted(false);
	        newQuoteButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                displayRandomQuote();
	            }
	        });

	        JPanel buttonPanel = new JPanel();
	        buttonPanel.add(newQuoteButton);

	        add(quotePanel, BorderLayout.CENTER);
	        add(buttonPanel, BorderLayout.SOUTH);

	        displayRandomQuote(); // Show a random quote on launch
	        setVisible(true);
	    }

	    private void displayRandomQuote() {
	        int index = random.nextInt(quotes.length);
	        quoteLabel.setText("<html><div style='text-align: center;'>" + quotes[index][0] + "</div></html>");
	        authorLabel.setText("- " + quotes[index][1]);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new RandomQuoteGeneratorConsole());
	    }

}
