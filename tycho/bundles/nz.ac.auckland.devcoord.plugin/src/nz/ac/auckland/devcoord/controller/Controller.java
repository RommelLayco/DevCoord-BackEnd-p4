package nz.ac.auckland.devcoord.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nz.ac.auckland.devcoord.commands.Commands;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;

public class Controller {

	private Commands service;
	
	public Controller(){
		this.service = new Commands();
	}
	
	

	public Task updateTaskInfo(TaskWrapper wrapper){
		Task task;
		boolean exist = service.taskExist(wrapper.getTaskID());
		
		if(exist){
			task = service.getTask(wrapper.getTaskID());
			task.updateTaskID(wrapper.getTaskID());
			task.updateHandle(wrapper.getTaskHandle());
			task.updateLablel(wrapper.getTaskLabel());
			task.addContextStructure(wrapper.getStructureHandle(), wrapper.getContextStructure());
			
			service.updateTask(task);
		} else{
			task = new Task(wrapper.getTaskID(),wrapper.getTaskHandle(), 
					wrapper.getTaskLabel());
			
			task.addContextStructure(wrapper.getStructureHandle(), wrapper.getContextStructure());
			
			service.addTask(task);
		}
		
		return task;
	}
	

	
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
			
			tp.calcProximityScore();
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
				service.addTaskPair(tp);
			} else{
				service.updateTaskPair(tp);
			}
		}
		
	}

}
