package nz.ac.auckland.devcoord.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DatabaseUtils {
	
	public static EntityManager getEntityManager(){
//		System.out.println(HibernateJpaActivator.class);
//		BundleContext context =  HibernateJpaActivator.getContext(); 
//		ServiceReference serviceReference = context.getServiceReference( PersistenceProvider.class.getName() );
//		PersistenceProvider persistenceProvider = (PersistenceProvider) context.getService( serviceReference );
		EntityManagerFactory emf = Persistence.createEntityManagerFactory( "PersistenceUnit", null );
		EntityManager em = emf.createEntityManager();
		return em;
	}

}
