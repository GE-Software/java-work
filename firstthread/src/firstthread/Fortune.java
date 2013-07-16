/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firstthread;

/**
 *
 * @author Lenovo-BM
 */
public class Fortune extends Thread {
    public void run()
    {
        while(true)
        {
            System.out.println("Good things will come your way");
            try{
                Thread.sleep(5000);
            }
            catch(InterruptedException e){
                 e.printStackTrace();
            
            }
        }
    }
}
