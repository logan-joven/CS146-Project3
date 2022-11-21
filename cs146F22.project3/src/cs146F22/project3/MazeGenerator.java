package cs146F22.project3;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * recursive backtracking algorithm
 * shamelessly borrowed from the ruby at
 * http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
 */
public class MazeGenerator {
	// coordinates row x, column y
	private final int x;
	private final int y;
	// stores the cells
	private final int[][] maze;
	
	// stores the adjacency lists
	ArrayList<ArrayList<Integer> > adj;
	
	// stores the time and finished flag for DFS
    public int time = 0;
    public boolean DFSfinished = false;
    
    //stores the shortest path
    ArrayList<Integer> path;
    
	// Constructor
	public MazeGenerator(int x, int y) {
		this.x = x;
		this.y = y;
		maze = new int[this.x][this.y];
		generateMaze(0, 0);
	}
	
	// constructor that reads a file to create the maze instead of generating a random one
	// 1st line is the dimensions
	// 2nd line contains the directions of cells
	public MazeGenerator(File file) throws FileNotFoundException {
		try (Scanner s = new Scanner(file)) { // read dimensions
			this.x = s.nextInt();
			this.y = s.nextInt();
			
			maze = new int[this.x][this.y]; // create the maze
			for (int i = 0; i < y; i++) { // read the maze, and turn it into 2D array of directions
				for (int j = 0; j < x ;j++) {
					maze[i][j] = s.nextInt();
				}
			}
		}
	}
	
	// prints the maze with the cells and walls removed 
	public void displayMaze() {
		for (int i = 0; i < y; i++) {
			// draw the north edge
			for (int j = 0; j < x; j++) {
				System.out.print((maze[i][j] & 1) == 0 ? "+---" : "+   ");
			}
			System.out.println("+");
			// draw the west edge
			for (int j = 0; j < x; j++) {
				System.out.print((maze[i][j] & 8) == 0 ? "|   " : "    ");
			}
			System.out.println("|");
		}
		// draw the bottom line
		for (int j = 0; j < x; j++) {
			System.out.print("+---");
		}
		System.out.println("+");	
	}
	
	// recursive perfect maze generator, using a modified DFS
	// (cx,cy) coordinates of current cell, and (nx,ny) coordinates of neighbor cell
	private void generateMaze(int cx, int cy) {
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));
		for (DIR dir : dirs) {
			//find neighbor cell
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			//if neighbor exists and not visited
			if (between(nx, x) && between(ny, y)
					&& (maze[nx][ny] == 0)) {
				//remove walls
				//update current cell using or (|) bit operations
				//example if a cell has north (1) and south (2) neighbor openings, maze holds 3
				//example if a cell has east (4) and west (8) neighbor openings, maze holds 12
				
				maze[cx][cy] |= dir.bit;
				//update neighbor cell
				maze[nx][ny] |= dir.opposite.bit;
				//recursive call to neighbor cell
				generateMaze(nx, ny);
			}
		}
	}
	
	// prints the value of maze array
	public void displayCells() {
		for (int i=0;i<x;i++) {
			for (int j=0;j<y;j++)
				System.out.print(" "+ maze[i][j]);
			System.out.println();
			}
	}	
		
    // checks if 0<=v<upper
	private static boolean between(int v, int upper) {
		return (v >= 0) && (v < upper);
	}

	// enum type for all directions 
	private enum DIR {
		//direction(bit, column move, row move)
		//bit 1 is North, 2 is South, 4 is East and 8 is West
		//example North N(1,0,-1).
		N(1, -1, 0), S(2, 1, 0), E(4, 0, 1), W(8, 0, -1);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIR opposite;

		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}

		private DIR(int bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};
	
	// u is index of first vertex, v is index of 2nd vertex
    static void addEdge(ArrayList<ArrayList<Integer> > adj, int u, int v) {
    	
    	adj.get(u).add(v);
    	}
	
	// create the array of linked list for graph representation
	public void createArray() {
		int V = x * y; // V vertices, equal to rows * columns
	    adj = new ArrayList<ArrayList<Integer> >(V);
	    
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<Integer>());
        
	    int vertex = 0;
	    // add edges 
		for (int i = 0; i < x; i++) { // row x
			for (int j = 0; j < y; j++) { // column y
				if (maze[i][j] == 1) // neighbor north
					addEdge(adj, vertex, vertex - y);
				else if (maze[i][j] == 2) // neighbor south
					addEdge(adj, vertex, vertex + y);
				else if (maze[i][j] == 3) { // neighbor south and north
					addEdge(adj, vertex, vertex + y); // south
					addEdge(adj, vertex, vertex - y); // north
				}
				else if (maze[i][j] == 4) // neighbor east
					addEdge(adj, vertex, vertex + 1);
				else if (maze[i][j] == 5) {// neighbor east and north
					addEdge(adj, vertex, vertex + 1); // east
					addEdge(adj, vertex, vertex - y); // north
				}
				else if (maze[i][j] == 6) { // neighbor east and south
					addEdge(adj, vertex, vertex + 1); // east
					addEdge(adj, vertex, vertex + y); // south
				}
				else if (maze[i][j] == 7) { // neighbor east south north
					addEdge(adj, vertex, vertex + 1); // east
					addEdge(adj, vertex, vertex + y); // south
					addEdge(adj, vertex, vertex - y); // north
				}
				else if (maze[i][j] == 8) // neighbor west
					addEdge(adj, vertex, vertex - 1);
				else if (maze[i][j] == 9) { // neighbor west and north
					addEdge(adj, vertex, vertex - 1); // west
					addEdge(adj, vertex, vertex - y); // north
				}
				else if (maze[i][j] == 10) { // neighbor west and south
					addEdge(adj, vertex, vertex - 1); // west
					addEdge(adj, vertex, vertex + y); // south
				}
				else if (maze[i][j] == 11) { // neighbor west south north
					addEdge(adj, vertex, vertex - 1); // west
					addEdge(adj, vertex, vertex + y); // south
					addEdge(adj, vertex, vertex - y); // north
				}
				else if (maze[i][j] == 12) { // neighbor west and east
					addEdge(adj, vertex, vertex - 1); // west
					addEdge(adj, vertex, vertex + 1); // east
				}
				else if (maze[i][j] == 13) { // neighbor west east north
					addEdge(adj, vertex, vertex - 1); // west
					addEdge(adj, vertex, vertex + 1); // east
					addEdge(adj, vertex, vertex - y); // north
				}
				else if (maze[i][j] == 14) { // neighbor west east south
					addEdge(adj, vertex, vertex - 1); // west
					addEdge(adj, vertex, vertex + 1); // east
					addEdge(adj, vertex, vertex + y); // south
				}
			// impossible combinations of bits are 0, 15
				vertex++;
			}
		}
		// print adjacency lists
//        for (int i = 0; i < adj.size(); i++) {
//            System.out.println("\nAdjacency list of vertex "
//                               + i);
//            System.out.print("head");
//            for (int j = 0; j < adj.get(i).size(); j++) {
//                System.out.print(" -> "
//                                 + adj.get(i).get(j));
//            }
//            System.out.println();
//        }
	}

	// perform BFS, then print output to file
	public void BFS() {
		// initialize
		int V = x * y; // V vertices, equal to rows * columns
		int visited[] = new int[V]; // 0 = not visited, 1 = discovered, 2 = finished
		int distance[] = new int[V]; // distance from origin 0, not used for this project
		int parent[] = new int[V]; // index of parent
		int discovered[] = new int[V]; // discovery time
		for (int i = 0; i < V; i++) {
			visited[i] = 0; // all are not visited at start
			distance[i] = Integer.MAX_VALUE; // infinity
			parent[i] = -1; // index outside of adj
			discovered[i] = 0; // discovery time
		}
		
		visited[0] = 1; // set first to grey
		distance[0] = 0; // distance to itself
		parent[0] = -1; // no parent
		discovered[0] = 0; // discovered first
		
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		boolean finished = false; // do not continue BFS if we reach the end
		int counter = 1; // used for discovery time
		while (q.size() != 0 && !finished) {
			Integer u = q.remove();
			for (Integer i : adj.get(u)) {
				if((u) == adj.size()-1){ // end of maze is V - 1
                    finished = true;
                    q.clear(); // clear queue 
                }
				if (visited[i] == 0 && !finished) { // if undiscovered and end of maze not reached
					visited[i] = 1; // discovered but not finished
					distance[i] = distance[u] + 1;
					parent[i] = u;
					discovered[i] = counter;
					counter++;
					q.add(i);
				}
			}
			visited[u] = 2; // finished
		}
		
		
		File file = new File("BFSoutput.txt"); // write to file
		PrintStream stdout = System.out; // save previous output object
		try {
			PrintStream stream = new PrintStream(file);
			System.setOut(stream); // change output to file instead of console
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	      
		System.out.println("\nBFS:");
		
		path = new ArrayList<Integer> ();
		shortestPath(0, V - 1, parent); // calculate shortest path
		printMaze(discovered, visited); // print maze with cells containing order discovered
		printMazeShortestPath(visited); // print maze with shortest path as #'s
		
		System.out.println("Path: ");
		System.out.println(path.toString()); // print path as an array
		System.out.println("Length of path: " + path.size()); // print size of path
		
		int visitedCounter = 0;
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] != 0)
				visitedCounter++;
		}		
		System.out.println("Visited cells: " + visitedCounter); // print # of visited cells
		
		System.setOut(stdout); // set path back to console
	}
    
	// perform DFS, then print results to file
    public void DFS() {
		// initialize
		int V = x * y; // V vertices, equal to rows * columns
		int visited[] = new int[V]; // 0 = not visited, 1 = discovered, 2 = finished
		int parent[] = new int[V]; // index of parent in adj
		int discovered[] = new int[V]; // discovery time
		
		// initialize the graph
		for (int i = 0; i < V; i++) {
			visited[i] = 0; // all are not visited at start
			parent[i] = -1; // index outside of adj
			discovered[i] = 0; // discovery time
		}
		
		// call visit on each vertex until end of maze 
        for(int u = 0; u < V; u++){
            if(visited[u] == 0 && !DFSfinished){
                visit(visited, parent, discovered, u);
                }
            }
        
		File file = new File("DFSoutput.txt"); // write to file
		PrintStream stdout = System.out; // save previous output object
		try {
			PrintStream stream = new PrintStream(file);
			System.setOut(stream); // change output to file instead of console
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	      
		System.out.println("\nDFS:");
		
		path = new ArrayList<Integer> ();
		shortestPath(0, V - 1, parent); // calculate shortest path
		printMaze(discovered, visited); // print maze with cells containing order discovered
		printMazeShortestPath(visited); // print maze with shortest path as #'s
		
		System.out.println("Path: ");
		System.out.println(path.toString()); // print path as an array
		System.out.println("Length of path: " + path.size()); // print size of path
		
		int visitedCounter = 0;
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] != 0)
				visitedCounter++;
		}		
		System.out.println("Visited cells: " + visitedCounter); // print # of visited cells
		
		System.setOut(stdout); // set path back to console
    }

    // recursive call in DFS
    public void visit(int visited[], int parent[], int discovered[], int u){
    	
    	visited[u] = 1; // make grey
    	time += 1;
    	discovered[u] = time;
    	
    	for (Integer i : adj.get(u)) {
    		if (visited[i] == 0 && !DFSfinished) {
    			if((u) == adj.size()-1){
                    DFSfinished = true;
                }
    			if (!DFSfinished) {
    				parent[i] = u;
    				visit(visited, parent, discovered, i);
    			}
    		}
    	}
    	visited[u] = 2;	
    }
	
    //  prints the maze with discovered cells in order (1s place only)
	void printMaze (int[] discovered, int[] visited) {
		int count = 0;
		for (int i = 0; i < y; i++) {
			// draw the north edge
			for (int j = 0; j < x; j++) {
				System.out.print((maze[i][j] & 1) == 0 ? "+---" : "+   ");
			}
			System.out.println("+");
			// draw the west edge
			for (int j = 0; j < x; j++) {
				if (visited[count] == 0)
					System.out.print((maze[i][j] & 8) == 0 ? "|   " : "    ");
				else
					System.out.print((maze[i][j] & 8) == 0 ? "| " + discovered[count]%10 +  " " : "  " + discovered[count]%10 + " " );
				count++;
			}
			System.out.println("|");
		}
		// draw the bottom line
		for (int j = 0; j < x; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}
	
	// prints maze with shortest path as #'s
    void printMazeShortestPath (int[] visited) {
        int count = 0;
        for (int i = 0; i < y; i++) {
            // draw the north edge
            for (int j = 0; j < x; j++) {
                System.out.print((maze[i][j] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < x; j++) {
                if(path.contains(count)) { // if the vertex is in path
                    System.out.print((maze[i][j] & 8) == 0 ? "| " + "#" + " " : "  " + "#"  + " ");
                }
                else {
                    System.out.print((maze[i][j] & 8) == 0 ? "| " + " " + " " : "  " + " "  + " ");
                }
                count++;
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < x; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
    
    // finds the shortest path from s to v, based on parents
    void shortestPath(int s, int v, int[] parent) {
        if (v == s) {
            path.add(s);
        }
        else if (parent[v] == -1)
            System.out.println("no path");
        else {
            shortestPath(s, parent[v], parent);
            path.add(v);
        }
    }

}