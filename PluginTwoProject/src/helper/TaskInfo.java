package helper;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.mylyn.context.core.IInteractionContext;
import org.eclipse.mylyn.context.core.IInteractionContextManager;
import org.eclipse.mylyn.context.core.IInteractionElement;
import org.eclipse.mylyn.internal.context.ui.ContextUiPlugin;
import org.eclipse.mylyn.internal.monitor.ui.ActivityContextManager;
import org.eclipse.mylyn.internal.monitor.ui.MonitorUiPlugin;
import org.eclipse.mylyn.internal.monitor.usage.UiUsageMonitorPlugin;
import org.eclipse.mylyn.internal.resources.ui.ResourceInteractionMonitor;
import org.eclipse.mylyn.internal.tasks.core.AbstractTask;
import org.eclipse.mylyn.monitor.ui.AbstractUserInteractionMonitor;
import org.eclipse.mylyn.monitor.ui.IActivityContextManager;
import org.eclipse.mylyn.monitor.ui.MonitorUi;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.ui.contexts.IContextManager;


@SuppressWarnings("restriction")
public class TaskInfo {
	


	public static String getMoniterInfoAsAString(){
		String toReturn="";
		String separator=System.getProperty("line.separator");

		List<AbstractUserInteractionMonitor> moiters=	org.eclipse.mylyn.monitor.ui.MonitorUi.getSelectionMonitors();
MonitorUiPlugin plugin=MonitorUiPlugin.getDefault();



toReturn+="plugin:"+plugin+separator;
toReturn+="plugin.getLog():"+plugin.getLog()+separator;

ActivityContextManager  contextManager= plugin.getActivityContextManager();




toReturn+="contextManager:"+contextManager+separator;






toReturn+=""+separator;
toReturn+=""+separator;
toReturn+=""+separator;

toReturn+="===================================="+separator;

		
toReturn+="UiUsageMonitorPlugin.getDefault():"+UiUsageMonitorPlugin.getDefault()+separator;
		
		
toReturn+="===================================="+separator;
	
//iContextmaanger

IInteractionContextManager iContextManager=org.eclipse.mylyn.context.core.ContextCore.getContextManager();

toReturn+="iContextManager:"+iContextManager+separator;

IInteractionContext  iContext =iContextManager.getActiveContext();

toReturn+="iContext:"+iContext+separator;

List<IInteractionElement>elements = iContext.getAllElements();

for (IInteractionElement element : elements) {
	toReturn+="		element:"+element.toString()+separator;
}




	
toReturn+="===================================="+separator;
		
		for (AbstractUserInteractionMonitor moniter : moiters) {
			System.out.println(moniter.toString());
			toReturn+="moniter.toString():"+moniter.toString()+separator ;
			toReturn+="moniter.getEventKind():"+moniter.getEventKind()+separator ;

			




			
			
			
//				if (moniter.getClass().equals(ResourceInteractionMonitor.class)) {
//					toReturn+="		This is ResourceInteractionMonitor:";
////					((ResourceInteractionMonitor)moniter).;
////					moniter.
//					
//				//	org.eclipse.mylyn.monitor.ui.MonitorUi.
//					
//					
//				} 

				

			
		}
		



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
