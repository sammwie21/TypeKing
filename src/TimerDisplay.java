import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class TimerDisplay extends JPanel {
    private long startTime;
    private boolean running;
    private JLabel timerLabel;

    // starting screen for timer
    public TimerDisplay() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.LIGHT_GRAY);
        timerLabel = new JLabel("Time: 0.00 seconds");
        timerLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        add(timerLabel);
    }

    public TimerDisplay(int size) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.LIGHT_GRAY);
        timerLabel = new JLabel("Time: 0.00 seconds");
        timerLabel.setFont(new Font("Times New Roman", Font.BOLD, size));
        add(timerLabel);
    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
        running = true;
        new Timer(0, e -> updateTimer()).start();
    }

    public void stopTimer() {
        running = false;
    }

    // updates the timer so it shows in milliseconds
    private void updateTimer() {
        if (running) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            double seconds = elapsedTime / 1000.0;
            DecimalFormat df = new DecimalFormat("0.00");
            timerLabel.setText("Time: " + df.format(seconds) + " seconds");
        }
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }
}