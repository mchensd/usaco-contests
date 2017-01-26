/*
USACO JAN 2017 SILVER 2
 */

/**
 *
 * @author Michael
 */
import java.util.*;
import java.io.*;
public class hps {
    
    int n, x;
    String[] fj;
    int before, after, ans;
    int[] hpsBefore, hpsAfter;
    HashMap<String, Integer> map;
    
    int maxVal(int[] a) {
        int max = 0;
        for (int i=0; i<a.length; i++) {
            max = Math.max(max, a[i]);
        }
        return max;
    }
    void getWins(int x) {
        // get wins if split at x
        String move = fj[x];
        int index = map.get(move);
        
        ++hpsBefore[index];
        --hpsAfter[index];
        
        before = maxVal(hpsBefore);
        after = maxVal(hpsAfter);
        ans = Math.max(ans, before+after);
    }
    void run() throws IOException {
        BufferedReader bf;
        PrintWriter pw;
        
        bf = new BufferedReader(new FileReader("hps.in"));
        
        n = Integer.parseInt(bf.readLine());
        
        map = new HashMap<>();
        map.put("H", 0); map.put("P", 1); map.put("S", 2);
        
        hpsBefore = new int[3];
        hpsAfter = new int[3];
        
        fj = new String[n];
        
        fj[0] = bf.readLine();
        hpsBefore[map.get(fj[0])] = 1;
        
        for (int i=1; i<n; i++) {
            fj[i] = bf.readLine();
//            System.out.println(fj[i]);
            ++hpsAfter[map.get(fj[i])];
        }
        
        before = 1; // max score before x
        after = maxVal(hpsAfter);
        ans = before + after;
        
        if (n > 1) {
            for (x = 1; x < n; x++) {
                getWins(x);
            }
        }
        
        pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        pw.println(ans);
        pw.close();
//        System.out.println(ans);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        new hps().run();
    }
    
}
