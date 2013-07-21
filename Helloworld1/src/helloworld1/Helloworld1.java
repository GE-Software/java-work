/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld1;

/**
 *
 * @author Lenovo-BM
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Helloworld1 {

    private static class HelloWorldDisplay extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString( "Hello World!", 20, 30 );
        }
}
private static class ButtonHandler implements ActionListener {
public void actionPerformed(ActionEvent e) {
System.exit(0);
}
}
public static void main(String[] args) {
    HelloWorldDisplay displayPanel = new HelloWorldDisplay();
    JButton okButton = new JButton("OK");
    ButtonHandler listener = new ButtonHandler();
    okButton.addActionListener(listener);
    JPanel content = new JPanel();
    content.setLayout(new BorderLayout());
    content.add(displayPanel, BorderLayout.CENTER);
    content.add(okButton, BorderLayout.SOUTH);
    JFrame window = new JFrame("Test");
    window.setContentPane(content);
    window.setSize(350,150);
    window.setLocation(200,500);
    window.setVisible(true);
}
}