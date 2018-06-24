package graphWithGenerics;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Graph<V> {

	private LinkedHashMap<V, LinkedList<Edge<V>>> adjacencyList;

	/**
	 * This list holds all the vertices so that we can iterate over them in the
	 * toString function
	 */
	private LinkedList<V> vertexList;

	public Graph() {
		adjacencyList = new LinkedHashMap<V, LinkedList<Edge<V>>>();
		vertexList = new LinkedList<V>();
	}
	/**
	 * This method adds node to the graph with its adjacency list;
	 * 
	 * @param vertex
	 */
	public void add(V vertex, LinkedList<Edge<V>> connected) {

		if (!this.adjacencyList.containsKey(vertex)) {
			adjacencyList.put(vertex, connected);
			vertexList.add(vertex);
			for (Edge<V> edge : connected) {
				if (!adjacencyList.containsKey(edge.getVertex())) {
					adjacencyList.put(edge.getVertex(), new LinkedList<>());
					adjacencyList.get(edge.getVertex()).add(new Edge<V>(vertex, edge.getWeight()));
					vertexList.add(edge.getVertex());
				} else {
					adjacencyList.get(edge.getVertex()).add(new Edge<V>(vertex, edge.getWeight()));
				}

			}

		} else {
			for (Edge<V> edge : connected) {
				if (!adjacencyList.containsKey(edge.getVertex())) {
					adjacencyList.put(edge.getVertex(), new LinkedList<>());
					adjacencyList.get(edge.getVertex()).add(new Edge<V>(vertex, edge.getWeight()));
					vertexList.add(edge.getVertex());
				} else {
					
					List<Edge<V>> adjacencyNodeVertexList = adjacencyList.get(edge.getVertex());
					
					boolean containsCurrentVertex = false;
					
					for (Edge<V> adjacencyNodeEdge : adjacencyNodeVertexList) {
						if (adjacencyNodeEdge.getVertex().equals(vertex)) {
							containsCurrentVertex = true;
							break;
						}
					}
					
					if (!containsCurrentVertex) {
						adjacencyList.get(edge.getVertex()).add(new Edge<V>(vertex, edge.getWeight()));
					}
					
				
				}
			}

		}

	}

	/**
	 * This methd adds new Edge to the existing graph. The new edge connects vertexOne to vertexTwo and has weight;
	 * @param vertexOne
	 * @param vertexTwo
	 * @param weight
	 * @return
	 */
	public boolean addEdge(V vertexOne, V vertexTwo, int weight) {

		if (!adjacencyList.containsKey(vertexOne)) {
			LinkedList<Edge<V>> tempList = new LinkedList<Edge<V>>();
			tempList.add(new Edge<V>(vertexTwo, weight));
			add(vertexOne, tempList);
			return true;
		}

		if (!adjacencyList.containsKey(vertexTwo)) {
			LinkedList<Edge<V>> tempList = new LinkedList<Edge<V>>();
			tempList.add(new Edge<V>(vertexOne, weight));
			add(vertexTwo, tempList);
			return true;
		}

		adjacencyList.get(vertexOne).add(new Edge<V>(vertexTwo, weight));
		adjacencyList.get(vertexTwo).add(new Edge<V>(vertexOne, weight));
		return true;
	}

	public LinkedHashMap<V, LinkedList<Edge<V>>> getAdjacencyList() {
		return adjacencyList;
	}

	public String toString() {
		String s = "";
		for (V vertex : vertexList) {
			s += vertex.toString();
			s += " : ";
			s += adjacencyList.get(vertex);
			s += "\n";
		}
		return s;
	}

}
