/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortthreaddemo;
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
/**
 *
 * @author Lenovo-BM
 */
public class MergeSortThreadDemo extends JPanel {

    private final static int ARRAY_SIZE = 150;  // The number of colored bars.
   
   private int[] hue = new int[ARRAY_SIZE];  // The array that will be sorted.
   private Color[] palette = new Color[ARRAY_SIZE]; // Colors in spectral order.
   private Display display;     // The panel that displays the colored bars.
   private JButton startButton; // The button that starts and stops the demo.
   
   private Runner runner; // The thread that runs the recursion.

   private volatile boolean running;  // Set to true while recursion is running;
                                      // This is set to true as a signal to the
                                      // thread to abort.
   
   public MergeSortThreadDemo() {
      for (int i = 0; i < ARRAY_SIZE; i++) {
         palette[i] = Color.getHSBColor((i*230)/(ARRAY_SIZE*255.0F), 1, 1);
         hue[i] = i;
      }
      setLayout(new BorderLayout());
      display = new Display();
      add(display, BorderLayout.CENTER);
      startButton = new JButton("Start");
      JPanel bottom = new JPanel();
      bottom.add(startButton);
      bottom.setBackground(Color.WHITE);
      add(bottom,BorderLayout.SOUTH);
      startButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (running)
               stop();
            else
               start();
         }
      });
   }
   
   
   
   public void delay(int millis) {
      if (! running)
         throw new ThreadTerminationException();
      display.repaint();
      try {
         Thread.sleep(millis);
      }
      catch (InterruptedException e) {
      }
      if (! running)
         throw new ThreadTerminationException();
   }
   
   private void start() {
      startButton.setText("Finish");
      runner = new Runner();
      running = true;  // Set the signal before starting the thread!
      runner.start();
   }
   
   private void stop() {

      /* Set the value of the signaling variable to false as a signal
       * to the thread to terminate.
       */

      running = false; 
      
      /* Wake the thread, in case it is sleeping, to get a more
       * immediate reaction to the signal.
       */
      
      runner.interrupt(); 
      
      /* Wait for the thread to stop before setting runner = null.
       * One second should be plenty of time for this to happen, but
       * in case something goes wrong, it's better not to 
       */
      
      try {
         runner.join(1000);  // Wait for thread to stop.  One second should be plenty of time.
      }
      catch (InterruptedException e) {
      }
      
      runner = null;
   }
   
   
 //private int[] numbers;
  private int[] helper=new int[hue.length];

  //private int number;

  /**public void sort(int[] values) {
    this.numbers = values;
    number = values.length;
    this.helper = new int[number];
    mergesort(0, number - 1);
  }**/

  private void mergesort(int low, int high) {
    // Check if low is smaller then high, if not then the array is sorted
    if (low < high) {
      // Get the index of the element which is in the middle
      int middle = low + (high - low) / 2;
      // Sort the left side of the array
      mergesort(low, middle);
      // Sort the right side of the array
      mergesort(middle + 1, high);
      // Combine them both
      merge(low, middle, high);
    }
  }

  private void merge(int low, int middle, int high) {

    // Copy both parts into the helper array
    for (int i = low; i <= high; i++) {
      helper[i] = hue[i];
      delay(10);
    }

    int i = low;
    int j = middle + 1;
    int k = low;
    // Copy the smallest values from either the left or the right side back
    // to the original array
    while (i <= middle && j <= high) {
      if (helper[i] <= helper[j]) {
        hue[k] = helper[i];
        delay(10);
        i++;
      } else {
        hue[k] = helper[j];
        delay(10);
        j++;
      }
      k++;
    }
    // Copy the rest of the left side of the array into the target array
    while (i <= middle) {
      hue[k] = helper[i];
      delay(10);
      k++;
      i++;
    }

  }   

   
    //---------------------------------------------
   private class Display extends JPanel {
      Display() {
         setPreferredSize(new Dimension(606,206));
         setBackground(Color.GRAY);
      }
      protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         double barWidth = (double)(getWidth() - 6) / hue.length;
         int h = getHeight() - 6;
         for (int i = 0; i < hue.length; i++) {
            int x1 = 3 + (int)(i*barWidth + 0.49);
            int x2 = 3 + (int)((i+1)*barWidth + 0.49);
            int w = x2 - x1;
            //if (hue[i] == -1)
            //   g.setColor(Color.BLACK);
               
               g.setColor(palette[hue[i]]);
            g.fillRect(x1,3,w,h);
         }
      }
   }
   //--------------------------------------
    private class ThreadTerminationException extends RuntimeException {
   }
    
    //----------------------------------------------------
    
    
    
    private class Runner extends Thread {
      public void run() {
         try {
            for (int i = hue.length-1; i > 0; i--) { // Randomize array.
               int r = (int)((i+1)*Math.random());
               int temp = hue[r];
               hue[r] = hue[i];
               hue[i] = temp;
            }
            delay(1000);  // Wait one second before starting the sort.
            mergesort(0,hue.length-1);  // Sort the whole array.
         }
         catch (ThreadTerminationException e) { // User aborted quickSort.
            for (int i = 0; i < hue.length; i++)
               hue[i] = i;
         }
         finally {// Make sure running is false and button label is correct. 
            running = false; 
            startButton.setText("Start");
            display.repaint();
         }
      }
   }

    //------------------------
    
     
    
    //-------------------------
    public static void main(String[] args) {
        // TODO code application logic here
    JFrame window = new JFrame("Demo: Recursion in a Thread");
      MergeSortThreadDemo content = new MergeSortThreadDemo();
      window.setContentPane(content);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.pack();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      window.setLocation( (screenSize.width - window.getWidth()) / 2,
            (screenSize.height - window.getHeight()) / 2 );
      window.setVisible(true);
   
    }
}
