/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JApplet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo-BM
 */
public class helloWorldApplet extends JApplet {

    private String currentMessage="Hello World";
    private MessageDisplay displayPanel;
    
    private class MessageDisplay extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawString(currentMessage,20,30);
                    
        }
    }
    
    private class ButtonHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentMessage.equals("Hello World"))
                currentMessage="GoodBye World";
            else
                    currentMessage="Hello World";
            displayPanel.repaint();
            
        }
        
    }
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {

        displayPanel = new MessageDisplay();
        JButton changeMessageButton=new JButton("Change Message");
        ButtonHandler listener=new ButtonHandler();
        changeMessageButton.addActionListener(listener);
        JPanel content=new JPanel();
        content.setLayout(new BorderLayout());
        content.add(displayPanel,BorderLayout.CENTER);
        content.add(changeMessageButton,BorderLayout.SOUTH);
        setContentPane(content);
        
        // TODO start asynchronous download of heavy resources
    }
    // TODO overwrite start(), stop() and destroy() methods
}
