package nz.ac.auckland.devcoord.database.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import nz.ac.auckland.devcoord.database.Task;

public class PersistTest {
	
	static EntityManagerFactory emf;
	static EntityManager em;
	
	@BeforeClass
	public static void createEntFactory(){
		emf = Persistence.createEntityManagerFactory( "PersistenceUnit");
		
	}

	@Before
	public void createManager(){
		em = emf.createEntityManager( );
		em.getTransaction( ).begin( );

	}
	
	@Test
	public void testCreateNewTask(){
		
		Task t1 = new Task(-1, "comp-key1", "testing Composite Key");

		em.persist( t1 );
		
		
	}
	
	@After
	public void closeManager(){
		em.getTransaction( ).commit( );
		em.close();
	}
	
	@AfterClass
	public static void closeEntFactory(){
		emf.close();
	}
	

}
