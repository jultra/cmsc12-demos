package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyButton extends JButton implements ActionListener{

    public MyButton(String title){
        super(title);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onclick();
    }

    public void onclick(){
        System.out.println("I was clicked");
    }
    
}
