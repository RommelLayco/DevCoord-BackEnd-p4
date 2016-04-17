package Main;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.Document;

import JunitTestCase.FakeClass;
import java_DOM_parser.Kind;
import java_DOM_parser.ReadXML;
import java_DOM_parser.WrongXML;
import nz.ac.auckland.proximity.Task;

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
		
		
		//push results into database
		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();

		// this would save the Student_Info object into the database
		FakeClass fakeTask = new FakeClass(1,"faketask");
		session.save(fakeTask);
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

}
