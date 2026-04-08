package thread;

import javax.swing.*;
import java.awt.*;

public class BrokenCounter extends JFrame {
    private JLabel countLabel;
    private JButton startBtn, stopBtn;
    private boolean running = false;
    private int count = 0;

    public BrokenCounter() {
        // 1. Basic GUI Setup
        setTitle("The 'Broken' Single-Threaded Demo");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        countLabel = new JLabel("Count: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 30));
        
        startBtn = new JButton("Start (This will freeze)");
        stopBtn = new JButton("Stop (This won't work)");

        // 2. The Logic: This runs on the Main UI Thread
        startBtn.addActionListener(e -> {
            running = true;
            startCounting();
        });

        stopBtn.addActionListener(e -> {
            running = false; // This line is never reached!
            System.out.println("Stop clicked!"); 
        });

        add(countLabel);
        add(startBtn);
        add(stopBtn);
        setVisible(true);
        System.out.println("Inside thread " + Thread.currentThread().getName());
    }

    private void startCounting() {
        // // ERROR: This loop hijacks the UI Thread (the EDT).
        // // While the code is in this loop, it CANNOT listen for the Stop button click.
        while (running) {
            count++;
            countLabel.setText("Count: " + count);
            
            try {
                // The thread "sleeps," but since it's the UI thread, 
                // the whole window "sleeps" with it.
                Thread.sleep(1000); 
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            // This print shows the code is working in the background, 
            // even though the GUI is frozen.
            System.out.println("Current Count: " + count);
        }
        // Runnable counter = new Counter();
        // Thread counterThread = new Thread(counter);
        // counterThread.start();
    }

    class Counter implements Runnable {
        @Override
        public void run(){
            while (running) {
                count++;
                // countLabel.setText("Count: " + count);
                // SwingUtilities.invokeLater(new Runnable(){
                //     @Override
                //     public void run(){
                //         countLabel.setText("Count: " + count);
                //     }
                // });
                SwingUtilities.invokeLater(() -> countLabel.setText("Count: " + count));
                
                try {
                    // The thread "sleeps," but since it's the UI thread, 
                    // the whole window "sleeps" with it.
                    Thread.sleep(1000); 
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                
                // This print shows the code is working in the background, 
                // even though the GUI is frozen.
                System.out.println("Current Count: " + count);
            }
        }
    }

    public static void main(String[] args) {
        // Standard way to launch a Swing app
        SwingUtilities.invokeLater(() -> new BrokenCounter());
        //new BrokenCounter();
    }
}
