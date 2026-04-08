package gui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class ToDoApp {

    public static void main(String[] args) {
        // Essential: Run on the EDT
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Tasks");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            
            setupUI(frame); // Move logic to a helper method
            
            frame.setLocationRelativeTo(null); // Center on screen
            frame.setVisible(true);
        });
    }

    private static void setupUI(JFrame frame) {

        // 1. Create Components
        JPanel panel = new JPanel(new BorderLayout(10, 10)); // 10px gaps
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(listModel);
        
        JTextField inputField = new JTextField();
        inputField.setColumns(20); //sets the length of the text field

        JButton addButton = new JButton("Add Task");

        // Color color[] = {Color.RED, Color.BLUE, Color.YELLOW, Color.ORANGE};

        
        // 2. Add Logic (Event Handling)
        addButton.addActionListener(e -> {
            String task = inputField.getText().trim();
            if (!task.isEmpty()) {
                listModel.addElement(task);
                inputField.setText(""); // Clear input
            } else {
                // Simple popup utility
                JOptionPane.showMessageDialog(frame, "Please enter a task!");
            }

            // addButton.setBackground(color[(int)Math.floor(Math.random()*color.length)]);
        });

        // JButton removeButton = new JButton("Remove Task");

        // removeButton.addActionListener(e -> {
        //     listModel.remove(taskList.getSelectedIndex());
        // });

        // 3. Assemble the UI
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        // inputPanel.add(removeButton);

        panel.add(new JLabel("Your Tasks for Today:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER); // Wrap list in scroll pane
        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(panel);

        // addButton.setContentAreaFilled(false);
        // addButton.setOpaque(true);
        addButton.setBackground(Color.RED);
        // addButton.setForeground(Color.WHITE);
    }

}
