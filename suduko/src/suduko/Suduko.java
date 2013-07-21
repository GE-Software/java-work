
//<editor-fold defaultstate="collapsed" desc="comment">

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package suduko;

import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Lenovo-BM
 */
public class Suduko {

    
    Cell[][] sgrid;
    List<Integer>[][] possibleValues;
    int size;
    
    public Suduko(int[][] A, int size) {
        sgrid = new Cell[size][size];
        possibleValues = new LinkedList[size][size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sgrid[i][j] = new Cell();
            }
        }
        initializeGrid(A);
        initializePossibleValues();
    }
    
    private void initializeGrid(int[][] A) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (A[i][j] != 0) {
                    sgrid[i][j].value = A[i][j];
                    sgrid[i][j].fixed = true;
                }
            }
        }
    }
    
    private void initializePossibleValues() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (sgrid[i][j].fixed = false) {
                    for (int k = 1; k <= size; k++) {
                        possibleValues[i][j].add(k);
                    }
                }
            }
        }
    }
    
    private void reducePossibleValues(int i, int j) {
        if (sgrid[i][j].fixed = true) {
            // reduces from rows
            for (int k = 0; k < size; k++) {
                if (possibleValues[i][k].contains((Integer) sgrid[i][j].value)) {
                    possibleValues[i][k].remove((Integer) sgrid[i][j].value);
                }
            }
            //reduces from columns
            for (int k = 0; k < size; k++) {
                if (possibleValues[k][j].contains((Integer) sgrid[i][j].value)) {
                    possibleValues[k][j].remove((Integer) sgrid[i][j].value);
                }
            }
            // reduces from subarray
            int subsize = (int) Math.sqrt(size);
            int lowerrow = (i / subsize) * subsize;
            int upperrow = lowerrow + subsize - 1;
            int lowercolumn = (j / subsize) * subsize;
            int uppercolumn = lowercolumn + subsize - 1;
            
            for (int k = lowerrow; k <= upperrow; k++) {
                for (int l = lowercolumn; l <= uppercolumn; l++) {
                    if (possibleValues[k][l].contains((Integer) sgrid[i][j].value)) {
                        possibleValues[k][l].remove((Integer) sgrid[i][j].value);
                    }
                }
            }
            
        }
    }
    
    private void reduceSingleton() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (possibleValues[i][j].size() == 1) {
                    reducePossibleValues(i, j);
                    sgrid[i][j].value = possibleValues[i][j].get(0);
                    sgrid[i][j].fixed = true;
                    possibleValues[i][j].remove(0);
                    
                }
            }
        }
    }

    private Boolean solutionFound()
    {
       
        
        for (int i=0;i<size;i++)
            for (int j=0; j<size;j++)
                if (sgrid[i][j].fixed==false)
                    return false;
        return true;
    }
    
    public void solve()
    {
        boolean solved=false;
        while (solved==false)
        {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    reducePossibleValues(i,j);
                    reduceSingleton();
                    if(solutionFound()==true)
                    {
                        solved=true;
                    }
                }
            }
        complexReduce();
        }
    }
    public void complexReduce()
    {
        
    }
    
    //----------------------
    private class Cell {
        
        int value = 0;
        boolean fixed = false;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}

//</editor-fold>
