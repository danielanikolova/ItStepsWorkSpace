package graphWithGenerics;

import java.io.File;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * This class contains methods to read and write information about a graph;
 * @author Daniela
 *
 */

public class GraphParser {

	
	/**
	 * The method parseXmlToGraph() reads information from xml document and than creates new Graph object
	 * @return new Object from type Graph<V>
	 */
	public static Graph<String> parseXmlToGraph() {

		Graph<String> graphFromXML = new Graph<String>();

		try {

			File file = new File("graphParameters.xml");

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			Element rootElement = doc.getDocumentElement();

			NodeList adjacensyList = rootElement.getElementsByTagName("adjacencyList");

			NodeList vertexList = adjacensyList.item(0).getChildNodes();

			LinkedList<Edge<String>> connected;
			Edge<String> current;

			for (int i = 0; i < vertexList.getLength(); i++) {

				connected = new LinkedList<Edge<String>>();

				NodeList edgeList = vertexList.item(i).getChildNodes();
				NamedNodeMap vertexAttribute = vertexList.item(i).getAttributes();
				String currentVertexName = vertexAttribute.item(0).getNodeValue();

				for (int j = 0; j < edgeList.getLength(); j++) {
					NamedNodeMap edgeAttribute = edgeList.item(j).getAttributes();
					String vertexName = edgeAttribute.item(0).getNodeValue();
					current = new Edge<String>(vertexName, 0);
					connected.add(current);
				}

				graphFromXML.add(currentVertexName, connected);
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return graphFromXML;

	}

	/**
	 * This method creates new xml document witch contains information about the method parameter from type Graph<V>. 
	 * @param myGraph
	 */
	public static <V> void parsObjectToXml(Graph<V> myGraph) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("graph");
			doc.appendChild(rootElement);

			// staff elements
			Element adjacencyList = doc.createElement("adjacencyList");
			rootElement.appendChild(adjacencyList);

			for (V entryVertex : myGraph.getAdjacencyList().keySet()) {

				Element vertex = doc.createElement("vertex");
				vertex.setAttribute("name", String.valueOf(entryVertex));
				adjacencyList.appendChild(vertex);

				for (Edge<V> entryEdge : myGraph.getAdjacencyList().get(entryVertex)) {

					Element edge = doc.createElement("edge");
					edge.setAttribute("vertex", (String) entryEdge.getVertex());
					edge.setAttribute("weight", "5");
					vertex.appendChild(edge);

				}

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File("graphParameters.xml"));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

	
}
