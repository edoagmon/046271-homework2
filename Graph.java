package homework2;



import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;



/**
 *class for holding a vertex and edges graph 
 * <p>
 * <b>Generics:</b>
 * 	<pre>
 * T: the class used for the nodes.
 * 	</pre>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *
 * </pre>
 * </p>
 * 
 *
 */

public class Graph<T> {
	
	/**
	 * Abstraction function
	 * <pre>
	 * nodes : represents a the vertexes in the graph    
	 * edge between two nodes is represented by  a filed at each node.
	 * </pre>
	 * Representation invariant
	 * <pre>
	 * for each node, the list of it's children does not contain duplicate nodes
	 * </pre>
	 */
	
	private final Map<T,Set<T>> nodes;
	private final String graph_name;
	
	/**
	 * Constructor for Graph
	 * @requires name != null && name != ""  
	 * @effects create a new graph
	 */
	public Graph(String name) {
		this.nodes 		= new HashMap<>();
		this.graph_name = name;
		this.checkRep();
	}
	
	
	/**
	 * @requires node != null
	 * @modifies this
	 * @effects adds node to this.nodes
	 */
	public void addNode(T node){
		this.checkRep();
		this.nodes.put(node,new HashSet<T>());
		
		this.checkRep();
	}
	
	/**
	 * @requires 	src != null && dst != null
	 * 				&& src and dst nodes are in Graph (this)
	 * @modifies this.nodes
	 * @effects adds destination_node to source_node's children
	 * @throws	if the edge is already exist , bad edge exception
	 */
	public void addEdge(T src,T dst) {
		this.checkRep();
		Set<T> sons = this.nodes.get(src);
		try {
			sons.add(dst);  //TODO maybe we should replace the src.sons with sons ?
			
		}catch(Exception e){
			System.out.println("Edge already exist");
		}
		this.checkRep();
	}
	
	
	/**
	 * @requires 	o != null
	 * @return  false if the graph are the same, false when they are not.   
	 */
	@Override 
	public boolean equals(Object o) {
		this.checkRep();
		if (o instanceof Graph<?>) {
			Graph<?> obj = (Graph<?>)o;
			this.checkRep();
			return (this.graph_name.equals(obj.toString()));
			
		}
		this.checkRep();
		return false;
		
	}
	
	/** 
	 * @return  return hash code.  
	 */
	public int hashCode() {
		this.checkRep();
		return (this.graph_name.hashCode());
	}
	
	
	/** 
	 * @return  return the name of this.  
	 */
	public String toString() {
		this.checkRep();
		return this.graph_name;
	}
	
	
	/** 
	 * @return  return the list of the graph nodes.  
	 */	
	public Iterator<T> getNodes(){
		this.checkRep();
		return this.nodes.keySet().iterator();
	}
	
	/** 
	 * @return  return the list of the graph nodes ,if the node without sons or the node doesn't exist at the graph null will be returned.  
	 */	
	public Iterator<T> getSons(T node){
		this.checkRep();
		if (this.nodes.containsKey(node) == false) {
			return null;
		}
		return this.nodes.get(node).iterator();
	}
	
	/** 
	 * @effect throw assertion error if representation invariant is violated.  
	 */
	public void checkRep() {
		assert(this.graph_name != null);
		///there is no chance for duplicate edges case , because we are using Set to hand the child node for node
	}
	
	
	
	
}
