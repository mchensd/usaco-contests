/*
USACO JAN 2017 SILVER 1
 */

/**
 *
 * @author Michael
 */
import java.util.*;
import java.io.*;

public class cowdance {
    int n, T, k, li, ui;
    int[] times;
    
    boolean canSolve() {
        int time = 0;
        int max = 0; // for the end
        
//        int onStage = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i=0; i<k; i++) {
            pq.add(times[i]);
            max = Math.max(max, times[i]);
        }
        for (int i=k; i<n; i++) {
            
            time = pq.poll();
            int tmpTime = times[i] + time;
            max = Math.max(max, tmpTime);
            pq.add(tmpTime);
            
        }
        
        return (max <= T);
    }
    void run() throws IOException {
        BufferedReader bf;
        PrintWriter pw;
        StringTokenizer st;
        
        bf = new BufferedReader(new FileReader("cowdance.in"));
        
        st = new StringTokenizer(bf.readLine());
        
        n = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        times = new int[n];
        for (int i=0; i<n; i++) {
            times[i] = Integer.parseInt(bf.readLine());
        }
        
        li = 1;
        ui = n;
        
        while (true) {
            if (li == ui) break;
            k = (li + ui) / 2;

            if (canSolve()) {
                ui = k;
            }
            
            else {
                li = k+1;
            }
        }
        
        pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        pw.println(ui);
        pw.close();
//        System.out.println(ui);
    }
    public static void main(String[] args) throws IOException{ new cowdance().run(); }
    
}
