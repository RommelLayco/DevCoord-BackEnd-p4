package nz.ac.auckland.devcoord.database.test;

import javax.persistence.EntityManager;

import nz.ac.auckland.devcoord.database.HibernateUtil;
import nz.ac.auckland.devcoord.database.Task;

public class TaskDataServiceImpl {
	

	private HibernateUtil hibernateUtil = new HibernateUtil();

	public void add(Task dp) {
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist( dp );
		em.getTransaction().commit();
		em.close();
	}

}
