package nz.ac.auckland.devcoord.controller;

import java.util.List;

import org.eclipse.mylyn.context.core.IInteractionContext;
import org.eclipse.mylyn.context.core.IInteractionContextManager;
import org.eclipse.mylyn.context.core.IInteractionElement;
import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.monitor.core.InteractionEvent.Kind;
import org.eclipse.mylyn.tasks.core.ITask;

/**
 * 
 *The objects of this class envelope the IntercationEvent Objects,while also providing 
 *additional  information about the Current task that is being worked on.
 *  * */

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

	private int taskID;
	private String taskHandle;
	private String taskLabel;
	private Kind interactionEventKind;
	private String structureHandle;
	private List<IInteractionElement> interactionElements;

	private String kindType;
	/**
	 * Constructor
	 * */
	private TaskWrapper(InteractionEvent interactionEventArg) {
		interactionEventKind=interactionEventArg.getKind();
		structureHandle=interactionEventArg.getStructureHandle();
		ITask activeTask=getActiveTask();
		taskID=Integer.parseInt(activeTask.getTaskId());
		taskHandle=activeTask.getHandleIdentifier();
		taskLabel=activeTask.toString();

		interactionElements=getCurrentContextElements();

	}

	public TaskWrapper(String id, String kind, String handle){
		taskID = Integer.parseInt(id);
		kindType = kind;
		structureHandle = handle;
		interactionElements=getCurrentContextElements();

	}

	/**Returns currently active task*/
	private ITask getActiveTask(){
		return	org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskActivityManager().getActiveTask();
	}

	public Integer getTaskID() {
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

	
	
	public List<IInteractionElement> getInteractionElements() {
		return interactionElements;
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

	private List<IInteractionElement> getCurrentContextElements(){

		IInteractionContextManager contextManager=		org.eclipse.mylyn.context.core.ContextCore.getContextManager();
		IInteractionContext context=contextManager.getActiveContext();
		return context.getAllElements();


	}
}