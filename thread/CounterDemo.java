package thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterDemo extends JFrame {
    private JLabel countLabel;
    private JButton startBtn, stopBtn;
    private volatile boolean running = false; // Flag to control the thread
    private int count = 0;

    public CounterDemo() {
        // 1. Setup the GUI
        setTitle("Multithreading Demo");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        countLabel = new JLabel("Count: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        startBtn = new JButton("Start");
        stopBtn = new JButton("Stop");

        // 2. Start Button Logic
        startBtn.addActionListener(e -> startCounting());

        // 3. Stop Button Logic
        stopBtn.addActionListener(e -> running = false);

        add(countLabel);
        add(startBtn);
        add(stopBtn);
        setVisible(true);
    }

    private void startCounting() {
        if (running) return; // Don't start multiple threads
        running = true;

        // Launch a new thread so the UI doesn't freeze
        new Thread(() -> {
            while (running) {
                count++;
                
                // Swing components MUST be updated on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> 
                    countLabel.setText("Count: " + count)
                );

                try {
                    Thread.sleep(1000); // Wait for 1 second
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CounterDemo::new);
    }
}
