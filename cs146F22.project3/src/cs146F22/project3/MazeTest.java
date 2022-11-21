package cs146F22.project3;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class MazeTest {

	@Test
	public void test4x4() throws IOException {
		File maze = new File("maze4.txt");

		MazeGenerator test = new MazeGenerator(maze);
		test.createArray();
		test.BFS();
		test.DFS();
		
		List<String> BFSsolution = Files.readAllLines(Paths.get("maze4BFSsolution.txt"));
		List<String> BFSoutput = Files.readAllLines(Paths.get("BFSoutput.txt"));
		List<String> DFSsolution = Files.readAllLines(Paths.get("maze4DFSsolution.txt"));
		List<String> DFSoutput = Files.readAllLines(Paths.get("DFSoutput.txt"));
		
		assertEquals(BFSsolution, BFSoutput); // check if BFS matches solution
		assertEquals(DFSsolution, DFSoutput); // check if DFS matches solution
	}
	
	@Test
	public void test6x6() throws IOException {
		File maze = new File("maze6.txt");

		MazeGenerator test = new MazeGenerator(maze);
		test.createArray();
		test.BFS();
		test.DFS();
		
		List<String> BFSsolution = Files.readAllLines(Paths.get("maze6BFSsolution.txt"));
		List<String> BFSoutput = Files.readAllLines(Paths.get("BFSoutput.txt"));
		List<String> DFSsolution = Files.readAllLines(Paths.get("maze6DFSsolution.txt"));
		List<String> DFSoutput = Files.readAllLines(Paths.get("DFSoutput.txt"));
		
		assertEquals(BFSsolution, BFSoutput); // check if BFS matches solution
		assertEquals(DFSsolution, DFSoutput); // check if DFS matches solution
	}
	
	@Test
	public void test8x8() throws IOException {
		File maze = new File("maze8.txt");

		MazeGenerator test = new MazeGenerator(maze);
		test.createArray();
		test.BFS();
		test.DFS();
		
		List<String> BFSsolution = Files.readAllLines(Paths.get("maze8BFSsolution.txt"));
		List<String> BFSoutput = Files.readAllLines(Paths.get("BFSoutput.txt"));
		List<String> DFSsolution = Files.readAllLines(Paths.get("maze8DFSsolution.txt"));
		List<String> DFSoutput = Files.readAllLines(Paths.get("DFSoutput.txt"));
		
		assertEquals(BFSsolution, BFSoutput); // check if BFS matches solution
		assertEquals(DFSsolution, DFSoutput); // check if DFS matches solution
	}
	
	@Test
	public void test10x10() throws IOException {
		File maze = new File("maze10.txt");

		MazeGenerator test = new MazeGenerator(maze);
		test.createArray();
		test.BFS();
		test.DFS();
		
		List<String> BFSsolution = Files.readAllLines(Paths.get("maze10BFSsolution.txt"));
		List<String> BFSoutput = Files.readAllLines(Paths.get("BFSoutput.txt"));
		List<String> DFSsolution = Files.readAllLines(Paths.get("maze10DFSsolution.txt"));
		List<String> DFSoutput = Files.readAllLines(Paths.get("DFSoutput.txt"));
		
		assertEquals(BFSsolution, BFSoutput); // check if BFS matches solution
		assertEquals(DFSsolution, DFSoutput); // check if DFS matches solution
	}
	
	@Test
	public void test20x20() throws IOException {
		File maze = new File("maze20.txt");

		MazeGenerator test = new MazeGenerator(maze);
		test.createArray();
		test.BFS();
		test.DFS();
		
		List<String> BFSsolution = Files.readAllLines(Paths.get("maze20BFSsolution.txt"));
		List<String> BFSoutput = Files.readAllLines(Paths.get("BFSoutput.txt"));
		List<String> DFSsolution = Files.readAllLines(Paths.get("maze20DFSsolution.txt"));
		List<String> DFSoutput = Files.readAllLines(Paths.get("DFSoutput.txt"));
		
		assertEquals(BFSsolution, BFSoutput); // check if BFS matches solution
		assertEquals(DFSsolution, DFSoutput); // check if DFS matches solution
	}

}
