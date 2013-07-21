/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firstthread;
import java.util.Scanner;
/**
 *
 * @author Lenovo-BM
 */
public class Firstthread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    Scanner input=new Scanner(System.in);
    //Fortune myFortuneThread=new Fortune();
    System.out.println("Enter anything");
    //myFortuneThread.setDaemon(false);
    //myFortuneThread.start();
    //FortuneCookie cookie1=new FortuneCookie("Wassim","Bedwani",1000);
    //FortuneCookie cookie2=new FortuneCookie("Jailan","Edward",1200);
    Thread cookie1= new Thread(new FortuneCookie("Wassim","Bedwani",1000));
    Thread cookie2=new Thread(new FortuneCookie("Jailan","Edward",1200));
    
    cookie1.setPriority(Thread.MAX_PRIORITY);
    cookie2.setPriority(Thread.MIN_PRIORITY);
    input.next();
    
    new Thread(
            new Runnable()
            {
                public void run(){
                    System.out.println("inner class");
                }
            }
            
            
            ).start();
    
    
    
    
    
    
    
    cookie1.start();
    cookie2.start();
    System.out.println("Input request was satisfied");
    try{
        cookie1.join();
    }
    catch (InterruptedException e){
         e.printStackTrace();
    }
    
    try{
        Thread.sleep(3000);
    }
    catch (InterruptedException e){
        e.printStackTrace();
    }
    System.out.println("I selpt for three seconds");
    }
    }

