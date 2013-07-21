/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitraining;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
/**
 *
 * @author Lenovo-BM
 */
public class ClickableRandomStringApp {
    public static void main(String[] args)
    {
        JFrame window=new JFrame("Random Strings");
        RandomStringPanel content=new RandomStringPanel();
        content.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt)
            {
                Component source=(Component) evt.getSource();
                source.repaint();
            }
            
            
            
        });
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(100, 75);
        window.setSize(300,240);
        window.setVisible(true);
        
    }
}
