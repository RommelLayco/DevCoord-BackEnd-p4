package nz.ac.auckland.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

import controller.DatabaseMethods;
import helper.TaskWrapper;
import java_DOM_parser.ReadXML;
import java_DOM_parser.WrongXML;
import nz.ac.auckland.proximity.Context_Structure;
import nz.ac.auckland.proximity.Task;
import nz.ac.auckland.proximity.TaskPair;


public class ControllerMethods {
	
	static TaskWrapper taskWrapper;
	
	@Before
	public void createTaskWrapper(){
		taskWrapper = new TaskWrapper("5", "edit", "name");
	}
	


}
