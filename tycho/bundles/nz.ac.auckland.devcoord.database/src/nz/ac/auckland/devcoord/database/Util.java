package nz.ac.auckland.devcoord.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Util {
	
	public EntityManager getEntityManager(){
		BundleContext context =  HibernateJpaActivator.getContext(); 
		ServiceReference serviceReference = context.getServiceReference( PersistenceProvider.class.getName() );
		PersistenceProvider persistenceProvider = (PersistenceProvider) context.getService( serviceReference );
		EntityManagerFactory emf = persistenceProvider.createEntityManagerFactory( "TheNameOfMyPersistenceUnit", null );
		EntityManager em = emf.createEntityManager();
		return em;
	}

}