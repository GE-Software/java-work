/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jobscheduling;

/**
 *
 * @author Lenovo-BM
 */
public class jobs {
    public int id;
    public float weight;
    public float length;
    public float score;  // score = weight/length;
    public float completiontime;
    
    jobs()
    {
        weight=0;
        length=0;
        score=0;
        completiontime=0;
    }
            
}
