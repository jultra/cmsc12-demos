package thread;

import javax.swing.*;
import java.awt.*;

public class MultiThreadedCounter extends JFrame {
    private JLabel countLabel;
    private JButton startBtn, stopBtn;
    private volatile boolean running = false; // 'volatile' ensures visibility across threads
    private int count = 0;

    public MultiThreadedCounter() {
        setTitle("The 'Fixed' Multi-Threaded Demo");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        countLabel = new JLabel("Count: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 30));
        
        startBtn = new JButton("Start (New Thread)");
        stopBtn = new JButton("Stop (Responsive)");

        // 1. Start Button: Launches a background worker
        startBtn.addActionListener(e -> startCountingInBackground());

        // 2. Stop Button: Changes the flag; the background thread will see this
        stopBtn.addActionListener(e -> {
            running = false;
            System.out.println("Stop clicked! Flag set to false.");
        });

        add(countLabel);
        add(startBtn);
        add(stopBtn);
        setVisible(true);
    }

    private void startCountingInBackground() {
        if (running) return; // Prevent spawning multiple counter threads
        running = true;

        // SOLUTION: Create a new Thread to handle the loop
        Thread worker = new Thread(new Counter());

        worker.start(); // This tells the OS to actually begin the new thread
    }

    class Counter implements Runnable{

            @Override
            public void run(){
            while (running) {
                count++;
                
                // IMPORTANT: Swing is NOT thread-safe. 
                // We use invokeLater to ask the UI thread to update the label for us.
                SwingUtilities.invokeLater(
                    // () -> 
                    // countLabel.setText("Count: " + count)
                    new Runnable(){

                        @Override
                        public void run(){
                            countLabel.setText("Count: " + count);
                        }
                    }
                );

                try {
                    Thread.sleep(1000); // Only the worker thread sleeps; UI stays awake!
                } catch (InterruptedException ex) {
                    break; 
                }
                
                System.out.println("Worker Thread Count: " + count);
            }
            System.out.println("Worker Thread Finished.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MultiThreadedCounter());
    }
}

