package helper;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.eclipse.mylyn.context.core.IInteractionContext;
import org.eclipse.mylyn.context.core.IInteractionContextManager;
import org.eclipse.mylyn.context.core.IInteractionElement;
import org.eclipse.mylyn.internal.context.core.InteractionContextRelation;
import org.eclipse.mylyn.internal.tasks.core.AbstractTask;
import org.eclipse.mylyn.tasks.core.ITask;


@SuppressWarnings("restriction")
public class TaskInfo {
	


	public static String getContextInfoAsAString(){
		String toReturn="";
		String separator=System.getProperty("line.separator");

//iContextmaanger

IInteractionContextManager iContextManager=org.eclipse.mylyn.context.core.ContextCore.getContextManager();

toReturn+="iContextManager:"+iContextManager+separator;

IInteractionContext  iContext =iContextManager.getActiveContext();

toReturn+="iContext:"+iContext+separator;

List<IInteractionElement>elements = iContext.getAllElements();

for (IInteractionElement element : elements) {
	
	
	toReturn+="		element:"+element.toString()+separator;
	toReturn+="			element.getInterest().getClass():"+element.getClass()+separator;

	toReturn+="			element.getContentType():"+element.getContentType()+separator;
	toReturn+="			element.getHandleIdentifier():"+element.getHandleIdentifier()+separator;
	toReturn+="			element.getInterest():"+element.getInterest()+separator;
	toReturn+="			element.getInterest().getValue():"+element.getInterest().getValue()+separator;
		
	
	Collection<InteractionContextRelation> contextRelations=  element.getRelations();
	
	for (InteractionContextRelation interactionContextRelation : contextRelations) {
		toReturn+="				interactionContextRelation:"+interactionContextRelation+separator;
		toReturn+="				interactionContextRelation.getLabel():"+interactionContextRelation.getLabel()+separator;
		
	}
	
	
	
toReturn+="0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o0o"+separator;
	
	
//	CompositeContextElement  compositeContextElement=(CompositeContextElement)element;
//	
//	
//	List<InteractionContextElement>   interactionContextElements= compositeContextElement.getNodes();
//	
//	for (InteractionContextElement interactionContextElement : interactionContextElements) {
//		toReturn+="				interactionContextElement:"+interactionContextElement+separator;
//		toReturn+="				interactionContextElement.getHandleIdentifier():"+interactionContextElement.getHandleIdentifier()+separator;
//		
//		
//		
//	}
	
	
}





	
toReturn+="===================================="+separator;

	
	toReturn+="===================================="+separator;
	
return toReturn;
	}
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
	
	
	public static ArrayList<String> getTasksInfoAsAList(){
		ArrayList<String> toReturn=new ArrayList<String>();
		org.eclipse.mylyn.internal.tasks.core.TaskList list=org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskList();

		
		
toReturn.add("*DevCoord plugin*") ;


toReturn.add("*Refreshed At "+new SimpleDateFormat("HH:mm").format(new Date())+"*");





		Collection<AbstractTask> tasks=list.getAllTasks();

		//TODO
		//list.getTask(handleIdentifier)
		for (AbstractTask task : tasks) {
			
			
			toReturn.add("****************");

			toReturn.add("TASK:"+task.toString());
			
			toReturn.add("	  ID:"+task.getTaskId());
			toReturn.add("	  Handle:"+task.getHandleIdentifier());
			
			toReturn.add("	  Owner:"+task.getOwner()+". ID:"+task.getOwnerId());
			toReturn.add("	  Priority:"+task.getPriority());
			toReturn.add("	  URL:"+task.getUrl());

			toReturn.add("	  Repository URL:"+task.getRepositoryUrl());

			toReturn.add("	  KIND:"+task.getTaskKind());
			toReturn.add("	  Child Tasks-");
			for (ITask child : task.getChildren()) {

				toReturn.add("		Child:"+child.toString());
			}

			toReturn.add("	  Status:"+task.getStatus());

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
