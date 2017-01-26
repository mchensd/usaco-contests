/*
ID: mchensd
LANG: JAVA
PROG: cowjog
 */

// 2014 Bronze #3

/**
 *
 * @author Michael
 */
import java.util.*;
import java.io.*;
public class cowjog {
    
    /**
     * @param speeds array of speeds
     * @param ng number of groups
     * @return final number of groups
     */
    public static int partition(int[] speeds, int ng) {
        
        int tempMin = speeds[0]; // smallest speed of a group
        int j = 0; // used to keep track of number of new groups after partition
        for (int i=1; i<ng; i++)
        {
            if (speeds[i] < tempMin) tempMin = speeds[i];
            
            else if (speeds[i] >= tempMin)
            {
                speeds[j++] = tempMin;
                tempMin = speeds[i];
            }
            
        } // end for
        speeds[j++] = tempMin;
        if (j == ng) return ng;
        else return partition(speeds, j); // j is equal to number of groups
    } // end partition
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader bf;
        PrintWriter pw;
        StringTokenizer st;
        
        bf = new BufferedReader(new FileReader("cowjog.in"));
        int ng = Integer.parseInt(bf.readLine()); // number of groups: initially is always equal to number of cows
        
        // get speeds
        int[] speeds = new int[ng];
        for (int i=0; i<ng; i++)
        {
            st = new StringTokenizer(bf.readLine());
            st.nextToken();
            speeds[i] = Integer.parseInt(st.nextToken());
        }
        
        ng = partition(speeds, ng);
        
        pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
        pw.println(ng);
        pw.close();
    }
    
}
