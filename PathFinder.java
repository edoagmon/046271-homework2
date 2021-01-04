package homework2;

import java.util.*;

public class PathFinder< N, P extends Path<N,P>> {
	private Graph<N> graph;
	
	/*
	 * constructs a new PathFinder.
	 * @requires graph != null
	 * @effects creates a PathFinder with relevant Graph.
	 */
	public PathFinder(Graph<N> graph){
		this.graph= graph;
	}	
	
	/*
	 *Return if a son node is alive in the algorithm.
	 *@requires active != null && son != null.
	 *@return true if son is active, false otherwise 
	*/
	public boolean isActive(PriorityQueue<P> active,N son){
		Iterator<P> pathIterator = active.iterator();
		while(pathIterator.hasNext()){
			if(son == pathIterator.next().getEnd()){
				return true;
			}
		}
		return false;
	}
	
	/*
	 *Return the shortest path from one element of starts to one element of goals in a node-weighted graph.
	 *@requires starts != null && goals != null.
	 *@return the shortest path from one of the starts to one of the goals. 
	 *	null if there are no paths from starts to goals
	*/
	public P findPath(Set<P> starts, Set<N> goals){
		
		Map<N,P> paths = new HashMap<N,P>();
		PriorityQueue<P> active = new PriorityQueue<P>();
		Set<N> finished = new HashSet<N>();
		
		for (P node : starts){
			paths.put(node.getEnd(), node);
			active.add(node);
		}
		
		while (!(active.isEmpty())){			
			P queueMin = active.poll();
			N nodeMin = queueMin.getEnd();
			P queueMinPath = paths.get(nodeMin);
			
			if (goals.contains(nodeMin)) {
				return queueMinPath;
			}
			
			Iterator<N> sonsIterator = graph.getSons(nodeMin);			
			while(sonsIterator.hasNext()){
				N son = sonsIterator.next();
				P sonPath  = queueMinPath.extend(son);
							
				if((!finished.contains(son)) && (!isActive(active,son))){					
						paths.put(son, sonPath);
						active.add(sonPath);			
				}	
			}			
			finished.add(nodeMin);
		}

	return null;
	};
}


