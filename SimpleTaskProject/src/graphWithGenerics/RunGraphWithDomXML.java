package graphWithGenerics;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;

public class RunGraphWithDomXML {

	public static void main(String[] args) throws ParserConfigurationException {

		Graph<String> myGraph = new Graph<String>();
		LinkedList<Edge<String>> connected = new LinkedList<Edge<String>>();

		connected.add(new Edge<String>("B", 2));
		connected.add(new Edge<String>("C", 12));
		connected.add(new Edge<String>("D", 7));

		myGraph.add("A", connected);

		myGraph.addEdge("C", "B", 1);

		myGraph.addEdge("Z", "F", 4);

		
		myGraph.addEdge("Z", "R", 5);

		myGraph.addEdge("D", "Z", 1);

		myGraph.addEdge("F", "D", 1);

		myGraph.addEdge("W", "L", 1);

		GraphParser.parsObjectToXml(myGraph);
		Graph<String> graphFromXml = GraphParser.parseXmlToGraph();

		System.out.println(myGraph.toString());
		System.out.println(graphFromXml.toString());

	}

	
}
