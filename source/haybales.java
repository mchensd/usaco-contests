/*
ID: mchensd
LANG: JAVA
PROG: haybales
 */
/**
 *
 * @author Michael
 */

// Dec 2016 Silver 1
import java.io.*;
import java.util.*;

public class haybales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader bf;
        PrintWriter pw;
        StringTokenizer st;
        
        bf = new BufferedReader(new FileReader("haybales.in"));
        st = new StringTokenizer(bf.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        
        int [] hayDist = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i=0; i<n; i++)
        {
            hayDist[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(hayDist);
        pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
        
        for (int i=0; i<q; i++)
        {
            st = new StringTokenizer(bf.readLine());
            int lb = Integer.parseInt(st.nextToken());
            int ub = Integer.parseInt(st.nextToken());
            
            int li = 0;
            int ui = n-1;
            int mi = (li+ui) / 2;
            
            int beginDist;
            int endDist;
            
            // get begindist
            while (true) {
                if (li == ui) {
                    beginDist = (hayDist[li] < lb) ? li + 1 : li; break;
                }
                
                if (hayDist[mi] == lb) { beginDist = mi; break; }
                if (hayDist[mi] < lb) {
                    li = mi+1;
                    mi = (li+ui) / 2;
                }
                
                else if (hayDist[mi] > lb) {
                    ui = mi;
                    mi = (li+ui) / 2;
                }
                
            } // end while
            
            // get endDist
            li = 0;
            ui = n-1;
            mi = (li+ui) / 2;
            while(true) {
                if (li==ui) { 
                    if (hayDist[li] > ub) endDist = li-1;
                    else endDist = li;
                    break;
                }
                if (hayDist[mi] == ub) { endDist = mi; break; }
                if (hayDist[mi] < ub) {
                    li = mi+1;
                    mi = (ui+li) / 2;
                }
                else if (hayDist[mi] > ub) {
                    ui = mi;
                    mi = (li+ui)/2;
                }
            } // end while
            pw.println(endDist-beginDist + 1);
        } // end for
        pw.close();
    } // end main
    
}
