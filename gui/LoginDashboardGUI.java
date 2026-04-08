package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class LoginDashboardGUI extends JFrame{

    public final static String LOGIN_PANEL = "login";
    public final static String DASHBOARD_PANEL = "dashboard";

    public static JPanel getDashboard(Container parent, CardLayout cardLayout){
        JPanel dashboardPanel = new JPanel();
        
        // JMenuBar menuBar = new JMenuBar();
        // JMenu fileMenu = new JMenu("File");
        // JMenuItem newFile = new JMenuItem("New File");
        // JMenuItem newWindow = new JMenuItem("New Window");
        // fileMenu.add(newFile);
        // fileMenu.add(newWindow);
        // menuBar.add(fileMenu);
        // menuBar.add(new JMenu("Edit"));
        // menuBar.add(new JMenu("Selection"));
    
        // dashboardPanel.setJMenuBar(menuBar);


        dashboardPanel.setLayout(new BorderLayout(10, 10));
        dashboardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel dashboardTitle = new JLabel("System Dashboard");
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(parent, LoginDashboardGUI.LOGIN_PANEL);
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEtchedBorder());

        JPanel topCenterPanel = new JPanel();
        topCenterPanel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 20));
        topCenterPanel.setLayout(new BorderLayout());
        topCenterPanel.add(new JTextField(), BorderLayout.CENTER);
        JButton searchButton = new JButton("");
        
        ImageIcon icon = new ImageIcon("./bin/search.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        searchButton.setIcon(new ImageIcon(image));

        topCenterPanel.add(searchButton, BorderLayout.EAST);

        topPanel.add(topCenterPanel, BorderLayout.CENTER);
        topPanel.add(dashboardTitle, BorderLayout.WEST);
        topPanel.add(logoutButton, BorderLayout.EAST);
        // frame.add(dashboardTitle, BorderLayout.WEST);
        // frame.add(logoutButton, BorderLayout.EAST);


        JPanel leftPanel = new JPanel();
        //leftPanel.setBorder(new TitledBorder("Navigation"));
        Border titledBorder =  BorderFactory.createTitledBorder("Navigation");
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        leftPanel.setBorder(BorderFactory.createCompoundBorder(titledBorder, paddingBorder));

        // leftPanel.setLayout(new GridLayout(0, 1));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        for(int i = 0; i < 4; i++){
            JButton nav = new JButton("Nav " + (i+1));
            nav.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftPanel.add(nav);
            leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        leftPanel.add(Box.createVerticalGlue());

        JPanel specialButtonsPanel = new JPanel();
        specialButtonsPanel.setLayout(new BoxLayout(specialButtonsPanel, BoxLayout.Y_AXIS));
        Border bevel = BorderFactory.createRaisedBevelBorder();
        specialButtonsPanel.setBorder(BorderFactory.createCompoundBorder(bevel, paddingBorder));

        for(int i = 0; i < 4; i++){
            JButton nav = new JButton("Special Button " + (i+1));
            nav.setAlignmentX(Component.CENTER_ALIGNMENT);
            specialButtonsPanel.add(nav);
            specialButtonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        leftPanel.add(specialButtonsPanel);



        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea("This is a sentence", 10, 20);
        centerPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel centerBottomPanel = new JPanel();
        // JPanel statPanel = new JPanel();
        // statPanel.add(new JLabel("--Data--"));
        // centerBottomPanel.add(statPanel);
        centerBottomPanel.setLayout(new GridLayout(1, 3));

        centerBottomPanel.add(createStatPanel("Statistics"));
        centerBottomPanel.add(createStatPanel("Statistics"));
        centerBottomPanel.add(createStatPanel("Statistics"));

        // centerBottomPanel.add(new JLabel("--Data--"));
        // centerBottomPanel.add(new JLabel("--Data--"));
        // centerBottomPanel.add(new JLabel("--Data--"));
        centerPanel.add(centerBottomPanel, BorderLayout.SOUTH);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        bottomPanel.add(new JLabel("Status: System Ready"), BorderLayout.WEST);
        // bottomPanel.add(new JButton("Save"), BorderLayout.EAST);
        // bottomPanel.add(new JButton("X"), BorderLayout.EAST);

        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.add(new JButton("Save"));
        bottomRightPanel.add(new JButton("X"));
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);

        dashboardPanel.add(bottomPanel, BorderLayout.SOUTH);
        dashboardPanel.add(centerPanel, BorderLayout.CENTER);
        dashboardPanel.add(leftPanel, BorderLayout.WEST);
        dashboardPanel.add(topPanel, BorderLayout.NORTH);
        // dashboardPanel.pack();
        dashboardPanel.revalidate();
        dashboardPanel.repaint();

        textArea.requestFocusInWindow();
        textArea.setCaretPosition(textArea.getDocument().getLength());

        return dashboardPanel;
    }

    public static JPanel getLoginPanel(Container parent, CardLayout cardLayout){
        JPanel loginForm = new JPanel();

        // loginPanel.setLayout(new GridLayout(6, 1));
        
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        loginForm.setBorder(BorderFactory.createCompoundBorder(etchedBorder, padding));


        GridBagConstraints gbc = new GridBagConstraints();
        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password");
        JTextField passwordField = new JTextField(20);
        JLabel spacerLabel = new JLabel("");
        JButton loginButton = new JButton("Login");

        
        loginForm.setLayout(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginForm.add(usernameLabel, gbc);

        gbc.gridy = 1;
        loginForm.add(usernameField, gbc);

        gbc.gridy = 2;
        loginForm.add(passwordLabel, gbc);

        gbc.gridy = 3;
        loginForm.add(passwordField, gbc);

        gbc.gridy = 4;
        loginForm.add(spacerLabel, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 5;
        loginForm.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                cardLayout.show(parent, LoginDashboardGUI.DASHBOARD_PANEL);
                JFrame frame = (JFrame)loginButton.getTopLevelAncestor();
                frame.setMinimumSize(new Dimension(800, 600));
            }
        });

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.add(loginForm);

        return loginPanel;
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {

                JFrame frame = new JFrame("System Dashboard");
                frame.setSize(800, 600);
                CardLayout cardLayout = new CardLayout();
                frame.setLayout(cardLayout);

                JPanel dashboardPanel = LoginDashboardGUI.getDashboard(frame.getContentPane(), cardLayout);
                JPanel loginPanel = LoginDashboardGUI.getLoginPanel(frame.getContentPane(), cardLayout );



                frame.add(dashboardPanel, LoginDashboardGUI.DASHBOARD_PANEL);
                frame.add(loginPanel, LoginDashboardGUI.LOGIN_PANEL);

                cardLayout.show(frame.getContentPane(), LoginDashboardGUI.LOGIN_PANEL);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(frame.getSize());
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        });
        
    }

    public static JPanel createStatPanel(String title){
        JPanel statPanel = new JPanel();
        statPanel.setBorder(BorderFactory.createTitledBorder(title));
        statPanel.add(new JLabel("--Data--"));
        statPanel.setPreferredSize(statPanel.getPreferredSize());
        
        return statPanel;
    }
    
}
