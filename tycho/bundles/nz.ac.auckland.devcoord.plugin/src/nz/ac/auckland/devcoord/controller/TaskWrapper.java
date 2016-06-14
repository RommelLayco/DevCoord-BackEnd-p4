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

	private boolean isEdit;
	private boolean isSelect;

	private String kindType;
	/**
	 * Constructor
	 * */
	private TaskWrapper(InteractionEvent interactionEventArg) {

		//info for task model
		ITask activeTask=getActiveTask();
		taskID=Integer.parseInt(activeTask.getTaskId());
		taskHandle=activeTask.getHandleIdentifier();
		taskLabel=activeTask.toString();

		//infor for context_Struture model
		structureHandle=interactionEventArg.getStructureHandle();
		setSelectOrEdit(interactionEventArg.getKind());

	}



	/**Returns currently active task*/
	private ITask getActiveTask(){
		return	org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskActivityManager().getActiveTask();
	}

	private Integer getTaskID() {
		return taskID;
	}

	private String getTaskHandle() {
		return taskHandle;
	}
	private String getTaskLabel() {
		return taskLabel;
	}

	private Kind getInteractionEventKind() {
		return interactionEventKind;
	}

	private String getStructureHandle() {
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


	private void setSelectOrEdit(Kind kind){
		if(kind.equals(InteractionEvent.Kind.EDIT)){
			this.isEdit = true;
			this.isSelect = false;
		} else if(kind.equals(InteractionEvent.Kind.SELECTION)){
			this.isEdit = false;
			this.isSelect = true;
		} else {
			this.isEdit = false;
			this.isSelect = false;
		}
	}
}