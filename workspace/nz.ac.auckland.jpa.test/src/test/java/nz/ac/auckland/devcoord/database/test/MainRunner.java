package nz.ac.auckland.devcoord.database.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nz.ac.auckland.devcoord.database.Task;

public class MainRunner {

	public static void main(String[] args){

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "PersistenceUnit", null );
		EntityManager entitymanager = emfactory.createEntityManager( );

		entitymanager.getTransaction( ).begin( );
		Task t1 = new Task(-1, "comp-key1", "testing Composite Key");

		entitymanager.persist( t1 );
		entitymanager.getTransaction( ).commit( );

		entitymanager.close( );
		emfactory.close( );


	}


}
