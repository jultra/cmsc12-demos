package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class DashboardGUI {
    
    public static void main(String args[]){
        JFrame frame = new JFrame("System Dashboard");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent mainPanel = (JComponent)frame.getContentPane();
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel();
        JLabel dashboardTitle = new JLabel("System Dashboard");
        JButton logoutButton = new JButton("Logout");
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        topPanel.setLayout(new BorderLayout());
        topPanel.add(dashboardTitle, BorderLayout.WEST);
        topPanel.add(logoutButton, BorderLayout.EAST);

        JPanel leftPanel = new JPanel();
        // leftPanel.setLayout(new GridLayout(10, 1));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        // leftPanel.setBorder(new TitledBorder("Navigation"));
        //leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 20));
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border titleBorder = BorderFactory.createTitledBorder("Navigation");

        leftPanel.setBorder(BorderFactory.createCompoundBorder(titleBorder, padding));
        
        for(int i = 0; i < 4; i++){
            JButton nav = new JButton("Nav " + (i+1));
            leftPanel.add(nav);
        }

        JPanel bottomPanel = new JPanel();

        bottomPanel.setLayout(new BorderLayout());

        JPanel bottomRightPanel = new JPanel();
    

        bottomPanel.add(new JLabel("Status: System Ready"), BorderLayout.WEST);
        bottomRightPanel.add(new JButton("Save"));
        bottomRightPanel.add(new JButton("X"));

        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        
        JTextArea textArea = new JTextArea(10, 20);
        centerPanel.add(textArea, BorderLayout.CENTER);
        JPanel bottomCenterPanel = new JPanel();


        // for(int i = 0; i < 3; i++){
        //     JLabel label = new JLabel("--data--");
        //     label.setBorder(new TitledBorder("Stat A"));
        //     bottomCenterPanel.add(label);
        // }

        bottomCenterPanel.setLayout(new GridLayout(1, 3));

        JLabel label1 = createStatLabel("Stat A");
        bottomCenterPanel.add(label1);
        centerPanel.add(bottomCenterPanel, BorderLayout.SOUTH);

        JLabel label2 = createStatLabel("Stat B");
        bottomCenterPanel.add(label2);
        JLabel label3 = createStatLabel("Stat C");
        bottomCenterPanel.add(label3);

        centerPanel.add(bottomCenterPanel, BorderLayout.SOUTH);
        centerPanel.add(bottomCenterPanel, BorderLayout.SOUTH);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.pack();
    }

    public static JLabel createStatLabel(String title){
        JLabel label1 = new JLabel("--data--");
        label1.setBorder(new TitledBorder(title));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        return label1;
    }
}
