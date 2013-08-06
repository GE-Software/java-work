/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twosumadvanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Lenovo-BM
 */
public class TwoSumAdvanced {

    Long[] A;
    HashMap<Long, Long> numbers;
    int counter = 0;
    int arrayActiveElements;

    TwoSumAdvanced(int nelements) throws FileNotFoundException {

        A = new Long[nelements];

        numbers = new HashMap(3000000);
        readfile();

    }

    public int calculateloop() {
        for (int i = -10000; i <= 10000; i++) {
            calculate(i);
        }
        return counter;
    }

    public void calculate(int sum) {

        Long remaining;
        for (int i = 0; i < arrayActiveElements; i++) {
            remaining = sum - A[i];
            if (numbers.containsKey(remaining) == true) {
                if (remaining != A[i]) {
                    counter = counter + 1;
                    //System.out.println(sum+"   "+A[i]+"    "+remaining);
                    break;
                }
            }
            // numbers.remove(A[i]);
        }

    }

    public void readfile() throws FileNotFoundException {
        String filename = "algo1_programming_prob_2sum.txt";
        //String filename="case2.txt";
        File file = new File(filename);
        Scanner s;
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String line = new String();
        line = "";
        s = new Scanner(bf);
        int i = 0;
        long t;

        while (s.hasNextLine()) {

            line = s.nextLine();
            t = Long.parseLong(line);
            /* if (numbers.isEmpty())
             {      A[i]=t;
             numbers.put(A[i],A[i]);
             i=i+1;
             }*/
            if (!numbers.containsKey(t)) {
                A[i] = t;
                numbers.put(A[i], A[i]);
                i = i + 1;
            }
        }
        arrayActiveElements = i;
        s.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here

        long starttime = System.nanoTime();
        TwoSumAdvanced t = new TwoSumAdvanced(1000000);
        int count = t.calculateloop();
        System.out.println(count);
        long endtime = System.nanoTime();
        double Difference = (endtime - starttime) / 1e9;
    }
}
