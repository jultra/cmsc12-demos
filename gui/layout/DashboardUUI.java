package gui.layout;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;


public class DashboardUUI {
    public static void main(String args[]){
        JFrame frame = new JFrame("Dashboard");
        frame.setSize(800, 500);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));


        JPanel northPanel = new JPanel(new BorderLayout(10, 10));
        northPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        JLabel label = new JLabel("System Dashboard Title");
        northPanel.add(label, BorderLayout.WEST);

        JButton logoutButton = new JButton("Logout");
        northPanel.add(logoutButton, BorderLayout.EAST);

        frame.add(northPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        leftPanel.setBorder(new TitledBorder("Navigation"));
        leftPanel.add(new JButton("Nav 1"));
        leftPanel.add(new JButton("Nav 2"));
        leftPanel.add(new JButton("Nav 3"));    
        leftPanel.add(new JButton("Nav 4"));
        frame.add(leftPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        JTextArea textArea = new JTextArea("This is my text area.", 20, 40);
        centerPanel.add(textArea, BorderLayout.CENTER);

        JPanel bottomCenterPanel = new JPanel(new GridLayout(1, 3, 5, 5));

        // JPanel statPanel = new JPanel();
        // statPanel.setBorder(new TitledBorder("Stat A"));
        // statPanel.add(new JLabel("-- Data --"));

        bottomCenterPanel.add(createStatPanel("Stat A"));
        bottomCenterPanel.add(createStatPanel("Stat B"));
        bottomCenterPanel.add(createStatPanel("Stat C"));
        centerPanel.add(bottomCenterPanel, BorderLayout.SOUTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.add(new JLabel("Status: System Ready"), BorderLayout.WEST);

        JPanel bottomRightPanel = new JPanel();

        bottomRightPanel.add(new JButton("Save"));
        bottomRightPanel.add(new JButton("X"));

        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);


        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

        frame.revalidate();
        frame.repaint();


    }

    public static JPanel createStatPanel(String title){
        JPanel statPanel = new JPanel();
        statPanel.setBorder(new TitledBorder(title));
        statPanel.add(new JLabel("-- Data --"));

        return statPanel;
    }
}
