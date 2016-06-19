package nz.ac.auckland.devcoord.commands;

import javax.persistence.EntityManager;

import nz.ac.auckland.devcoord.database.Task;

public class Commands {

	private HibernateUtil hibernateUtil;

	public Commands(){
		hibernateUtil = new HibernateUtil();
	}
	
	public Commands(HibernateUtil hibernateUtil){
		this.hibernateUtil = hibernateUtil;
	}
	
	public boolean taskExist(int ID){
		boolean result = false;
		EntityManager em = hibernateUtil.getEntityManager();
		Task task = em.find( Task.class, ID );
		em.close();

		if(task != null){
			result = true;
		} 

		return result;

	}


	public Task getTask(int ID){
		EntityManager em = hibernateUtil.getEntityManager();
		Task task = em.find( Task.class, ID );
		em.close();

		return task;
	}

	public void addTask(Task task){
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist( task );
		em.getTransaction().commit();
		em.close();
	}

	public void updateTask(Task task){
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge( task );
		em.getTransaction().commit();
		em.close();
	}

}
