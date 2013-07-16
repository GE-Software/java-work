/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mutliprocessorscheduling;

/**
 *
 * @author Lenovo-BM
 */

import java.util.Random;


public class Job {

    int ID;
    Double Weight;
    Double Processingtime;
    Double Completiontime;
    Double Score;
    int ProcessorID=0;
    
    Job(int ID , Double Processingtime, Double weight)
    {
       
        
        this.ID=ID;
        this.Weight=weight;
        this.Processingtime=Processingtime;
        Completiontime=0.0;
        if (Processingtime!=0)
        Score=Weight/Processingtime;
        else
            Score=0.0;
    }
    
}
