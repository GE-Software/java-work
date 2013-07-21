/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guitraining;

import javax.swing.JFrame;

/**
 * A stand-alone application that lets the user play a simple
 * game, using the arrow keys.  The main program just opens a window
 * that shows a SubKillerPanel, which does all the work.
 */
public class SubKiller {
   
   public static void main(String[] args) {
      JFrame window = new JFrame("Sub Killer Game");
      SubKillerPanel content = new SubKillerPanel();
      window.setContentPane(content);
      window.setSize(600, 550);
      window.setLocation(100,100);
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setResizable(false);  // User can't change the window's size.
      window.setVisible(true);
   }
   
}
