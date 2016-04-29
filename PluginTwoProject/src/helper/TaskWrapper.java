package helper;

import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.monitor.core.InteractionEvent.Kind;
import org.eclipse.mylyn.tasks.core.ITask;

/**
 * Rommel needs-
 * 		Task wrapper

		Event happens - “an edit or selection of a context structure associated with the active task”
		
		Create a task wrapper with :
		Task id
		Task handle
		Task label
		From the interaction element get:
		The kind - i.e edit or select
		The structure handle - i.e. what has been seen/ modified.

 * 
 * 
 * */

@SuppressWarnings("restriction")
public class TaskWrapper {


	/**
	 * By calling this static method,a TaskWrapper object is created with the desired
	 * informaton.
	 * 
	 * This method should only be called by the triggering of the InteractionEvent listener.
	 * 
	 * */
	public static TaskWrapper getTaskWrapper(InteractionEvent eventArg){
		
		return  new TaskWrapper(eventArg);
		
	}

	private String taskID;
	private String taskHandle;
	private String taskLabel;

	private Kind interactionEventKind;
	private String structureHandle;
	
	private TaskWrapper(InteractionEvent interactionEventArg) {
		
		interactionEventKind=interactionEventArg.getKind();
		
		structureHandle=interactionEventArg.getStructureHandle();
		
		ITask activeTask=getActiveTask();
		
		
		taskID=activeTask.getTaskId();
		taskHandle=activeTask.getHandleIdentifier();
		taskLabel="DUMMY_LABEL";
		
		
		

	
	}
	
	
	
	private ITask getActiveTask(){
		
		
		
	return	org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskActivityManager().getActiveTask();
		
		
	}
	
	
	

	public String getTaskID() {
		return taskID;
	}



	public String getTaskHandle() {
		return taskHandle;
	}



	public String getTaskLabel() {
		return taskLabel;
	}



	public Kind getInteractionEventKind() {
		return interactionEventKind;
	}



	public String getStructureHandle() {
		return structureHandle;
	}

	@Override
	public String toString() {	

		String separator=System.getProperty("line.separator");
		
		String toReturn="";
		
		
		toReturn+="TaskID:"+getTaskID()+separator;
		toReturn+="TaskLabel:"+getTaskLabel()+separator;
		toReturn+="TaskHandle:"+getTaskHandle()+separator;
		toReturn+="EventKind:"+getInteractionEventKind()+separator;
		toReturn+="StructureHandle:"+getStructureHandle()+separator;
		
		

		return toReturn;

	}
	

}
