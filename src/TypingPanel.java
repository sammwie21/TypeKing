import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TypingPanel extends JPanel {
    private String[] currentWords;
    private String[] typedWords;
    private int currentWordIndex = 0;
    private boolean gameStarted = false;
    private TimerDisplay timerDisplay;
    private String randFont = "Arial"; // Default font

    public TypingPanel(TimerDisplay timerDisplay, int wordCount) {
        this.timerDisplay = timerDisplay;

        currentWords = new String[wordCount];
        typedWords = new String[wordCount];
        Random random = new Random();

        // add words to currentWords from words.txt
        for (int i = 0; i < wordCount; i++) {
            currentWords[i] = ScanFile.words.get(random.nextInt(ScanFile.words.size()));
            typedWords[i] = "";
        }

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!gameStarted) {
                    timerDisplay.startTimer();
                    gameStarted = true;
                }

                char typedChar = e.getKeyChar();
                if (Character.isLetterOrDigit(typedChar)) {
                    if (typedWords[currentWordIndex].length() < currentWords[currentWordIndex].length()) {
                        typedWords[currentWordIndex] += typedChar;

                        // check if typed letter is correct
                        int typedLength = typedWords[currentWordIndex].length();
                        if (typedLength <= currentWords[currentWordIndex].length() &&
                                typedWords[currentWordIndex].charAt(typedLength - 1) != currentWords[currentWordIndex].charAt(typedLength - 1)) {
                            // change the font when a wrong letter is typed
                            randFont = ScanFile.fonts.get(random.nextInt(ScanFile.fonts.size()));
                        }

                        repaint();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !typedWords[currentWordIndex].isEmpty()) {
                    // Remove the last character
                    typedWords[currentWordIndex] = typedWords[currentWordIndex].substring(0, typedWords[currentWordIndex].length() - 1);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // Check if the word matches
                    if (typedWords[currentWordIndex].equals(currentWords[currentWordIndex])) {
                        currentWordIndex++;
                        if (currentWordIndex >= currentWords.length) {
                            timerDisplay.stopTimer();
                            long elapsedTime = timerDisplay.getElapsedTime(); // in milliseconds
                            double seconds = elapsedTime / 1000.0;
                            double wps = (double) currentWords.length / seconds;
                            double wpm = wps * 60;
                            wpm = Math.round(wpm * 100.0) / 100.0;
                            JOptionPane.showMessageDialog(null,
                                    String.format("Congratulations! You finished in %.2f seconds.\nYour WPM: %.2f", seconds, wpm));
                            System.exit(0);
                        }
                        typedWords[currentWordIndex] = "";
                        repaint();
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font(randFont, Font.PLAIN, 16));

        int x = 10, y = 50;
        int wordSpacing = 250, lineHeight = 40;
        int wordsPerRow = getWidth() / wordSpacing;

        for (int i = 0; i < currentWords.length; i++) {
            if (i % wordsPerRow == 0 && i > 0) {
                x = 10;
                y += lineHeight;
            }

            g.setColor(Color.LIGHT_GRAY);
            g.drawString(currentWords[i], x, y);

            // drawing the letters typed
            if (!typedWords[i].isEmpty()) {
                for (int j = 0; j < typedWords[i].length(); j++) {
                    char typedChar = typedWords[i].charAt(j);
                    if (j < currentWords[i].length() && typedChar == currentWords[i].charAt(j)) {
                        g.setColor(Color.BLACK);
                    } else {
                        g.setColor(Color.RED);
                        g.setFont(new Font(randFont, Font.PLAIN, 16)); // update the font
                    }
                    g.drawString(String.valueOf(typedChar), x + g.getFontMetrics().stringWidth(currentWords[i].substring(0, j)), y);
                }
            }

            if (i == currentWordIndex) {
                g.setColor(Color.GREEN);
                g.drawRect(x - 5, y - 20, wordSpacing, 25);
            }

            x += wordSpacing;
        }
    }
}