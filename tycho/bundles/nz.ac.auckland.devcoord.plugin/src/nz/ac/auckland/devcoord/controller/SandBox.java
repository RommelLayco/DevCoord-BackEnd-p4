package nz.ac.auckland.devcoord.controller;

import java.security.KeyStore.Entry.Attribute;
import java.util.Map;
import java.util.Set;

import org.eclipse.mylyn.internal.bugzilla.core.BugzillaCorePlugin;
import org.eclipse.mylyn.internal.bugzilla.core.BugzillaRepositoryConnector;
import org.eclipse.mylyn.internal.bugzilla.core.BugzillaTaskDataCollector;
import org.eclipse.mylyn.internal.bugzilla.ui.tasklist.BugzillaConnectorUi;
import org.eclipse.mylyn.internal.tasks.core.TaskTask;
import org.eclipse.mylyn.tasks.core.AbstractRepositoryConnector;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.ui.TasksUi;
import org.eclipse.mylyn.tasks.ui.TasksUiImages;

public class SandBox {
@SuppressWarnings("restriction")
public static void main(String[] args) {

//	System.out.println("org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskList().getTask.getClass().toString():"+org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskList().getTask("https://app.devzing.com/devcoordp4/bugzilla", "1").getClass().toString());
	
TaskTask taskTask=	(TaskTask) org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskList().getTask("https://app.devzing.com/devcoordp4/bugzilla", "1");

Map<String, String> mapT=	taskTask.getAttributes();
System.out.println(mapT.size());

//	TaskRepository repository=new TaskRepository("bugzilla", "https://app.devzing.com/devcoordp4/bugzilla");
//	
//	TaskAttributeMapper mapper=new TaskAttributeMapper(repository);
//	TaskData data=new  TaskData(mapper, "bugzilla",  "https://app.devzing.com/devcoordp4/bugzilla", "1");
//	
//	
//Map<String, TaskAttribute> dataAttr=	data.getRoot().getAttributes();
//	System.out.println(dataAttr.size());
//	
//	data.
//	
//	
//	
//	AbstractRepositoryConnector connector= TasksUi.getRepositoryConnector("bugzilla");
//	 AbstractTaskDataHandler dataHandler = connector.getTaskDataHandler();
//	
//	dataHandler.
//	
//	org.eclipse.mylyn.internal.tasks.core.TaskList list=org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskList();
//	
//	Set<ITask> tasks= list.getTasks("https://app.devzing.com/devcoordp4/bugzilla");
//	
//	for (ITask iTask : tasks) {
//		System.out.println(iTask.getTaskId());
//
//		//Platform
//		for (Map.Entry<String, String> s : iTask.getAttributes().entrySet()) {
//			System.out.println("		"+s);
//		}
//		
//	}
//	
//	
//	org.eclipse.mylyn.internal.bugzilla.ui.tasklist.BugzillaConnectorUi bugzillaConnectorUi=new BugzillaConnectorUi();
//	//bugzillaConnectorUi.
//	
//	System.out.println(bugzillaConnectorUi.getConnector().toString());
//
//	
//	TaskRepository repository=new TaskRepository("bugzilla", "https://app.devzing.com/devcoordp4/bugzilla");
//	System.out.println(repository.toString());
	//System.out.println(repository.);
	
	
	
//	BugzillaRepositoryConnector bugzillaRepositoryConnector=new BugzillaRepositoryConnector();
//	
//	System.out.println("bugzillaRepositoryConnector.getConnectorKind():"+bugzillaRepositoryConnector.getConnectorKind());
//	
//	bugzillaRepositoryConnector.url
//	
//	org.eclipse.mylyn.tasks.core.TaskRepository taskRepository=new TaskRepository("bugzilla", "https://app.devzing.com/devcoordp4/bugzilla");
//	
//	System.out.println("taskRepository:"+taskRepository.set);

	
	
}
}
