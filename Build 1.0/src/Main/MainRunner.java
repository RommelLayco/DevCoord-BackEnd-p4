package Main;

import org.w3c.dom.Document;

import java_DOM_parser.ReadXML;

public class MainRunner {
	
	public static void main(String[] args){
		Document doc = ReadXML.readInput("xmlfiles/tasklist.xml");
		
		ReadXML.createTaskObjects(doc);
	}

}
