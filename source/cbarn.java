/*
ID: mchensd
LANG: JAVA
PROG: cbarn

USACO 2016 Feb Silver 1
 */
/**
 *
 * @author Michael
 */
import java.util.*;
import java.io.*;
public class cbarn {
    
    public static class Cow {
        private int strt;
        public Cow(int pos) {
            this.strt = pos;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader bf;
        PrintWriter pw;
        StringTokenizer st;
        
        bf = new BufferedReader(new FileReader("cbarn.in"));
        int n = Integer.parseInt(bf.readLine());
        
        Cow[][] rooms = new Cow[n][n];
        ArrayList<Integer> nulls = new ArrayList<>();  // array of indeces of nulls;
        for (int i=0; i<n; i++) {
            int cs = Integer.parseInt(bf.readLine());
            if (cs == 0) nulls.add(i);
            else {
                
            }
        }
        
        
        
    }
    
}
