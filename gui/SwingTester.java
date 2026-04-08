package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class SwingTester {
    public static void main(String[] args) {
        // try {
        //     // Set the Look and Feel to the system default
        //     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // JFrame frame = new JFrame("My First GUI Application");
        // // frame.setLayout(new FlowLayout(FlowLayout.LEADING));

        // // JLabel label = new JLabel("A label");
        // // frame.add(label);
        
        // // JButton button = new JButton("Click me!");
        // // frame.add(button);

        // frame.setSize(500, 500);
        // frame.setVisible(true);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        // JButton button = new JButton("Button1");
        // //frame.add(button); // frame.getContentPane().add(button);
        // panel.add(button);

        // JButton button2 = new JButton("Button2");
        // //frame.add(button2);
        // panel.add(button2);
        // //panel.setBorder(BorderFactory.createLineBorder(Color.blue));
        // panel.setBorder(BorderFactory.createBevelBorder(EtchedBorder.RAISED));

        // panel.setSize(200, 200);
        
        // for(int i= 0; i < 10; i++){
        //     panel.add(new JButton("Button " + i));
        // }
        // frame.add(panel);



        CalculatorUI calUI = new CalculatorUI();
        calUI.makeGridBagUI();

        // MyFrame frame2 = new MyFrame();
    }
}

class MyFrame extends JFrame{

    public MyFrame(){
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("My Window App");
    }
}

class CalculatorUI{
    public String[] buttonStr = {
        "7", "8", "9", "x",
        "6", "5", "4", "-",
        "3", "2", "1", "+",
        "/", "0", ".", "="
    };

    public void makeGridUI(){
        JFrame frame = new JFrame("Calculator App");
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JTextField inputField = new JTextField(20);
        frame.add(inputField);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));

        for(String str : buttonStr){
            JButton button = new JButton(str);
            buttonPanel.add(button);
        }

        Insets inset = buttonPanel.getInsets(); 
        inset.set(20, 20, 20, 20);

        buttonPanel.revalidate();
        buttonPanel.repaint();


        frame.add(buttonPanel);
        frame.pack();
        frame.revalidate();
        frame.repaint();
    }

    public void makeGridBagUI(){
        JFrame frame = new JFrame("Calculator App");
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.setLayout(new GridBagLayout());

        JTextField inputField = new JTextField(20);
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridy = 0;
        cons.gridx = 0;
        cons.weightx = 1.0;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10, 10, 10);
        frame.add(inputField, cons);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        cons.gridx = 0;
        cons.gridy = 1;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        frame.add(buttonPanel, cons);

        cons = new GridBagConstraints();
        cons.insets = new Insets(2, 2, 2, 2);

        JButton button0 = new JButton(buttonStr[0]);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 3;
        buttonPanel.add(button0, cons);

        JButton button1 = new JButton(buttonStr[1]); 
        cons.gridx = 4;
        cons.gridy = 0;
        cons.weightx = 1.0;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH;
        buttonPanel.add(button1, cons);

        JButton button2 = new JButton(buttonStr[2]); 
        cons.gridx = 5;
        cons.gridy = 0;
        cons.weightx = 0.0;
        cons.weighty = 0.0;
        cons.gridwidth = 10;
        cons.ipady = 100;
        cons.fill = GridBagConstraints.NONE;
        buttonPanel.add(button2, cons);

        JButton button3 = new JButton(buttonStr[3]);
        cons.gridx = 0;
        cons.gridy = 1;
        cons.weightx = 0.0;
        cons.weighty = 0.0;
        cons.gridwidth = 1;
        cons.ipady = 0;
        buttonPanel.add(button3, cons);

        JButton button4 = new JButton(buttonStr[4]);
        cons.gridx = 1;
        cons.gridy = 1;
        cons.weightx = 0.0;
        cons.weighty = 0.0;
        cons.gridwidth = 1;
        cons.ipady = 0;
        buttonPanel.add(button4, cons);


        // for(String str : buttonStr){
        //     JButton button = new JButton(str);
        //     buttonPanel.add(button);
        // }


        frame.pack();
        frame.revalidate();
        frame.repaint();
    }
}
