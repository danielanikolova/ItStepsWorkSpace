package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * Graph class represents simple implementation for Graph with integer nodes. 
 * The basic methods provide breath first and depth first traversing algorithm.
 *
 */
public class Graph {

    	@XmlElement(name = "adjacencyList")
	private Map<Integer, List<Integer>> adjacencyList;
	@XmlElement(name = "verteces")
	private List<Integer> verteces;
	
	public Graph() 
	{
		this.adjacencyList = new HashMap<>();
		this.verteces = new LinkedList<>();
	}
	
	public void addVertex(int vertexValue, List<Integer> currentVertexList) 
	{			
		this.adjacencyList.put(vertexValue, currentVertexList);
		this.verteces.add(vertexValue);		
	}
	
	/*
	 * This method provides traversing algorithm where we start traversing from a selected node 
	 * (source or starting node) and traverse the graph layerwise thus exploring the neighbor nodes.
	 */
	public void BFSTraverse(int gate) 
	{		
		Queue<Integer> toVisit = new ArrayDeque<>();
		List<Integer> resultList = new ArrayList<>();		
		
		resultList.add(gate);
		
		toVisit.addAll(adjacencyList.get(gate));		
		
		while (!toVisit.isEmpty()) 
		{			
			if (!resultList.contains(toVisit.peek())) 
			{
				toVisit.addAll(adjacencyList.get(toVisit.peek()));
				resultList.add(toVisit.poll());
			}else {
				toVisit.poll();
			}			
		}
		
		System.out.println(resultList.toString());
		
		
	}
	
	
	/*
	 * Depth-first search (DFS) method provides an algorithm for traversing the graph starting at 
	 * the root node (selecting some arbitrary node as the root node in the case of a graph) 
	 * and explores as far as possible along each branch before backtracking.
	 */
	public void DFSTraversal(int gate) 
	{	
		List<Integer> resutList = new LinkedList<>();
		
		getChildren(resutList, gate);		
		
		System.out.println(resutList.toString());
		
	}
	
	/*
	 * This method get the neighbors of each node and writes them in result list of visited nodes by dfs traversing
	 */
	private void getChildren(List<Integer> resutList, int gate) 
	{		
		resutList.add(gate);
		Queue<Integer> toVisit = new ArrayDeque<>();
		toVisit.addAll(adjacencyList.get(gate));
		
		while (!toVisit.isEmpty()) 
		{		
				if (!resutList.contains(toVisit.peek())) {				
					getChildren(resutList, toVisit.poll());
				
				}else {
					toVisit.poll();
				}			
			
		}
		
	}

	public String mapInfo() 
	{
		return adjacencyList.toString();
	}

}
