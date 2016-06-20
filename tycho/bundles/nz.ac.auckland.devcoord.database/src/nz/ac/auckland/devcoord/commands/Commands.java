package nz.ac.auckland.devcoord.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

	/**
	 * Method gets tasks from database that contains the
	 * same context structures the task has.
	 * 
	 * @param task with the context structures. Look for other task
	 * with same context structures.
	 */
	public List<Task> getTaskWithSameContext(Task task){
		List<Task> tasks = new ArrayList<Task>(); 
		
		//create query string
		String jpql = "Select t from Task t, IN(t.contextStructure) c"
				+ " where t.taskID != " + task.getTaskID();
		
		
		Set<String> keys = task.getContextStructures().keySet();
		if(keys.size() > 0){
			String line = contextStructureJPQL(keys);
			
			jpql = jpql + " AND (" + line + ")";
			
//			jpql = "Select t from Task t, IN(t.contextStructure) c"
//					+ " where c.name = 'queryTest'";
			
			EntityManager em = hibernateUtil.getEntityManager();
			Query query = em.createQuery(jpql);
			
			tasks = query.getResultList();
			em.close();
			
			return tasks;
		} else{
			return tasks;
		}
		
		
		
	}
	
	/**
	 * Formats the the "or's" of the context structures
	 * @param name
	 * @return
	 */
	private static String contextStructureJPQL(Set<String> contextStructureNames){
		String line = "";
		
		Iterator<String> it = contextStructureNames.iterator();
		//put down the first context structure name
		
		//note name need to be put between '' marks
		//else it thinks it is a column name
		if(it.hasNext()){
			String name  = it.next();
			line = "c.name = '" + name +"'";
		}
		
		//put down the rest
		while(it.hasNext()){
			String name = it.next();
			line = line + " OR c.name = '" + name + "'";
		}
		
		return line;
	}
}
