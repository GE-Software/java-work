/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitraining;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo-BM
 */
public class SimpleStamperApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        SimpleStamperPanel panel=new SimpleStamperPanel();
        setContentPane(panel);
       
        
        // TODO start asynchronous download of heavy resources
    }
    // TODO overwrite start(), stop() and destroy() methods
}
