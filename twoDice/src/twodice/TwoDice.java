/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twodice;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo-BM
 */
public class TwoDice extends JPanel {

    int width=100;
    int height=100;
    int val1;
    int val2;
    int dotsize=10;
    TwoDice()
    {
        setBackground(Color.black);
        setSize(300,300);
        
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(50,50 , width, height);
        g.fillRect(200,200,width, height);
        drawDice(g,4,50,50);
        drawDice(g,6,200,200);
   }
    
    private void drawDice(Graphics g,int val, int x, int y)
    {
        g.setColor(Color.BLACK);
        switch(val){
            case 1:
                g.fillOval(x+(width/2)-dotsize/2,y+(height/2)-dotsize/2,dotsize,dotsize);
                break;
            case 2:
                g.fillOval(x+20-dotsize/2, y+20-dotsize/2, dotsize, dotsize);
                g.fillOval(x+width-20-dotsize/2, y+height-20-dotsize/2, dotsize, dotsize);
                break;
            case 3:
              g.fillOval(x+20-dotsize/2, y+20-dotsize/2, dotsize, dotsize);
              g.fillOval(x+width/2-dotsize/2, y+(height/2)-dotsize/2, dotsize, dotsize);
              g.fillOval(x+width-20-dotsize/2, y+height-20-dotsize/2, dotsize, dotsize);
              break;
            case 4:
                g.fillOval(x+20-dotsize/2, y+20-dotsize/2, dotsize, dotsize);
                g.fillOval(x+width-20-dotsize/2, y+20-dotsize/2,dotsize,dotsize);
                g.fillOval(x+20-dotsize/2, y+height-20-dotsize/2, dotsize, dotsize);
                g.fillOval(x+width-20-dotsize/2, y+height-20-dotsize/2, dotsize, dotsize);
                break;
            case 5:
                g.fillOval(x+20-dotsize/2, y+20-dotsize/2, dotsize, dotsize);
                g.fillOval(x+width-20-dotsize/2, y+20-dotsize/2,dotsize,dotsize);
                g.fillOval(x+width/2-dotsize/2, y+height/2-dotsize/2, dotsize, dotsize);
                g.fillOval(x+20-dotsize/2, y+height-20-dotsize/2, dotsize, dotsize);
                g.fillOval(x+width-20-dotsize/2, y+height-20-dotsize/2, dotsize, dotsize);
                break;
            case 6: 
                g.fillOval(x+20-dotsize/2,y+20-dotsize/2,dotsize,dotsize);
                g.fillOval(x+width/2-dotsize/2, y+20-dotsize/2, dotsize, dotsize);
                g.fillOval(x+width-20-dotsize/2,y+20-dotsize/2,dotsize,dotsize);
                g.fillOval(x+20-dotsize/2,y+height-20-dotsize/2,dotsize,dotsize);
                g.fillOval(x+width/2-dotsize/2, y+height-20-dotsize/2, dotsize,dotsize);
                g.fillOval(x+width-20-dotsize/2,y+height-20-dotsize/2,dotsize,dotsize);
                break;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    JFrame window=new JFrame();
    TwoDice t=new TwoDice();
    window.setContentPane(t);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setSize(300, 300);
    window.setLocation(300,300);
    window.setVisible(true);
    
    }
}
