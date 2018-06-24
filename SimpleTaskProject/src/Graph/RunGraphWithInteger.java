/**
 * RunGraphWithInteger.java
 * 
 * created at 2018-06-07 by d.nikolova <d.nikolova@seeburger.com>
 * 
 *  Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 * 
 * <p>
 * Assigned by Todor Manahov
 * <p>
 */

package Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RunGraphWithInteger {

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new FileReader("verteces.txt"));
	
	Graph myGraph = new Graph();
	
	String line = br.readLine();
	
	while (line!=null) {
		
		String[] vertexInfo = line.split(" -> ");
		int vertexValue = Integer.parseInt(vertexInfo[0]);
		List<Integer> adjacencyList = new LinkedList<>();
					
		String [] adjacencies = vertexInfo[1].split(", ");
		
		
		for (int i = 0; i < adjacencies.length; i++) {
			adjacencyList.add(Integer.parseInt(adjacencies[i]));
			
		}
		
		myGraph.addVertex(vertexValue, adjacencyList );
		
		line = br.readLine();
	}
	
	br.close();
	
	myGraph.DFSTraversal(7);
	
	myGraph.BFSTraverse(7);
	
	System.out.println(myGraph.mapInfo());
	
	System.out.println();

    }

}
