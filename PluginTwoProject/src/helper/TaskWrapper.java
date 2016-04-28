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
public class TaskWrapper {

	private String taskID;
	private String taskHandle;
	private String taskLabel;

	private Kind interactionEventKind;
	private String structureHandle;
	
	public TaskWrapper(InteractionEvent interactionEventArg) {
		
		interactionEventKind=interactionEventArg.getKind();
		
		structureHandle=interactionEventArg.getStructureHandle();
		
		ITask activeTask=getActiveTask();
		
		
		taskID=activeTask.getTaskId();
		taskHandle=activeTask.getHandleIdentifier();
		
		
		
		

	
	}
	
	
	
	private ITask getActiveTask(){
		
		
		
	return	org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskActivityManager().getActiveTask();
		
		
	}
	
	
	
	
	
	
	public void getInteractionKind() {
		// TODO Auto-generated method stub

	}

}
