/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitraining;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Lenovo-BM
 */
public class SimpleStamperPanel extends JPanel implements MouseListener, MouseMotionListener {

    boolean dragging=false;
    int previousX , previousY;
    
    public SimpleStamperPanel(){
        setBackground(Color.BLACK);
        addMouseListener(this);
        addMouseMotionListener(this); 
        
    }
    
    public void paintComponent(Graphics g) {
       
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        if (evt.isShiftDown())
        {
            repaint();
            dragging=false;
        }
        if (dragging==true)
            return;
    
     dragging=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent evt) {
    }

    @Override
    
    public void mouseDragged(MouseEvent evt) {
        if ( evt.isShiftDown() ) {
// The user was holding down the Shift key. Just repaint the panel.
// Since this class does not define a paintComponent() method, the
// method from the superclass, JPanel, is called. That method simply
// fills the panel with its background color, which is black. The
// effect is to clear the panel.
        repaint();
        return;
    }
        if (dragging==false)
            return;
    
    int x = evt.getX(); // x-coordinate where user clicked.
    int y = evt.getY();// y-coordinate where user clicked.
    
    if ((previousX+50<=x)|| (previousY+50<=y) )
    {
    
    Graphics g = getGraphics(); // Graphics context for drawing directly.
    // NOTE: This is considered to be bad style!
    if ( evt.isMetaDown() ) {
// User right-clicked at the point (x,y). Draw a blue oval centered
// at the point (x,y). (A black outline around the oval will make it
// more distinct when shapes overlap.)
        g.setColor(Color.BLUE); // Blue interior.
        g.fillOval( x - 30, y - 15, 60, 30 );
        g.setColor(Color.BLACK); // Black outline.
        g.drawOval( x - 30, y - 15, 60, 30 );
    }
    else {
// User left-clicked (or middle-clicked) at (x,y).
// Draw a red rectangle centered at (x,y).
            g.setColor(Color.RED); // Red interior.
            g.fillRect( x - 30, y - 15, 60, 30 );
            g.setColor(Color.BLACK); // Black outline.
            g.drawRect( x - 30, y - 15, 60, 30 );
        }
    g.dispose(); // We are finished with the graphics context, so dispose of it.
    previousX=x;
    previousY=y;
    }
    else 
        return;
    
    }
        
        


    @Override
    public void mouseMoved(MouseEvent e) {
       
    }
    
}
