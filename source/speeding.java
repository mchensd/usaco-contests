/*
ID: mchensd
LANG: JAVA
PROG: speeding
 */

// This program answers USACO 2015-2016 Bronze Contest in December

/**
 *
 * @author Michael
 */
import java.util.*;
import java.io.*;
public class speeding {
    
    /**
     * @param arr the array of the values we need to finish comparing to compSpd
     * @param ai the current index of the array
     * @param compSpd speed to compare with the values of arr
     * @param currMax the value of the current max speed over the speed limit
     * @param compareBSpd if true, then compSpd is bSpd, else compSpd is rSpd
     * @return an updated max speed over
     */
    public static int compareRest(int[] arr, int ai, int compSpd, int currMax, boolean compareBSpd) {
       while (ai < arr.length)
       {
           if (compareBSpd) {
               currMax = (compSpd - arr[ai] > currMax) ? compSpd - arr[ai] : currMax;
           }
           
           else {
               currMax = (arr[ai] - compSpd > currMax) ? arr[ai] - compSpd : currMax;
           }
           ++ai;
       } // end while
       
       return currMax;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader bf;
        PrintWriter pw;
        
        bf = new BufferedReader(new FileReader("speeding.in"));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        int n, m;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        // intializing our arrays for speed and segments
        int[] rSegs = new int[n];
        int[] rSpeeds = new int[n]; 
        
        int[] bSegs = new int[m];
        int[] bSpeeds = new int[m];
        
       
        int currDist = 0;
        for (int i=0; i<n; i++)
        {
            st = new StringTokenizer(bf.readLine());
            currDist += Integer.parseInt(st.nextToken());
            rSegs[i] = currDist;
            rSpeeds[i] = Integer.parseInt(st.nextToken());
        } // end for
        
        currDist = 0;
        for (int i=0; i<m; i++)
        {
            st = new StringTokenizer(bf.readLine());
            currDist += Integer.parseInt(st.nextToken());
            bSegs[i] = currDist;
            bSpeeds[i] = Integer.parseInt(st.nextToken());
        } // end for
        
        // begin comparing by using one pointer for the road and one for bessie
        int ri = 0; // road index
        int bi = 0; // bessie index;
        int maxOver = 0;
        while (true)
        {
            int rSpd = rSpeeds[ri];
            int bSpd = bSpeeds[bi];
            int rDist = rSegs[ri];
            int bDist = bSegs[bi];
            
            // First update the max speed over
            if (bSpd - rSpd > maxOver) maxOver = bSpd - rSpd;
            
            // Now check if we are at the end of either segments
            if (ri == n - 1)
            {
                // compare the rest of bSpeeds to the last speed in r, updating maxOver if needed
                maxOver = compareRest(bSpeeds, bi+1, rSpd, maxOver, false); 
                break;
            }
            
            else if (bi == m - 1)
            {
                // Compare rest of rSpeeds to the last speed in bSpeeds, update maxOver
                maxOver = compareRest(rSpeeds, ri+1, bSpd, maxOver, true);
                break;
            }
            
            else if (rDist < bDist) ++ri;
            else if (bDist < rDist) ++bi;
            else if (rDist == bDist) { ++ri; ++bi; }            
        } // end while
        
        pw = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
        pw.println(maxOver);
        pw.close();
    } // end main
    
}
