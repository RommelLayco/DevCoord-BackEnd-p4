package nz.ac.auckland.devcoord.controller;

import java.util.List;

import nz.ac.auckland.devcoord.commands.Commands;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;

public class Controller {

	private Commands service;
	
	public Controller(){
		this.service = new Commands();
	}
	
	public Controller(Commands service){
		this.service = service;
	}

	public Task updateTaskInfo(TaskWrapper wrapper){
		Task task;
		System.out.println("dffdf");
		boolean exist = service.taskExist(wrapper.getTaskID());
		
		if(exist){
			task = service.getTask(wrapper.getTaskID());
			task.updateTaskID(wrapper.getTaskID());
			task.updateHandle(wrapper.getTaskHandle());
			task.updateLable(wrapper.getTaskLabel());
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
	

	
//	public List<TaskPair> getTaskPairs(Task task){
//		
//	}


}
