/*
ID: mchensd
LANG: JAVA
PROG: learning
 */

// USACO December 2014 Bronze contest #4

/**
 *
 * @author Michael
 */
import java.util.*;
import java.io.*;
public class learning {
    public static class MyQuickSort {
     
    private int array[];
    private char[] spotted;
    private int length;
 
    public void sort(int[] inputArr, char[] charArr) {
         
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        this.spotted = charArr;
        length = inputArr.length;
        quickSort(0, length - 1);
    }
 
    private void quickSort(int lowerIndex, int higherIndex) {
         
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
 
    private void exchangeNumbers(int i, int j) {
        int temp = array[i];
        char spotTemp = spotted[i];
        array[i] = array[j];
        array[j] = temp;
        spotted[i] = spotted[j];
        spotted[j] = spotTemp;
    }
}
    
    public static int mid(int a, int b) {
        // return middle weight of a and b, rounded down
        return (a+b)/2;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader bf;
        PrintWriter pw;
        StringTokenizer st;
        
        bf = new BufferedReader(new FileReader("learning.in"));
        st = new StringTokenizer(bf.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        int[] weights = new int[n];
        char[] spotted = new char[n];
        
        // set up weights, spotted
        for (int i=0; i<n; i++)
        {
            st = new StringTokenizer(bf.readLine());
            spotted[i] = (st.nextToken().equals("S")) ? "y".charAt(0) : "n".charAt(0);
            weights[i] = Integer.parseInt(st.nextToken());
        }
        
        MyQuickSort qs = new MyQuickSort();
        qs.sort(weights, spotted);
        /*for (int i=0; i<n; i++)
        {
            System.out.printf("%d %s\n", weights[i], spotted[i]); 
        }*/
        int numSpotted = 0;
        boolean nInRange = true;
        for (int i=0; i<n-1; i++)
        {
            char lowerSpots = spotted[i];
            char upperSpots = spotted[i+1];
            int lowerw = weights[i];
            int upperw = weights[i+1];
            
            if (lowerw >= b)
            {
                nInRange = false;
                if (lowerSpots == "y".charAt(0)) numSpotted = b-a+1; 
                else numSpotted = 0;
                break;                      
            }
                
            if (upperw > b) {
                nInRange = false;
                upperw = b;
            }
            
            int mid = mid(lowerw, upperw);
            
            if (lowerSpots != upperSpots)
            {
                if ((upperw-lowerw) % 2 == 1) { // odd
                    numSpotted += mid-lowerw;
                }
                
                else if ((upperw-lowerw) % 2 == 0) { // even
                    numSpotted += mid - lowerw;
                }
                
            }
            
            else if (lowerSpots == upperSpots)
            {
                numSpotted += (lowerSpots == "y".charAt(0)) ? upperw - lowerw : 0;
            }
                
            if (!nInRange) break;
            
        } // end for
        
        // count for edge cases:
        if (weights[0] <=    b)
        {
            
            numSpotted += weights[0] - a + 1;
            if (nInRange)
            {numSpotted += b - weights[weights.length - 1];}
        }
        pw = new PrintWriter(new BufferedWriter(new FileWriter("learning.out")));
        pw.println(numSpotted);
        pw.close();
    } // end main
    
}