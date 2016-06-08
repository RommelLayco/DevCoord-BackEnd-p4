package nz.ac.auckland.devcoord.unittest;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import nz.ac.auckland.devcoord.database.DatabaseUtils;
import nz.ac.auckland.devcoord.database.Task;

public class TestPersist {
	static EntityManager em; 

	@Before
	public void createEnityManager(){
		em = DatabaseUtils.getEntityManager();

	}

	/**
	 * Test if task pair has correct annotations
	 * Test to make sure if input to object creation is reversed will
	 * throw an exeception as the task already exisit
	 */

	@Test
	public void TestSaveTask(){
		
		em.getTransaction( ).begin( );

		Task t1 = new Task(-1, "comp-key1", "testing Composite Key");

		em.persist( t1 );
		em.getTransaction( ).commit( );

		em.close( );


	}
	
	
	
}
