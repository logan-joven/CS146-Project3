package cs146F22.project3;



import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;


public class MazeTest {


    @Test
    public void testBFS()throws FileNotFoundException{
        int x = 3;
        int y = 3;
        Scanner sc = new Scanner(new File("/Users/jeffreyvan/Documents/GitHub/cs146F22.project3/cs146F22.project3/src/cs146F22/project3/testmaze.txt"));
        int [][]arr = new int[3][3];
        for(int i = 0 ; i<3; i++){
            for(int j =0; j<3;j++){
                arr[i][j] = sc.nextInt();
            }
        }
       MazeGenerator testMaze = new MazeGenerator(arr,x,y);
        testMaze.createArray();
        testMaze.BFS();
        assertEquals(6, testMaze.tiles);

    }

    @Test
    public void testDFS()throws FileNotFoundException{
        int x = 3;
        int y = 3;
        Scanner sc = new Scanner(new File("/Users/jeffreyvan/Documents/GitHub/cs146F22.project3/cs146F22.project3/src/cs146F22/project3/testmaze.txt"));
        int [][]arr = new int[3][3];
        for(int i = 0 ; i<3; i++){
            for(int j =0; j<3;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        MazeGenerator testMaze = new MazeGenerator(arr,x,y);
        testMaze.createArray();
        testMaze.DFS();
        assertEquals(5, testMaze.tiles);


    }

    @Test
    public void testBFSFive()throws FileNotFoundException{
        int x = 5;
        int y = 5;
        Scanner sc = new Scanner(new File("/Users/jeffreyvan/Documents/GitHub/cs146F22.project3/cs146F22.project3/src/cs146F22/project3/testMaze2.txt"));
        int [][]arr = new int[5][5];
        for(int i = 0 ; i<x; i++){
            for(int j =0; j<y;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        MazeGenerator testMaze = new MazeGenerator(arr,x,y);
        testMaze.createArray();
        testMaze.BFS();
        assertEquals(12, testMaze.tiles);


    }
    @Test
    public void testDFSFive()throws FileNotFoundException{
        int x = 5;
        int y = 5;
        Scanner sc = new Scanner(new File("/Users/jeffreyvan/Documents/GitHub/cs146F22.project3/cs146F22.project3/src/cs146F22/project3/testMaze2.txt"));
        int [][]arr = new int[5][5];
        for(int i = 0 ; i<x; i++){
            for(int j =0; j<y;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        MazeGenerator testMaze = new MazeGenerator(arr,x,y);
        testMaze.createArray();
        testMaze.DFS();
        assertEquals(12, testMaze.tiles);


    }
    @Test
    public void testPathBFS()throws FileNotFoundException {
        int x = 5;
        int y = 5;
        Scanner sc = new Scanner(new File("/Users/jeffreyvan/Documents/GitHub/cs146F22.project3/cs146F22.project3/src/cs146F22/project3/testMaze2.txt"));
        int[][] arr = new int[5][5];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        MazeGenerator testMaze = new MazeGenerator(arr, x, y);
        testMaze.createArray();
        testMaze.BFS();
        assertEquals(11, testMaze.pathLength);
    }

    @Test
    public void testPathDFS()throws FileNotFoundException {
        int x = 5;
        int y = 5;
        Scanner sc = new Scanner(new File("/Users/jeffreyvan/Documents/GitHub/cs146F22.project3/cs146F22.project3/src/cs146F22/project3/testMaze2.txt"));
        int[][] arr = new int[5][5];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        MazeGenerator testMaze = new MazeGenerator(arr, x, y);
        testMaze.createArray();
        testMaze.DFS();
        assertEquals(11, testMaze.pathLength);
    }


    @Test
    public void testPathBFSThree()throws FileNotFoundException {
        int x = 3;
        int y = 3;
        Scanner sc = new Scanner(new File("/Users/jeffreyvan/Documents/GitHub/cs146F22.project3/cs146F22.project3/src/cs146F22/project3/testmaze.txt"));
        int[][] arr = new int[3][3];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        MazeGenerator testMaze = new MazeGenerator(arr, x, y);
        testMaze.createArray();
        testMaze.BFS();
        assertEquals(5, testMaze.pathLength);
    }

    @Test
    public void testPathDFSThree()throws FileNotFoundException {
        int x = 3;
        int y = 3;
        Scanner sc = new Scanner(new File("/Users/jeffreyvan/Documents/GitHub/cs146F22.project3/cs146F22.project3/src/cs146F22/project3/testmaze.txt"));
        int[][] arr = new int[3][3];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        MazeGenerator testMaze = new MazeGenerator(arr, x, y);
        testMaze.createArray();
        testMaze.DFS();
        assertEquals(5, testMaze.pathLength);
    }



}





