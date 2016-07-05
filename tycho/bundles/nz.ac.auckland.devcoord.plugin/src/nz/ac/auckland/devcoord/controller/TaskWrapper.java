package nz.ac.auckland.devcoord.controller;

import java.util.List;

import org.eclipse.mylyn.context.core.IInteractionContext;
import org.eclipse.mylyn.context.core.IInteractionContextManager;
import org.eclipse.mylyn.context.core.IInteractionElement;
import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.monitor.core.InteractionEvent.Kind;
import org.eclipse.mylyn.tasks.core.ITask;

import nz.ac.auckland.devcoord.database.Context_Structure;



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


	private boolean isActiveTask;
	private int taskID;
	private String taskHandle;
	private String taskLabel;
	private Kind interactionEventKind;
	private String structureHandle;
	private List<IInteractionElement> interactionElements;

	private boolean isEdit;
	private boolean isSelect;
	private Context_Structure context;

	private String kindType;
	/**
	 * Constructor
	 * */
	private TaskWrapper(InteractionEvent interactionEventArg) {

		//info for task model
		ITask activeTask=getActiveTask();
		if(activeTask != null){

			taskID=Integer.parseInt(activeTask.getTaskId());
			taskHandle=activeTask.getHandleIdentifier();
			taskLabel=activeTask.toString();
			
			//infor for context_Struture model
			structureHandle=interactionEventArg.getStructureHandle();
			setSelectOrEdit(interactionEventArg.getKind());
			interactionEventKind=interactionEventArg.getKind();
			context = new Context_Structure(structureHandle, isSelect,
					isEdit);
			
			isActiveTask = true;
		} else {
			isActiveTask = false;
		}

		

	}

	//constuctor to test
	private TaskWrapper(int taskID, String handle, String label, Context_Structure context){
		this.taskID = taskID;
		taskHandle = handle;
		taskLabel = label;
		this.context = context;
		this.structureHandle = context.getName();
	}


	public static TaskWrapper getTestWrappper(int taskID, String handle, String label, Context_Structure context){
		return new TaskWrapper(taskID, handle, label, context);
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

	private Kind getInteractionEventKind() {
		return interactionEventKind;
	}

	public String getStructureHandle() {
		return structureHandle;
	}

	public Context_Structure getContextStructure(){
		return context;
	}


	public List<IInteractionElement> getInteractionElements() {
		return interactionElements;
	}

	public boolean isTaskActive(){
		return this.isActiveTask;
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