package Main;

import java.util.Map;

import org.w3c.dom.Document;

import PromixtyCalc.Task;
import java_DOM_parser.Kind;
import java_DOM_parser.ReadXML;
import java_DOM_parser.WrongXML;

public class MainRunner {
	
	public static void main(String[] args){
		
		
		Document doc = ReadXML.readInput("xmlfiles/tasklist.xml");
		Map<String,Task> tasks;
	
		try {
			tasks = ReadXML.createTaskObjects(doc);
			
			for(int i = 1; i <= 2; i++){
				doc = ReadXML.readInput("xmlfiles/context/local-"+ i + 
						".xml");
				
				tasks = ReadXML.setContextOfTaskObject(doc, tasks);
			}
			
			
		} catch (WrongXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//do promixty calculation here
	}

}
