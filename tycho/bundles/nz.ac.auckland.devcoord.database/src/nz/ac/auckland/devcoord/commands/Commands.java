package nz.ac.auckland.devcoord.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;

public class Commands {

	private HibernateUtil hibernateUtil;

	public Commands(){
		hibernateUtil = HibernateUtil.getInstance();
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

	public void addTaskPair(TaskPair taskPair){
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		//try{
		em.persist( taskPair );
		/*} catch (javax.persistence.PersistenceException ex){
			//this happens cause i am trying to persist two task
			//objects that may have already been persisted
			
			//code still works error you get is: 
			//org.hibernate.PersistentObjectException: detached entity passed to persist: nz.ac.auckland.devcoord.database.TaskPair
		
			System.err.println("detach entity error code still works");
		}*/
		em.getTransaction().commit();
		em.close();
	}

	public void updateTaskPair(TaskPair taskPair){
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge( taskPair );
		em.getTransaction().commit();
		em.close();
	}

	public boolean taskPairExist(int ID){
		boolean result = false;
		EntityManager em = hibernateUtil.getEntityManager();
		TaskPair taskPair = em.find( TaskPair.class, ID );
		em.close();

		if(taskPair != null){
			result = true;
		} 

		return result;

	}

	/**
	 * Gets a task pair base on the two task that make the pair
	 * return null if the task pair does not exist
	 * @param task1ID
	 * @param task2ID
	 * @return
	 */
	public TaskPair getTaskPair(int task1ID, int task2ID){
		//swap the ids to make sure taskID 1 is smaller
		if(task1ID > task2ID){
			int tmp = task2ID;
			task2ID = task1ID;
			task1ID = tmp;
		}

		String jpql = "Select tp from TaskPair tp "
				+ "where tp.task1 = " + task1ID + " AND "
				+ "tp.task2 = " + task2ID;

		EntityManager em = hibernateUtil.getEntityManager();
		Query query = em.createQuery(jpql);
		TaskPair tp = null;

		try{
			tp = (TaskPair) query.getSingleResult(); 
		} catch (javax.persistence.NoResultException ex){
			//all good if the task pair does not exist
			//return null
			System.err.println("ensure that tp is null");
		}
		
		return tp;
	}
}
