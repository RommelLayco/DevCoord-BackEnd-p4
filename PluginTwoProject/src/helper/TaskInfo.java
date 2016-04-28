package helper;


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
import org.eclipse.mylyn.internal.context.core.AggregateInteractionEvent;
import org.eclipse.mylyn.internal.context.core.InteractionContextRelation;
import org.eclipse.mylyn.internal.tasks.core.AbstractTask;
import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.tasks.core.ITask;


@SuppressWarnings("restriction")
public class TaskInfo {
	private static InteractionEvent latestEvent=null;
	
	public static InteractionEvent getLatestEvent() {
		
		
		return latestEvent;
	}
	public static void setLatestEvent(InteractionEvent latestEventArg) {
		System.out.println("latestEventArg coming in method:"+latestEventArg.getKind());
		if (latestEvent!=null) {
		System.out.println("latestEvent before:"+latestEvent.getKind());	
		} else {
			System.out.println("latestEvent before:"+"NULL");
			
		}
		


			
				latestEvent = latestEventArg;
		
			System.out.println("latestEvent after:"+latestEvent.getKind());	
				
		
	}
	public static String getContextInfoAsAString(){
		String toReturn="";
		String separator=System.getProperty("line.separator");
		toReturn+="*DevCoord plugin*"+separator ;
		toReturn+="*Refreshed At "+new SimpleDateFormat("HH:mm:ss").format(new Date())+separator ;
		
		

		toReturn+="===================================="+separator;
		IInteractionContextManager iContextManager=org.eclipse.mylyn.context.core.ContextCore.getContextManager();
		/*
		if (getLatestEvent()!=null) {
			toReturn+="				getLatestEvent().getKind:"+getLatestEvent().getKind() +separator;
			toReturn+="				getLatestEvent().getStructureHandle().toString():"+getLatestEvent().getStructureHandle().toString() +separator;

			
		}
		
		toReturn+="===================================="+separator;
	*/	
		//	toReturn+="iContextManager:"+iContextManager+separator;


		IInteractionContext  iContext =iContextManager.getActiveContext();
	//	toReturn+="iContext:"+iContext+separator;
	
		List<InteractionEvent> events=iContext.getInteractionHistory();

		
		toReturn+="____________________________________" +separator;
		
		if (events.size()>0) {
			InteractionEvent last=events.get(events.size()-1);
			
			
			
			toReturn+="				last.getKind:"+last.getKind() +separator;
			toReturn+="				last.getStructureHandle().toString():"+last.getStructureHandle().toString() +separator;


			
		}
		toReturn+="____________________________________" +separator;
		

		Collections.sort(events, new Comparator<InteractionEvent>() {
		    public int compare(InteractionEvent m1, InteractionEvent m2) {
		        return m2.getDate().compareTo(m1.getDate());
		    }
		} );
		
		for (InteractionEvent interactionEvent : events) {
			if (!interactionEvent.getKind().equals(InteractionEvent.Kind.PROPAGATION)) {
				toReturn+="				getKind:"+interactionEvent.getKind() +separator;
				toReturn+="				getStructureHandle().toString():"+interactionEvent.getStructureHandle().toString() +separator;

				toReturn+="				getStructureHandle().getDelta():"+interactionEvent.getDelta()+separator;
				toReturn+="				getStructureHandle().getgetDate():"+interactionEvent.getDate()+separator;


			}
			
		}
		/*		*/
		
		
		
		
//		toReturn+="		event :"+event.getClass() +separator;
//		toReturn+="		event.getKind().name():"+event.getKind().name() +separator;
	
		

		
		/**
		List<IInteractionElement>elements = iContext.getAllElements();
		
		
		for (IInteractionElement element : elements) {
			
			toReturn+="		element:"+element.toString()+separator;
			toReturn+="		element.getContentType():"+element.getContentType()+separator;
			
//			toReturn+="		element.getContentType():"+element. +separator;
			
			toReturn+="			getInterest().getValue():"+element.getInterest().getValue()+separator;
			toReturn+="			Content Type:"+element.getContentType()+separator;
			toReturn+="			Handle Identifier:"+element.getHandleIdentifier()+separator;
		}
		*/
		toReturn+="===================================="+separator;
		return toReturn;
	}
	public static void printTaskInfoForAllTasks(){
		/**
		 * Classes being used
		 * */
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


	public static ArrayList<String> getTasksInfoAsAList(){

		/**
		 * Classes being used
		 * */
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
			//toReturn.add("	  Status:"+task.getStatus());
			toReturn.add("****************");
		}
		return toReturn;
	}

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
