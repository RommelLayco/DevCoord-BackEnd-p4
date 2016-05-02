package java_DOM_parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import nz.ac.auckland.proximity.Context_Structure;
import nz.ac.auckland.proximity.Task;

/**
 * Class used to read in the xml files.
 * And process the data to poulate the fields of a task object.
 * @author Rommel
 *
 */
public class ReadXML {

	/**
	 * 
	 * @param filepath - of the xml file to read
	 * @return a Document object we can process further to get elements by tags
	 */
	public static Document readInput(String filepath){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filepath);

			return doc;

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}


	/**
	 * Method process the document nodes of the xml file
	 * and creates task objects. 
	 * @param doc
	 * @return Map of the task objects. Key is the task handle.
	 */
	public static Map<String,Task> createTaskObjects(Document doc) throws WrongXML{
		//create map to store task objects
		Map<String, Task> tasks = new HashMap<String, Task>();

		NodeList taskList = doc.getElementsByTagName("Task");

		//get
		for(int i = 0; i < taskList.getLength(); i++){
			Node taskXML = taskList.item(i);

			if(taskXML.getNodeType() == Node.ELEMENT_NODE){
				Element taskElement = (Element) taskXML;

				//get values need to create task object
				String id = taskElement.getAttribute("TaskId");
				int ID = Integer.parseInt(id);

				String handle = taskElement.getAttribute("Handle");
				String label = taskElement.getAttribute("Label");

				//create task object
				Task taskObject = new Task(ID, handle, label);

				//store in the map - key = taskID.
				tasks.put(handle, taskObject);

			} else {
				throw new WrongXML("Read the wrong xml file. Should have found an element called Task");
			}
		}



		return tasks;
	}

	/**
	 * Method that looks at the context of a task
	 * and changes the java file boolean fields edit and select
	 * if the file has been consulted - "select" or changed - "edit"

	 *Only creates java file object for a file that contain a class.
	 *If structure handle has method name, it is truncated.

	 * @param doc of the xml document that was read
	 * @param map containg all the task.
	 * @return
	 * @throws WrongXML
	 */
	public static Map<String,Task> setContextOfTaskObject(Document doc,
			Map<String,Task> map) throws WrongXML{

		//get task object that we will check for additions and selections.
		NodeList interactionList = doc.getElementsByTagName("InteractionHistory");

		if( interactionList.getLength() > 1){
			throw new WrongXML("Read the wrong xml file. Should have only 1 element called InteractionHistory");
		}

		Node n = interactionList.item(0);

		if(n.getNodeType() == Node.ELEMENT_NODE){
			Element element = (Element) n;
			String id = element.getAttribute("Id");

			//get task object from handle
			Task taskObject = map.get(id);
			Map<String, Context_Structure> javaFiles = taskObject.getContextStructures();

			//look through children nodes for edit and select.
			NodeList eventList = element.getChildNodes();
			for(int i = 0; i < eventList.getLength(); i++){
				Node eventNode = eventList.item(i);

				if(eventNode.getNodeType() == Node.ELEMENT_NODE){
					Element event = (Element) eventNode;

					//get name of the java file class
					String name = event.getAttribute("StructureHandle");
					String kindString = event.getAttribute("Kind");

					//convert to enum
					Kind kind = Kind.fromString(kindString);

					//do more processing it is a class that is affected.
					name = checkName(name);

					if(name != null && kind !=null){
						//check if it already defined
						Context_Structure file = javaFiles.get(name);

						// create java file object and store in map
						if(file == null){ 
							file = createJavaFile(name, kind);
							javaFiles.put(name, file);
						}

						//set field
						file = setType(file, kind);



					}

				}
			}
		}


		return map;

	}

	/**
	 * Helper method to convert structure handle into the class
	 * name it the structure handle has a method.
	 * 
	 * Also check is the structure handle has the class name
	 * 
	 * @param name
	 * @return
	 */
	private static String checkName(String name){

		String[] check = name.split("\\{");

		if( check.length > 1){ //has a class
			//check if it has method attach
			check = name.split("\\[");

			if(check.length > 1) { //has a method affected
				return check[0];
			} else{
				return name;
			}
		}
		return null;
	}

	/**
	 * Helper method to create a new java file depend on the kind we get.
	 * @param name
	 * @param kind
	 * @return
	 */
	private static Context_Structure createJavaFile(String name, Kind kind){

		switch(kind){
		case SELECTION:
			return new Context_Structure(name, true, false);

		case EDIT:
			return new Context_Structure(name, false, true);

		default:
			System.err.println("There is an extra enum");
			return null;
		}

	}

	/**
	 * Helper method to change either the edit or selection
	 * boolean field of a javafile object to true.
	 * 
	 * That is the file has been used is some way.
	 * @return
	 */
	private static Context_Structure setType(Context_Structure file, Kind kind){

		switch(kind){
		case SELECTION:
			file.setSelected(true);
			break;

		case EDIT:
			file.setEdit(true);
			break;

		default:
			System.err.println("There is an extra enum");

		}

		return file;
	}
}
