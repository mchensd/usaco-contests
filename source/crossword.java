/*
ID: mchensd
LANG: JAVA
PROG: crossword
 */

// 2014 Bronze #2 

/**
 *
 * @author Michael
 */
import java.util.*;
import java.io.*;
public class crossword {
    
    /**
     * @param puzzle the 2d array of the crossword board
     * @param i the row of the grid we are checking
     * @param j column of grid we are checking
     * @param n total number of rows
     * @param m total number of columns 
     * @param isHoriz can the clue go horizontal (is the piece to the left blocked)
     * @param isVert can the clue go vertical (is the piece directly upward blocked)
     * @return if the grid at puzzle[i][j] can form a clue
     */
    public static boolean clueCheck(boolean[][] puzzle, int i, int j, int n, int m, boolean isHoriz, boolean isVert) {
        //System.out.printf("%d %d %d %d\n", i, j, n, m);
        if (isHoriz && m-j > 2 && puzzle[i][j+1] && puzzle[i][j+2]) {
            return true;
        } // end if
        
        else if (isVert && n-i > 2 && puzzle[i+1][j] && puzzle[i+2][j]) {
            return true;
        }
        
        return false;
    } // end clueCheck
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader bf;
        PrintWriter pw;
        StringTokenizer st;
        
        bf = new BufferedReader(new FileReader("crosswords.in"));
        st = new StringTokenizer(bf.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        boolean[][] puzzle = new boolean[n+1][m+1];
        for (int i=1; i<n+1; i++)
        {
            st = new StringTokenizer(bf.readLine().replaceAll("", " "));
            for (int j=1; j<m+1; j++)
            {
                // System.out.println(j);
                if (st.nextToken().equals(".")) puzzle[i][j] = true;
            }
        } // outer for
        
        //MyUtil.print2d(puzzle, n+1, m+1);
        
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        int numClues = 0;
        
        for (int i=1; i<n+1; i++)
        {
            for (int j=1; j<m+1; j++)
            {
                boolean isHoriz = false;  // can the word be horizontal
                boolean isVert = false;  // can the word be vertical
                boolean isClue; // can the word be a clue
                if (puzzle[i][j]) {
                    isHoriz = !puzzle[i][j-1];
                    isVert = !puzzle[i-1][j];
                }
                
                isClue = clueCheck(puzzle, i, j, n+1, m+1, isHoriz, isVert); // checks vertical and horizontal for clues
                if (isClue) {
                    numClues++;
                    xs.add(i);
                    ys.add(j);
                }
            } // inner for
        } // outer for
        //System.out.println(xs);
        //System.out.println(numClues);
        pw = new PrintWriter(new BufferedWriter(new FileWriter("crosswords.out")));
        pw.println(numClues);
        for (int i=0; i<numClues; i++)
        {
            pw.printf("%d %d", xs.get(i), ys.get(i));
            pw.println();
        }
        pw.close();
        
    } // main
    
}
