package nz.ac.auckland.devcoord.controller;

import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.views.DevCoord;

/**
 * Used for updating and creating of new {@link Task} objects.
 * */
public class TaskUtil {

	/**
	 * This single public method should be called upon 
	 * creation of a new {@link TaskWrapper} object in {@link DevCoord} class.
	 * The method will use the {@link TaskWrapper} object to update the already persisting Task object,
	 * or create a new one if none exists for a given handle.
	 * */
	public static boolean update(TaskWrapper taskWrapper){

		Task task=findTask(taskWrapper.getTaskHandle());
		if (task==null) {
			task=createNewTask(taskWrapper);
		}
		updateTask(taskWrapper);
		return true;
	}


	/**
	 * Will be used to search for a persisting {@link Task} object for a given handle identifier.
	 * 
	 * */
	private static Task findTask(String handle){


		return new Task();
	}


	/**
	 * This method will be used to create a new persisting {@link Task} objects.
	 * */
	private static Task createNewTask(TaskWrapper taskWrapper){

		return new Task();

	}

	/**
	 * 
	 * Used to update the existing persisting {@link Task} object using the {@link TaskWrapper}
	 * */
	private static boolean updateTask(TaskWrapper taskWrapper){

		return false;

	}

}