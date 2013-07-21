/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mutliprocessorscheduling;

/**
 *
 * @author Lenovo-BM
 */
public class Multiprocessorscheduling {

    Job[] Jobs;
    Processor[] Processors;
    HeapJobs HJobs;
    HeapProcessor HProcessor;
    
    
    
    Multiprocessorscheduling(int njobs,int nprocessors,Double[] time, Double[] weight)
    {
        Jobs= new Job[njobs];
        Processors= new Processor[nprocessors];
        HJobs=new HeapJobs(njobs);
        HProcessor= new HeapProcessor(nprocessors);
        
        for(int i=0;i<njobs;i++)
        {
          Jobs[i]=new Job(i,time[i],weight[i]);
          HJobs.insert(Jobs[i]);
        
        }
        for (int i=0; i< nprocessors ; i++)
        {
           Processors[i]=new Processor(i);
           HProcessor.insert(Processors[i]);
        }
    
    }
    
    public Double Simulate()
    {
        Job tempjob=new Job(-1,0.0,0.0);
        Processor tempprocessor=new Processor(-1);
        
        for (int i=0; i<Jobs.length;i++)
        {
            tempjob=HJobs.deletemax();
            tempprocessor=HProcessor.deletemin();
            Jobs[tempjob.ID].Completiontime=Processors[tempprocessor.ID].Completiontime+tempjob.Processingtime;
            Processors[tempprocessor.ID].Completiontime=Jobs[tempjob.ID].Completiontime;
            Processors[tempprocessor.ID].Jobssequence.append(tempjob.ID);
            HProcessor.insert(Processors[tempprocessor.ID]);
        }
        return fitness();
    }
    public Double fitness()
    {
        int njobs;
        Double F=0.0;
        njobs=Jobs.length;
        for (int i=0;i<njobs;i++) {
            F= F + Jobs[i].Weight*Jobs[i].Completiontime;
        }
    
        return F;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
        int njobs=6;
        int nprocessors=2;
        Double[] time={10.0,5.0,3.0,2.0,1.0,6.0};
        Double[] weight={6.0,1.0,4.0,1.0,1.0,12.0};
        
        Multiprocessorscheduling MPS=new Multiprocessorscheduling(njobs,nprocessors,time,weight);
        Double f=MPS.Simulate();
    
    }
}
