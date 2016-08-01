package nz.ac.auckland.devcoord.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.mylyn.context.core.IInteractionContext;
import org.eclipse.mylyn.context.core.IInteractionContextManager;
import org.eclipse.mylyn.context.core.IInteractionElement;
import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.monitor.core.InteractionEvent.Kind;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskData;

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

	private String OS;
	private String platform;
	private String component;
	
	private String owner;
	private String description;
	private LocalDateTime time;

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
			structureHandle = formatHandle(interactionEventArg.getStructureHandle());
			//structureHandle=interactionEventArg.getStructureHandle();
			setSelectOrEdit(interactionEventArg.getKind());
			interactionEventKind=interactionEventArg.getKind();
			context = new Context_Structure(structureHandle, isSelect,
					isEdit);

			//get the map of attributes
			Map<String, TaskAttribute> map = getTask(activeTask);
			TaskAttribute attribute;
			
			attribute =  map.get("op_sys");
			this.OS = attribute.getValue();
			
			attribute = map.get("rep_platform");
			this.platform = attribute.getValue();
			
			attribute = map.get("component");
			this.component = attribute.getValue();
			
			this.owner = activeTask.getOwner();
			this.time = LocalDateTime.now();
			
			attribute = map.get("long_desc");
			this.description = attribute.getValue();

			isActiveTask = true;
		} else {
			isActiveTask = false;
		}



	}

	//constuctor to test
	private TaskWrapper(int taskID, String handle, String label, 
			String OS, String platform, String component, Context_Structure context){
		this.taskID = taskID;
		taskHandle = handle;
		taskLabel = label;
		this.OS = OS;
		this.platform = platform;
		this.component = component;
		
		this.context = context;
		this.structureHandle = context.getName();
	}


	public static TaskWrapper getTestWrappper(int taskID, String handle,
			String label, String OS, String platform,
			String component, Context_Structure context){
		return new TaskWrapper(taskID, handle, label, OS,
				platform, component, context);
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
	
	public String getOS(){
		return this.OS;
	}
	
	public String getPlatform(){
		return this.platform;
	}
	
	public String getComponent(){
		return this.component;
	}
	
	public String getOwner(){
		return this.owner;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public LocalDateTime getTime(){
		return this.time;
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
		toReturn +="OS: " + getOS() + separator;
		toReturn +="Platform: " + getPlatform() + separator;
		toReturn +="Component: " + getComponent() + separator;
		
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

	private static String formatHandle(String handle){
		//get name from src level
		//the first part is the name of the project we can
		//decided if we need the project name
		//String[] split1 = handle.split("/");

		//ignore field and method level
		//only looking at class level
		String[] split2 = handle.split("\\[");


		return split2[0];
	}

	private Map<String, TaskAttribute>  getTask(ITask task){

		try {
			TaskData t = org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskDataManager().getTaskData(task);
			return t.getRoot().getAttributes();

		} catch (CoreException e) {
			// TODO Auto-generated catch block
			System.err.println("============================");
			System.err.println("in method that gets map of attributes");
			e.printStackTrace();

		}

		//code should not get here
		System.err.println("=========== :Error in code");
		return null;

	}
}