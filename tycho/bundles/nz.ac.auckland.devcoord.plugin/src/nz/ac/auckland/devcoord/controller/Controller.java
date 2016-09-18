package nz.ac.auckland.devcoord.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import nz.ac.auckland.devcoord.commands.Commands;
import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;
public class Controller {
	private Commands service;
	
	public Controller(){
		this.service = new Commands();
	}
	
	/**
	 * Updates the active task's context structure that is being worked
	 * on and returns the updated context structure
	 * @return
	 */
	public Context_Structure updateInfoOfActiveTask(TaskWrapper wrapper){
		Task task;
		boolean exist = service.taskExist(wrapper.getTaskID());
		if(exist){
			task = service.getTaskAndAddContext(wrapper.getTaskID(), wrapper.getContextStructure());
			task.updateTaskID(wrapper.getTaskID());
			task.updateHandle(wrapper.getTaskHandle());
			task.updateLablel(wrapper.getTaskLabel());
			task.updateOS(wrapper.getOS());
			task.updatePlatform(wrapper.getPlatform());
			task.updateComponent(wrapper.getComponent());
			task.updateOwner(wrapper.getOwner());
			task.updateDescription(wrapper.getDescription());
			task.updateDate();
			service.updateTask(task);
		} else{
			task = new Task(wrapper.getTaskID(),wrapper.getTaskHandle(), 
					wrapper.getTaskLabel(), wrapper.getOS(),
					wrapper.getPlatform(), wrapper.getComponent(),
					wrapper.getOwner(), wrapper.getDescription());
			task.addContextStructure(wrapper.getStructureHandle(), wrapper.getContextStructure());
			service.addTask(task);
		}
		return task.getContextStructures().get(wrapper.getStructureHandle());
	}
	
	/**
	 * Updates the info about the active task.
	 * 
	 * Return the task object.
	 * @param wrapper
	 * @return
	 */
	public Task updateTaskInfo(TaskWrapper wrapper){
		Task task;
		boolean exist = service.taskExist(wrapper.getTaskID());
		if(exist){
			task = service.getTaskAndAddContext(wrapper.getTaskID(), wrapper.getContextStructure());
			task.updateTaskID(wrapper.getTaskID());
			task.updateHandle(wrapper.getTaskHandle());
			task.updateLablel(wrapper.getTaskLabel());
			task.updateOS(wrapper.getOS());
			task.updatePlatform(wrapper.getPlatform());
			task.updateComponent(wrapper.getComponent());
			task.updateOwner(wrapper.getOwner());
			task.updateDescription(wrapper.getDescription());
			task.updateDate();
			service.updateTask(task);
		} else{
			task = new Task(wrapper.getTaskID(),wrapper.getTaskHandle(), 
					wrapper.getTaskLabel(), wrapper.getOS(),
					wrapper.getPlatform(), wrapper.getComponent(),
					wrapper.getOwner(), wrapper.getDescription());
			task.addContextStructure(wrapper.getStructureHandle(), wrapper.getContextStructure());
			service.addTask(task);
		}
		return task;
	}
	/* no longer needed as method to getTask pairs as update
	 * of proximity score done with a differnet method.
	/**
	 * Get all the other that contains one of the context structures
	 * this task has. and creates the task pair
	 * @param task
	 * @return
	 *
	public List<TaskPair> getTaskPairs(Task task){
		List<TaskPair> taskpairs = new ArrayList<TaskPair>();
		List<Task> tasks = service.getTaskWithSameContext(task);
		Iterator<Task> it = tasks.iterator();
		while(it.hasNext()){
			Task t2 = it.next();
			TaskPair tp = service.getTaskPair(task.getTaskID(), t2.getTaskID());
			if(tp == null){
				tp = new TaskPair(task, t2);
			}
			//tp.calcProximityScore();
			taskpairs.add(tp);
		}
		return taskpairs;
	}*/
	
	/**
	 * Gets all the task except for the one with the given task id that 
	 * contains the file and gets the task pairs
	 * 
	 * @param file that a task working set may contain
	 * @param task_id of the task we do not want
	 * 
	 * @param days is the numbers of day in the past. That is only get task that have been
	 * updated in the database in the last x amount of days. Note if has been updated in 
	 * the database it has been worked on.
	 * 
	 * @return
	 */
	public List<TaskPair> getTaskPairs(Context_Structure file, int task_id, int days){
		List<TaskPair> taskpairs = new ArrayList<TaskPair>();
		List<Integer> task_IDs = service.getTaskIDsWithSameContext(file, task_id, days);
		Iterator<Integer> it = task_IDs.iterator();
		while(it.hasNext()){
			Integer t2 = it.next();
			TaskPair tp = service.getTaskPair(task_id, t2);
			if(tp == null){
				tp = service.createTaskPair(task_id, t2);
			} else { //update the value of the proximity score
				tp = service.updateProximityScore(file, t2, tp);
				tp.setTaskProperties();
			}
			taskpairs.add(tp);
		}
		return taskpairs;
	}
	
	public void saveTaskPairs(List<TaskPair> taskpairs){
		Iterator<TaskPair> it = taskpairs.iterator();
		while(it.hasNext()){
			TaskPair tp = it.next();
			//boolean exist = service.taskPairExist(tp.getID());
			boolean exist = (tp.getID() != null) ? true : false;
			if(exist){
				service.updateTaskPair(tp);
			} else{
				service.updateTaskPair(tp);
			}
		}
	}
}
