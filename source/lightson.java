/*
USACO Dec 2015 Silver 1
 */

/**
 *
 * @author Michael
 */
import java.util.*;
import java.io.*;

public class lightson {
    int n, m, ans;
    Room[][] rooms;
    Queue<Room> q;
    Room[] lit;
    
    Room[] getNeighbours(Room room) {
        int i = 0;
        Room[] neighbours = new Room[4];
        int x = room.x;
        int y = room.y;
        for (int col=room.x-1; col<=room.x+1; col+=2) {
            if (col >= 1 && col <= n) neighbours[i++] = rooms[col][y];
        }
        
        for (int row=room.y-1; row<=room.y+1; row+=2) {
            if (row >= 1 && row <= n) neighbours[i++] = rooms[x][row];
        }
        
        return neighbours;
    }
    boolean hasVisitedDifferent(Room room, Room origin) {
        int i = 0;
        Room[] neighbours = getNeighbours(room);
        
        Room neighbour;
        boolean has = false;
        while (i < 4 && (neighbour = neighbours[i++]) != null) {
            if (neighbour == origin) return false;
            if (neighbour.visited && neighbour != origin) has = true;  // need to check all neighbors, can't return yet
        }
        
        return has;
        /*
        for (int col=room.x-1; col<=room.x+1; col+=2) {
            if (col >= 1 && col <= n) {
                Room neighbour = rooms[col][room.y];
                if(neighbour.visited && neighbour != origin) {
                    return true;
                }
            }
        }
        
        for (int row=room.y-1; row<=room.y+1; row+=2) {
            if (row >=1 && row <=n) {
                Room neighbour = rooms[room.x][row];
                if (neighbour.visited && neighbour != origin) {
                    return true;
                }
            }
        }
        return false;*/
    }
    
    void checkNeighbours(Room room) {
        Room[] neighbours = getNeighbours(room);
        
        int i = 0;
        Room neighbour;
        while (i < 4 && (neighbour = neighbours[i++]) != null) {
            if (neighbour.isLit && !neighbour.visited) q.add(neighbour);
        }
    }
    
    void solve(Room room) {
//        System.out.println(room.x);
//        System.out.println(room.y);
        room.visit();
//        System.out.println("in solve");
        for (int i=0; i<room.switches.size(); i++) {
            Room tmp = room.switches.get(i);
            if (!tmp.visited && !tmp.isLit) {
                tmp.setLit();
                ++ans;

                if (hasVisitedDifferent(tmp, room)) q.add(tmp);  // if tmp has visited neighbours which aren't room 
                //(will add neighbours of room later)
            }
        }
        
        checkNeighbours(room); // add lit unvisited neighbors to the queue
        
    }
    
//    static boolean[][] visited, lit;
    void run() throws IOException {
        BufferedReader bf;
        StringTokenizer st;
        PrintWriter pw;
        /*
        rooms = new Room[5][5];
        n=4;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                rooms[i][j] = new Room(i, j);
            }
        }
        Room[] neighbours = getNeighbours(rooms[2][2]);*/
        
        bf = new BufferedReader(new FileReader("lightson.in"));

        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        rooms = new Room[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                rooms[i][j] = new Room(i, j);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a, b, x, y;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            rooms[a][b].addSwitch(rooms[x][y]);
        }

        q = new LinkedList<>();
        q.add(rooms[1][1]);
        
        ans = 1;
        while (!q.isEmpty()) {
            solve(q.remove());
        }
        pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
        pw.println(ans);
        pw.close();
        System.out.println(ans);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{ new lightson().run(); }
    
    static class Room {

        int x, y;
        public boolean visited, isLit;
        public ArrayList<Room> switches;

        public Room(int x, int y) {
            this.x = x;
            this.y = y;
            switches = new ArrayList<>();
        }

        public void setLit() {
            this.isLit = true;
        }

        public void visit() {
            this.visited = true;
        }
        
        public void addSwitch(Room other) {
            // this room has a switch that lights up other the other room
            switches.add(other);
        }
        /*
        public void lightAll() {
            for (int i=0; i<switches.size(); i++) {
                switches.get(i).setLit();
            }
        }*/
    }
    
}
