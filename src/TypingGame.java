import javax.swing.*;
import java.awt.*;

public class TypingGame extends JFrame {
    private int lvl1 = 25;
    private int lvl2 = 50;
    private int lvl3 = 75;

    public TypingGame(int width, int height) {
        try {
            ScanFile.createWords();
            ScanFile.createFonts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading the word file: " + e.getMessage());
            System.exit(1);
        }

        setTitle("Typing Game");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Display the level screen
        String[] options = {"Easy (25 words)", "Medium (50 words)", "Hard (75 words)"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose your difficulty level:",
                "Typing Game Difficulty",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        // close the game is user clicks exit button
        if (choice == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

        // user choose the level
        int wordCount = switch (choice) {
            case 0 -> lvl1; // Easy
            case 1 -> lvl2; // Medium
            case 2 -> lvl3; // Hard
            default -> lvl1; // Default to Easy
        };

        TimerDisplay timer = new TimerDisplay();
        TypingPanel panel = new TypingPanel(timer, wordCount);

        setLayout(new BorderLayout());
        add(timer, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        setVisible(true);
    }

    public TypingGame() {
        try {
            ScanFile.createWords();
            ScanFile.createFonts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading the word file: " + e.getMessage());
            System.exit(1);
        }

        setTitle("Typing Game");
        setSize(1800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // start screen where user needs to choose level
        String[] options = {"Easy (25 words)", "Medium (50 words)", "Hard (75 words)"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose your difficulty level:",
                "Typing Game Difficulty",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                null
        );

        // if user clicks the exit button, it closes
        if (choice == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

        // user chooses the level
        int wordCount = switch (choice) {
            case 0 -> lvl1; // Easy
            case 1 -> lvl2; // Medium
            case 2 -> lvl3; // Hard
            default -> lvl1; // Default to Easy
        };

        TimerDisplay timer = new TimerDisplay();
        TypingPanel panel = new TypingPanel(timer, wordCount);

        setLayout(new BorderLayout());
        add(timer, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        setVisible(true);
    }
}