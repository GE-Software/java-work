/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package squaresgui;

import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
/**
 *
 * @author Lenovo-BM
 */
public class SquaresGUI  {
    
    public static class Squares extends JPanel implements MouseListener , MouseMotionListener
    {
        boolean dragging=false;
        boolean initial=true;
        boolean reddragging=false;
        boolean bluedragging=false;
        
        int redCurrentX=0;
        int redCurrentY=0;
        int blueCurrentX=0;
        int blueCurrentY=0;
        int offsetX,offsetY;
        Squares()
        {
            setBackground(Color.WHITE);
            addMouseListener(this);
            addMouseMotionListener(this);
            repaint();
        }
                
        public void paintComponent(Graphics g)
        {
                        
            int height=getHeight();
            int width=getWidth();
            if (initial == true)
            {
                super.paintComponent(g);
                g.setColor(Color.RED);
                
                g.fillRect(20,20,100,100);
                g.setColor(Color.BLUE);
                
                g.fillRect(width-120,height-120,100,100);
                initial=false;
                redCurrentX=20;
                redCurrentY=20;
                blueCurrentX=width-120;
                blueCurrentY=height-120;
            }
            else
            {
                if (reddragging==true)
                {
                    super.paintComponent(g);
                    g.setColor(Color.RED);
                                       
                     g.fillRect(redCurrentX,redCurrentY,100,100);
                     
                     g.setColor(Color.BLUE);
                     
                     g.fillRect(blueCurrentX,blueCurrentY,100,100);
                     
                }
                else 
                    if(bluedragging==true)
                    {
                        super.paintComponent(g);
                        g.setColor(Color.BLUE);
                        
                        g.fillRect(blueCurrentX,blueCurrentY,100,100);
                        g.setColor(Color.RED);
                       
                        g.fillRect(redCurrentX,redCurrentY,100,100);
                    }
                else
                    {   }
                        /**g.setColor(Color.BLUE);
                        g.drawRect(blueCurrentX,blueCurrentY,100,100);
                        g.fillRect(blueCurrentX,blueCurrentY,100,100);
                        g.setColor(Color.RED);
                        g.drawRect(redCurrentX,redCurrentY,100,100);
                        g.fillRect(redCurrentX,redCurrentY,100,100);
                    }   **/
            
        }
     }
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if((e.getX()>=redCurrentX) &&(e.getX()<=redCurrentX+100)&&(e.getY()>=redCurrentY)&&
                    e.getY()<=redCurrentY+100)
            {
                dragging=true;
                reddragging=true;
                offsetX = e.getX() - redCurrentX;  // Distance from corner of square to (x,y).
                offsetY = e.getY() - redCurrentY;
            }
            else if((e.getX()>=blueCurrentX) &&(e.getX()<=blueCurrentX+100)&&(e.getY()>=blueCurrentY)&&
                    e.getY()<=blueCurrentY+100)
            {
                dragging=true;
                bluedragging=true;
                offsetX=e.getX()-blueCurrentX;
                offsetY=e.getY()-blueCurrentY;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
       if (dragging == false)
            return;  // Nothing to do because the user isn't drawing.
         dragging = false;
        
         bluedragging=false;
         reddragging=false;
        
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        if (dragging == false)
            return;
        if (reddragging==true)
        {
           redCurrentX=e.getX()-offsetX;
           redCurrentY=e.getY()-offsetY;
        }
        else
        {
            blueCurrentX=e.getX()-offsetX;
            blueCurrentY=e.getY()-offsetY;
        }
          repaint();
        }
    

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame window=new JFrame("Squares Test");
        Squares s=new Squares();
        window.setSize(500,500);
        window.setLocation(100,100);
        window.setContentPane(s);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    
    }
}
