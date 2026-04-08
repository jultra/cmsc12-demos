package thread;

import javax.swing.*;
import java.awt.*;

public class ManualCounter extends JFrame {
    private JLabel countLabel;
    private JButton incrementBtn, resetBtn;
    private int count = 0;

    public ManualCounter() {
        // 1. Basic GUI Setup
        setTitle("Manual Counter (No Threads Needed)");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        countLabel = new JLabel("Count: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 30));
        
        incrementBtn = new JButton("Click to Increment");
        resetBtn = new JButton("Reset");

        // 2. Logic: These events are near-instant, so the UI won't freeze.
        incrementBtn.addActionListener(e -> {
            count++;
            countLabel.setText("Count: " + count);
            System.out.println("Button clicked! Count is now: " + count);
        });

        resetBtn.addActionListener(e -> {
            count = 0;
            countLabel.setText("Count: 0");
        });

        add(countLabel);
        add(incrementBtn);
        add(resetBtn);
        setVisible(true);
        System.out.println("Inside " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        // Run on the Event Dispatch Thread (standard practice)
        SwingUtilities.invokeLater(() -> new ManualCounter());
    }
}

