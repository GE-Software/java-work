/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swinglearning;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Lenovo-BM
 */
public class SwingLearning extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame myMainFrame = new SwingLearning();
        myMainFrame.setTitle("Hey: I am the Title");
        myMainFrame.setSize(400, 300);
        myMainFrame.setLocation(100, 100);
        myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainFrame.setVisible(true);
        myMainFrame.setLayout(new FlowLayout());
        JButton myButton = new JButton("click here");
        myMainFrame.add(myButton);
        MyButtonHandler mbh = new MyButtonHandler();
        myButton.addActionListener(mbh);
        JButton otherButton = new JButton("Other Button");
        myMainFrame.add(otherButton);
        otherButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("I hit the Other Button");

                    }
                });
    }
}

class MyButtonHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("I hit the Button");
        System.out.println(e);
    }
}