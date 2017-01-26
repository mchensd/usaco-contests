/*
USACO DEC 2015 SILVER 3
 */

/**
 *
 * @author Michael
 */

import java.util.*;
import java.io.*;
public class bcount {
    int n, q;
    int[][] breeds;
    StringBuilder sb;
    PrintWriter pw;
    
    
    static void copyArray(int[] orig, int[] a) {
        for (int i=0; i<orig.length; i++ ){
            a[i] = orig[i];
        }
        
    }
    
    int[] subArrays(int[] a, int[] b) {
        // subtract all elements in b from corresponding index in a
        int[] ans = new int[a.length];
        
        for (int i=0; i<ans.length; i++) {
            ans[i] = a[i] - b[i];
        }
        
        return ans;
    }
    void query(int a, int b) {
        int[] ans = subArrays(breeds[b], breeds[a-1]);
        sb = new StringBuilder();

        for (int i=0; i<3; i++) {
            sb.append(Integer.toString(ans[i]));
            if (i < 2) sb.append(" ");
        }
        pw.println(sb.toString());
    }
    void runIO() throws IOException {
        BufferedReader bf;
        bf = new BufferedReader(new FileReader("bcount.in"));
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        
        breeds = new int[n+1][3];
        
//        ++breeds[0][Integer.parseInt(bf.readLine())-1];
        
        for (int i=1; i<=n; i++) {
            copyArray(breeds[i-1], breeds[i]);
            ++breeds[i][Integer.parseInt(bf.readLine()) - 1];
        }
        
        
        // query all
        pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));

        for (int i=0; i<q; i++) {
            st = new StringTokenizer(bf.readLine());
            query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        pw.close();
//        System.out.println(sb.toString());
        
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{ new bcount().runIO(); }
    
}
