package gui.layout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DashboardUI extends JFrame {

    public DashboardUI() {
        // 1. Main Frame Setup (Root uses BorderLayout)
        setTitle("System Dashboard - Layout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout(10, 10)); // Added gaps for breathing room

        // 2. Top Panel (BorderLayout: Title on Left, Logout on Right)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("  System Dashboard Title", JLabel.LEFT), BorderLayout.WEST);
        topPanel.add(new JButton("Logout"), BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        add(topPanel, BorderLayout.NORTH);

        // 3. Sidebar Panel (GridLayout: Vertical stack of buttons)
        JPanel sidebar = new JPanel(new GridLayout(10, 1, 5, 5));
        sidebar.setBorder(new TitledBorder("Navigation"));
        sidebar.add(new JButton("Nav 1"));
        sidebar.add(new JButton("Nav 2"));
        sidebar.add(new JButton("Nav 3"));
        sidebar.add(new JButton("Nav 4"));
        add(sidebar, BorderLayout.WEST);

        // 4. Center Area (Nesting: Text Area at Top, Stats at Bottom)
        JPanel centerContainer = new JPanel(new BorderLayout(0, 10));
        
        // Main Content Area
        JTextArea mainContent = new JTextArea("Main Content Area (Type notes here...)");
        centerContainer.add(new JScrollPane(mainContent), BorderLayout.CENTER);

        // Stats Grid (GridLayout: 3 equal boxes)
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        statsPanel.add(createStatBox("Stat A"));
        statsPanel.add(createStatBox("Stat B"));
        statsPanel.add(createStatBox("Stat C"));
        centerContainer.add(statsPanel, BorderLayout.SOUTH);
        
        add(centerContainer, BorderLayout.CENTER);

        // 5. Bottom Panel (BorderLayout: Status on Left, Buttons on Right)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(new JLabel(" Status: System Ready"), BorderLayout.WEST);

        // Buttons Panel (FlowLayout: Groups Save and X buttons)
        JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomButtons.add(new JButton("Save"));
        bottomButtons.add(new JButton("X"));
        bottomPanel.add(bottomButtons, BorderLayout.EAST);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Helper method to create a decorative stat box
    private JPanel createStatBox(String title) {
        JPanel p = new JPanel();
        p.setBorder(new TitledBorder(title));
        p.add(new JLabel("-- Data --"));
        return p;
    }

    // private JLabel createStatBox(String title) {
    //     JPanel p = new JPanel();
    //     p.setBorder(new TitledBorder(title));
    //     p.add(new JLabel("-- Data --"));
    //     return new JLabel("-- Data --");
    // }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardUI().setVisible(true);
        });
    }
}
