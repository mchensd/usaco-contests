/*
USACO DEC 2015 SILVER 2
 */

/**
 *
 * @author Michael
 */
import java.util.*;
import java.io.*;
public class highcard {
    int n;
    int[] e, b;
    
    void getBetsy() {
        int ei = 0;
        int bi = 0;
        
        int i;
        for (i=1; i<=2*n; i++) {
            if (e[ei] == i) {
                if (ei == n-1) break;
                ++ei;
            }
            
            else b[bi++] = i;
        }
        
        for (int j=i+1; j<=2*n; j++) {
            b[bi++] = j;
        }
    }
    void solve() throws IOException {
        PrintWriter pw;
        
        Arrays.sort(e);
        b = new int[n];
        getBetsy();
        
        
//        System.out.println(Arrays.toString(b));
        int last, ans, numCards;
        
        last = ans = numCards = 0;
        for (int i=0; i<n; i++) {
            numCards += b[i] - last - 1;
            if (numCards > 0) {
                ++ans;
                --numCards;
            }
            
            last = b[i];
        }
        
        pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
        pw.println(ans);
        pw.close();
//        System.out.println(ans);
    }
    
    void runIO() throws IOException {
        BufferedReader bf;
        
        bf = new BufferedReader(new FileReader("highcard.in"));
        
        n = Integer.parseInt(bf.readLine());
        
        e = new int[n];  // elseie's cards
        for (int i=0; i<n; i++) {
            e[i] = Integer.parseInt(bf.readLine());
        }
        
        solve();
    }
    
    public static void main(String[] args) throws IOException { new highcard().runIO(); }
    
}
