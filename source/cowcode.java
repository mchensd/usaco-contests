/*
USACO JAN 2017 SILVER 3
 */

/**
 *
 * @author Michael
 */
import java.util.*;
import java.io.*;
public class cowcode {
    long n;
    void run() throws IOException {
        BufferedReader bf;
        PrintWriter pw;
        
        bf = new BufferedReader(new FileReader("cowcode.in"));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String base = st.nextToken();
        int len = base.length();
        
        n = Long.parseLong(st.nextToken());
        
//        long l = 0;
        
        long lb =  len;
        
        while (n > lb) {
//            ++l;
            lb *= 2;
//            lb = Long.lb << 1);
        }
//        --l;
        lb /= 2;
        while (n > len) {
            long pos = n - lb;
            if (pos == 1) n = lb;
            else {
                n = pos - 1;
            }
            while (lb >= n) lb /= 2;
        }
        
        pw = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
//        System.out.println(n);
        pw.println(base.charAt((int) n-1));
        pw.close();
//        System.out.println(base.charAt((int) n-1));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException { new cowcode().run(); }
    
}
