/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitraining;

import javax.swing.JApplet;

/**
 *
 * @author Lenovo-BM
 */
public class RandomStringApplet extends JApplet {

    /**
   * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        String message=getParameter("message");
        RandomStringPanel content=new RandomStringPanel(message);
        setContentPane(content);
        content.addMouseListener(content);
        //RepaintOnClick listener=new RepaintOnClick();
        //content.addMouseListener(listener);
        
        
        // TODO start asynchronous download of heavy resources
    }
    // TODO overwrite start(), stop() and destroy() methods
}
