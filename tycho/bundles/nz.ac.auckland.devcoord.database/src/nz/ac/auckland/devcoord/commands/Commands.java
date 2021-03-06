package nz.ac.auckland.devcoord.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;

public class Commands {

	private HibernateUtil hibernateUtil;

	public Commands(){
		hibernateUtil = HibernateUtil.getInstance();
	}

	/**
	 * Method to check if the task is already persisted in the
	 * database
	 * @param ID of the task you want to check
	 * @return true if the task exist in the database, false otherwise
	 */

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


	/**
	 * Get the task from the database
	 * @param ID of the task to fetch
	 * @return the task
	 */
	public Task getTask(int ID){
		EntityManager em = hibernateUtil.getEntityManager();
		Task task = em.find( Task.class, ID );
		em.close();

		return task;
	}

	/**
	 * Add a new task to the database
	 * @param task
	 */
	public void addTask(Task task){
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist( task );
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Update an existing task in the database
	 * @param task
	 */
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

			//ensure that cast is done correctly.
			tasks = castList(Task.class, query.getResultList());
			em.close();

			return tasks;
		} else{
			return tasks;
		}


	}

	/**
	 * Formats the the "or's" of the context structures
	 * this is no longer used in the program
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

	/**
	 * Add a new task pair object to the database
	 * @param taskPair
	 */
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

	/**
	 * Update a task pair that is already in the database
	 * @param taskPair
	 */
	public void updateTaskPair(TaskPair taskPair){
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge( taskPair );
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Check if the task pair is already in the database
	 * @param ID
	 * @return
	 */
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

	/**
	 * get list of task id that  from database that contains the context structure file1.
	 * 
	 * Ignore the task that has the task_ID value.
	 * @param file1
	 * @param task_ID
	 * @param days is the numbers of day in the past. That is only get task that have been
	 * updated in the database in the last x amount of days. Note if has been updated in 
	 * the database it has been worked on.
	 * @return
	 */
	public List<Integer> getTaskIDsWithSameContext(Context_Structure file1, int task_ID, int days){
		List<Integer> task_IDs = new ArrayList<Integer>(); 

		//create query string
		String jpql = "Select t.id from Task t, IN(t.contextStructure) c"
				+ " where t.taskID != " + task_ID +
				" AND c.name = '" + file1.getName() +"'" +
				" AND t.date > :date";

		

		EntityManager em = hibernateUtil.getEntityManager();
		Query query = em.createQuery(jpql);
		
		
		LocalDate date = LocalDate.now();
		query.setParameter("date", date.minusDays(days));
		
		
		
		//query.setParameter("start", date.minusDays(days));
		//query.setParameter("end", date);

		//ensure that cast is done correctly.
		task_IDs = castList(Integer.class, query.getResultList());
		em.close();

		return task_IDs;

	}
	
	/**
	 * Method to update proximity score
	 * 
	 * needs to fetch context structure of task which use lazy loaded
	 * 
	 * i.e. need to load persistence context, this done by using the entity manager
	 * @param c2
	 * @param task
	 * @param tp
	 * @return
	 */
	public TaskPair updateProximityScore(Context_Structure c2, int ID, TaskPair tp){
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		Task task = em.find( Task.class, ID );
				
		tp.updateProximityScore(c2, task.getContextStructures().get(c2.getName()));
		
		em.getTransaction().commit();
		em.close();
		
		return tp;
	}
	
	/**
	 * Method to create TaskPair
	 * 
	 * needs to fetch context structure of task which use lazy loaded
	 * 
	 * i.e. need to load persistence context, this done by using the entity manager
	 * @param c2
	 * @param task
	 * @param tp
	 * @return
	 */
	public TaskPair createTaskPair(int id1, int id2){
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		Task t1 = em.find( Task.class, id1 );
		Task t2 = em.find( Task.class, id2 );
				
		TaskPair taskPair = new TaskPair(t1, t2);
		
		em.getTransaction().commit();
		em.close();
		
		return taskPair;
	}
	
	/**
	 * Method to add context_structure while getting the task. Needs an entity manager
	 * as the persistence context has not yet add the context structures
	 * field. 
	 * @param task
	 * @return
	 */
	public Task getTaskAndAddContext(Integer ID, Context_Structure context){
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		
		Task task = em.find( Task.class, ID );
		task.addContextStructure(context.getName(), context);
		
		em.getTransaction().commit();
		em.close();
		
		return task;
	}
	
	/**
	 * fixes the Type safety: The expression of type List needs 
	 * unchecked conversion to conform to List<T>
	 * 
	 * i.e. when a list of results is queried
	 * 
	 * @param clazz
	 * @param c
	 * @return
	 * 
	 * taken from http://stackoverflow.com/questions/367626/how-do-i-fix-the-expression-of-type-list-needs-unchecked-conversion
	 */
	private static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
		
	
}
