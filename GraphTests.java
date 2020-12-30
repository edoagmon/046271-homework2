package homework2;

import org.junit.Test;
import java.io.*;


//import com.sun.org.apache.xpath.internal.operations.NotEquals;

import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}
	
	@Test
	public void testGraph() {
		Graph<WeightedNode> graph1 = new Graph<>("New York");
		Graph<WeightedNode> graph2 = new Graph<>("Bat Yam");
		WeightedNode node1NY = new WeightedNode("Candy Str", 25);
		WeightedNode node2NY = new WeightedNode("Mia Kalifa Str", 25);
		WeightedNode node3NY = new WeightedNode("Sasha Gray Str", 5);
		
		
		WeightedNode node1BY = new WeightedNode("Balfoure Blvd", 10);
		WeightedNode node2BY = new WeightedNode("Shprintzak Blvd", 1);
		WeightedNode node3BY = new WeightedNode("Sesemy Str", 11);
		
		graph1.addNode(node1NY);
		graph1.addNode(node2NY);
		graph1.addNode(node3NY);
		graph1.addEdge(node1NY, node2NY);
		graph1.addEdge(node1NY, node3NY);
		
		graph2.addNode(node1BY);
		graph2.addNode(node2BY);
		graph2.addEdge(node1BY, node2BY);
		graph2.addEdge(node1BY, node2BY);
		graph2.addEdge(node1BY, node3BY);
		assertNotEquals("yes",graph1,graph2);
		
		
		Iterator<WeightedNode> it = graph1.getNodes();
		//node adding check 
		assertEquals("Candy Str", it.next().getName());
		assertEquals("Mia Kalifa Str", it.next().getName());
		assertEquals("Sasha Gray Str", it.next().getName());
		
		
		Iterator<WeightedNode> it_ver = graph2.getSons(node1BY);
		//System.out.println(it_ver.next());
		assertEquals(1,it_ver.next().getCost());
		assertEquals("Sesemy Str",it_ver.next().getName());
		
	}
	
}
