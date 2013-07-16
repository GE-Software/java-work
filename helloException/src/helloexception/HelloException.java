/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helloexception;

/**
 *
 * @author Lenovo-BM
 */
public class HelloException {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    int a=0, b=6, c=0;
    try
    {
        a=b/c;
    }
    catch(ArithmeticException e){
    System.out.println("Divide By Zero");
    }
}
}