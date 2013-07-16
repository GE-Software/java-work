/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firstthread;

/**
 *
 * @author Lenovo-BM
 */
public class FortuneCookie implements Runnable{
    private String first;
    private String last;
    private int sleepTime;
    FortuneCookie(String first, String last, int sleepTime)
    {
        this.first=first;
        this.last=last;
        this.sleepTime=sleepTime;
    }
    public void run()
    {
        while(true)
        {
            System.out.println(first);
            try{
                Thread.sleep(sleepTime);
            }
            catch(InterruptedException e){
                 e.printStackTrace();
            
            }
            System.out.println(last);
        }
        
    }
}
