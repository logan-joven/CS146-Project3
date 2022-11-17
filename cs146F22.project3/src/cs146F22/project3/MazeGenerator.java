//package src.project3;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * recursive backtracking algorithm
 * shamelessly borrowed from the ruby at
 * http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
 */
public class MazeGenerator {
	//coordinates row x, column y
	private final int x;
	private final int y;
	//stores the cells
	private final int[][] maze;
	
	//stores the adjacency list
	ArrayList<ArrayList<Integer> > adj;

	//Constructor
	public MazeGenerator(int x, int y) {
		this.x = x;
		this.y = y;
		maze = new int[this.x][this.y];
		generateMaze(0, 0);
		
	}
	//prints the maze with the cells and walls removed 
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
	//recursive perfect maze generator, using a modified DFS
	//(cx,cy) coordinates of current cell, and (nx,ny) coordinates of neighbor cell
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
	//prints the value of maze array
	public void displayCells() {
		for (int i=0;i<x;i++) {
			for (int j=0;j<y;j++)
				System.out.print(" "+ maze[i][j]);
			System.out.println();
			}
	}	
		
    //checks if 0<=v<upper
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
				// TODO: come up with something better?
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
				//System.out.println(vertex);
			}
		}
		// print adjacency lists
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("\nAdjacency list of vertex "
                               + i);
            System.out.print("head");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> "
                                 + adj.get(i).get(j));
            }
            System.out.println();
        }
	}

	
	public void BFS() {
		// initialize
		int V = x * y; // V vertices, equal to rows * columns
		int visited[] = new int[V]; // 0 = not visited, 1 = discovered, 2 = finished
		int discovered[] = new int[V]; // discovery time
		int parent[] = new int[V]; // index of parent in adj
		for (int i = 0; i < V; i++) {
			visited[i] = 0; // all are not visited at start
			discovered[i] = Integer.MAX_VALUE; // infinity
			parent[i] = -1; // index outside of adj
		}
		
		visited[0] = 1; // set first to grey
		discovered[0] = 0; // distance to itself
		parent[0] = -1; // no parent
		
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		while (q.size() != 0) {
			Integer u = q.remove();
			for (Integer i : adj.get(u)) {
				if (visited[i] == 0) { // if undiscovered
					visited[i] = 1; // if discovered but not finished
					discovered[i] = discovered[u] + 1;
					//want a discovered that increments the counter
					//counter = 0, everytime we discover something increment
					//an array that is the distance can be set to the current discovered
					parent[i] = u;
					q.add(i);
				}
			}
			visited[u] = 2; // finished
		}
		System.out.println("Visited: " );
		for (int i = 0; i < V; i++) {
			System.out.print(visited[i] + " ");
		}
		System.out.println("\nDiscovered: " );
		for (int i = 0; i < V; i++) {
			System.out.print(discovered[i] + " ");
		}
		System.out.println("\nParent: " );
		for (int i = 0; i < V; i++) {
			System.out.print(parent[i] + " ");
		}
	}

//
	public void DFS() {
    	int V = x*y;
		int visited[] = new int[V]; // 0 = not visited, 1 = discovered, 2 = finished
		int discovered[] = new int[V];
		int parent[] = new int[V]; // index of parent in adj

		for(int i = 0; i<V;i++) {
			visited[i] = 0; //set it to 0, to indicate unvisited
			parent[i] = -1; //set to -1 to indicate no parents.
		}

		for(int j = 0; j<V; j++){
			if(visited[j] == 0){
				visit(j);
				}
			}
		}


// Need to fix this method
	public void visit(int a){ //have an array as the parameter
		//DELETE THIS
//		int V = x*y;
//		int visited[] = new int[V]; // 0 = not visited, 1 = discovered, 2 = finished
//		int discovered[] = new int[V];
//		int parent[] = new int[V]; // index of parent in adj
			//DELETE THESE
//
//    	visited[a]= 1;
//    	discovered[a] +=1;
//    	visited[discovered[a]] = visited[a];
//
//		for (Integer i : adj.get(a)) {
//			if(visited[i] == 0){
//				parent[i] = a;
//				visit(parent[i]);
//			}
//			visited[i] = 2;
//			discovered[i] += 1;
//
	}
//
//
//
//
//	}

	public static void main(String[] args) {
		
		MazeGenerator maze33 = new MazeGenerator(3, 3);
		
		maze33.displayCells();
	    maze33.displayMaze();
	    maze33.createArray();
	    maze33.BFS();
	    
	    
//	    MazeGenerator maze55 = new MazeGenerator(5, 5);
//		
//		maze55.displayCells();
//	    maze55.displayMaze();
//	    maze55.createArray();
//	    maze55.BFS();
	}

}