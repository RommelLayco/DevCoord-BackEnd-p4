package nz.ac.auckland.devcoord.controller;

import nz.ac.auckland.devcoord.commands.Commands;
import nz.ac.auckland.devcoord.database.Task;

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


}
