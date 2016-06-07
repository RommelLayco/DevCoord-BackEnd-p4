package nz.ac.auckland.devcoord.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.eclipse.mylyn.context.core.IInteractionContext;
import org.eclipse.mylyn.context.core.IInteractionContextManager;
import org.eclipse.mylyn.context.core.IInteractionElement;
import org.eclipse.mylyn.internal.tasks.core.AbstractTask;
import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.tasks.core.ITask;


@SuppressWarnings("restriction")
/**
 * Helper class containing methods to return string representation of context and task lists.
 * */
public class TaskInfo {
	/**
	 * Returns a 'toString' representation of he current context.
	 * 
	 * */
	public static String getContextInfoAsAString(){
		String toReturn="";
		String separator=System.getProperty("line.separator");
		toReturn+="*DevCoord plugin*"+separator ;
		toReturn+="*Refreshed At "+new SimpleDateFormat("HH:mm:ss").format(new Date())+separator ;
		toReturn+="===================================="+separator;
		IInteractionContextManager iContextManager=org.eclipse.mylyn.context.core.ContextCore.getContextManager();
		IInteractionContext  iContext =iContextManager.getActiveContext();
		List<IInteractionElement>elements = iContext.getAllElements();
		for (IInteractionElement element : elements) {
			toReturn+="		element:"+element.toString()+separator;
			toReturn+="		element.getContentType():"+element.getContentType()+separator;
			toReturn+="			getInterest().getValue():"+element.getInterest().getValue()+separator;
			toReturn+="			Content Type:"+element.getContentType()+separator;
			toReturn+="			Handle Identifier:"+element.getHandleIdentifier()+separator;
		}
		toReturn+="===================================="+separator;
		return toReturn;
	}

	/**
	 * Returns a String representation of the InteractionEvent List
	 * */
	public static String getInteractionEventListAsAString(){
		String toReturn="";
		String separator=System.getProperty("line.separator");
		toReturn+="*DevCoord plugin*"+separator ;
		toReturn+="*Refreshed At "+new SimpleDateFormat("HH:mm:ss").format(new Date())+separator ;
		IInteractionContextManager iContextManager=org.eclipse.mylyn.context.core.ContextCore.getContextManager();
		IInteractionContext  iContext =iContextManager.getActiveContext();
		List<InteractionEvent> events=iContext.getInteractionHistory();
		/**
		 * Sorting solely on date and time,not kind.
		 * */
		Collections.sort(events, new Comparator<InteractionEvent>() {
			public int compare(InteractionEvent m1, InteractionEvent m2) {
				return m2.getDate().compareTo(m1.getDate());
			}
		});
		for (InteractionEvent interactionEvent : events) {
			toReturn+="____________________________________" +separator;
			toReturn+="getKind:"+interactionEvent.getKind() +separator;
			toReturn+="				getStructureHandle().toString():"+interactionEvent.getStructureHandle().toString() +separator;
			toReturn+="				getDelta():"+interactionEvent.getDelta()+separator;
			toReturn+="				getgetDate().getTime():"+interactionEvent.getDate()+separator;
			toReturn+="				getInterestContribution():"+interactionEvent.getInterestContribution()+separator;
			toReturn+=separator;
		}
		toReturn+="____________________________________" +separator;
		return toReturn;
	}

	/**
	 * Prints out all the Tasks in the console.
	 * */	
	public static void printTaskInfoForAllTasks(){
		org.eclipse.mylyn.internal.tasks.core.TaskList list=org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskList();
		Collection<AbstractTask> tasks=list.getAllTasks();
		for (AbstractTask task : tasks) {
			System.out.println("=====================================");
			System.out.println("TASK:"+task.toString());
			System.out.println("	TASK ID:"+task.getTaskId());
			System.out.println("	TASK Owner:"+task.getOwner()+". ID:"+task.getOwnerId());
			System.out.println("	TASK Priority:"+task.getPriority());
			System.out.println("	TASK URL:"+task.getUrl());
			System.out.println("	TASK Repository URL:"+task.getRepositoryUrl());
			System.out.println("	TASK KIND:"+task.getTaskKind());
			System.out.println("	TASK Child Tasks-");
			for (ITask child : task.getChildren()) {
				System.out.println("		Child  TASK :"+child.toString());
			}
			System.out.println("	TASK Status:"+task.getStatus());
			System.out.println("=====================================");
		}
	}

	/**
	 * Makes a list of strings out of the information for all the existing tasks.
	 * */
	public static ArrayList<String> getTasksInfoAsAList(){
		ArrayList<String> toReturn=new ArrayList<String>();
		org.eclipse.mylyn.internal.tasks.core.TaskList list=org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskList();
		toReturn.add("*DevCoord plugin*") ;
		toReturn.add("*Refreshed At "+new SimpleDateFormat("HH:mm").format(new Date())+"*");
		Collection<AbstractTask> tasks=list.getAllTasks();
		for (AbstractTask task : tasks) {
			toReturn.add("****************");
			toReturn.add("TASK:"+task.toString());
			toReturn.add("	  ID:"+task.getTaskId());
			toReturn.add("	  Handle:"+task.getHandleIdentifier());
			toReturn.add("	  IsActive?:"+task.isActive());
			toReturn.add("	  Owner:"+task.getOwner()+". ID:"+task.getOwnerId());
			toReturn.add("	  Priority:"+task.getPriority());
			toReturn.add("	  URL:"+task.getUrl());
			toReturn.add("	  Repository URL:"+task.getRepositoryUrl());
			toReturn.add("	  KIND:"+task.getTaskKind());
			toReturn.add("	  Child Tasks-");
			for (ITask child : task.getChildren()) {
				toReturn.add("		Child:"+child.toString());
			}
			toReturn.add("****************");
		}
		return toReturn;
	}

	/**
	 * Uses the list returned form {@link #getTasksInfoAsAList() to create a single string out of the info of all tasks.}
	 * */	
	public static String getTasksInfoAsAString(){
		ArrayList<String> list=getTasksInfoAsAList();
		String toReturn="";
		String separator=System.getProperty("line.separator");
		for (String string : list) {
			toReturn+=string+separator ;
		}
		return toReturn;
	}
}
