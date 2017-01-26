/*
ID: mchensd
LANG: JAVA
PROG: citystate
 */
// USACO December 2016 Silver 2
/**
 *
 * @author Michael
 */
import java.io.*;
import java.util.*;

public class citystate {
    
    public static int compareCityStates(String state, String city, String[] locs) {
        // return 1 if the city and states match, else 0
        // there can only be one state with the same given city name
        //search for that state because locs is sorted by states
        int count = 0;
        int n = locs.length;
        int li = 0;
        int ui = n-1;
        int xs = (li+ui) / 2;
        
        while (true) {
            if (li == ui) {
                if (locs[li].substring(0, 2).equals(city)) {
                    return (locs[xs].substring(2,4).equals(state)) ? 1 : 0;
                }
                else return 0;
            } // no state with matching city name
            if (city.equals(locs[xs].substring(0, 2))) {// found matching state!
                // check if that states city matches given state
                break; // need to check if multiple states with same name
                // if (locs[xs].substring(2,4).equals(state)) return 1;
            }
            
            if (city.compareTo(locs[xs].substring(0,2)) < 0) { // comes before, need to move up li
                
                ui = xs;
                xs = (li + ui) / 2;
            }
            
            else if (city.compareTo(locs[xs].substring(0,2)) > 0) { // comes after, move down ui
               li = xs + 1;
               xs = (li + ui) / 2;
            }
        } // end while
        int tmp = xs;

        while (tmp < locs.length && locs[tmp].substring(0,2).equals(city)) {
            if (locs[tmp].substring(2,4).equals(state)) ++count;
            ++tmp;
        }
        tmp = xs-1;
        while ( tmp >= 0 && locs[tmp].substring(0,2).equals(city)) {
            if (locs[tmp].substring(2,4).equals(state)) ++count;
            --tmp;
        }
            
         // end while
        return count;
    } // end funct
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader bf;
        PrintWriter pw;
        StringTokenizer st;
        String[] myLocs = {"FLMI", "MIFL"};
        compareCityStates("MI","FL",myLocs);
        //System.out.println(compareCityStates("FL", "MI", myLocs));
        
        bf = new BufferedReader(new FileReader("citystate.in"));
        int n = Integer.parseInt(bf.readLine());
        
        String[] locs = new String[n]; // locations
        for (int i=0; i<n; i++)
        {
            st = new StringTokenizer(bf.readLine());
            String city = st.nextToken().substring(0,2);
            String state = st.nextToken();
            locs[i] = state+city;
        }
        
        Arrays.sort(locs);
        //System.out.println(Arrays.toString(locs));
        int count=0;
        for (int i=0; i<n; i++)
        {
            String[] tmpArray = new String[n-1];
            System.arraycopy(locs, 0, tmpArray, 0, i);
            System.arraycopy(locs, i+1, tmpArray, i, n-1-i);
            //System.out.println(Arrays.toString(tmpArray));
            count += compareCityStates(locs[i].substring(0,2), locs[i].substring(2,4), tmpArray);
        }
        
        pw = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
        pw.println(count/2);
        pw.close();
    } // end main
    
}
