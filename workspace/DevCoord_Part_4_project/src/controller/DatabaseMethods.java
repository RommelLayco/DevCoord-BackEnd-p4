package controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import helper.TaskWrapper;
import nz.ac.auckland.proximity.Task;

public class DatabaseMethods {

	private SessionFactory sessionFactory;


	public DatabaseMethods(){
		try{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}

	

	
	public Task updateOrAddTask(TaskWrapper taskWrapper) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer ID = Integer.parseInt(taskWrapper.getTaskID());
		Task task = null;
		try{
			tx = session.beginTransaction();
			tx = session.beginTransaction();
	         task = (Task)session.get(Task.class, ID); 
	         if(task != null){
	        	 task.updateInfo(taskWrapper);
	        	 session.update(task);
	         } else {
	        	 //create a new task object
	        	 task = new Task(ID, taskWrapper.getTaskHandle(), 
	        			 taskWrapper.getTaskLabel());
	        	 
	        	 //add context structure to task
	        	 //task.addContextStructure(filepath, structure);
	        	 
	         }
			
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		
		return task;
	}
	
}
