/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jobshopscheduling1.pkg0;

/**
 *
 * @author Administrator
 */
public class Microgenetic {
 int chromosomelength;
 int nmachines;
 int populationsize;
 int fitness[];
 int chromosomes[][];
 int crossoverpoints;
 
 Microgenetic(int chrlength, int nmach, int psize, int cpoints ){
     chromosomelength=chrlength;
     nmachines=nmach;
     populationsize=psize;
     crossoverpoints=cpoints;
     fitness=new int [psize];
     chromosomes= new int [chromosomelength][populationsize];
     Initialize(0);
     
 }
   private void Initialize(int initial)
   {
 
           for (int i=initial;i<populationsize;i++)
               for (int j=0;j<chromosomelength;j++)
                   chromosomes[i][j]=random()
   }
}
