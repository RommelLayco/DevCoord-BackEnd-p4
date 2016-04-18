//import devcoordplugin.Activator;
//
//import devcoordplugin.handlers.SampleHandler;
//
//import java.nio.file.attribute.UserDefinedFileAttributeView;
//import java.util.ArrayList;
//
//import org.eclipse.core.runtime.CoreException;
//import org.eclipse.mylyn.*;
//import org.eclipse.mylyn.internal.monitor.ui.MonitorUiPlugin;
//import org.eclipse.mylyn.internal.provisional.tasks.ui.wizards.QueryPageSearch;
//import org.eclipse.mylyn.internal.tasks.core.AbstractTask;
//import org.eclipse.mylyn.internal.tasks.core.TaskActivityManager;
//import org.eclipse.mylyn.internal.tasks.core.TaskList;
//import org.eclipse.mylyn.internal.tasks.core.TaskRepositoryManager;
//import org.eclipse.mylyn.internal.tasks.core.sync.GetTaskHistoryJob;
//import org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin;
//import org.eclipse.mylyn.internal.tasks.ui.editors.outline.QuickOutlineDialog.Filter;
//import org.eclipse.mylyn.tasks.core.TaskRepository;
//import org.eclipse.mylyn.tasks.core.data.TaskData;
//import org.omg.CORBA.LocalObject;
//import org.eclipse.mylyn.internal.bugzilla.*;
//import org.eclipse.mylyn.internal.bugzilla.core.BugzillaClient;
//import org.eclipse.mylyn.internal.bugzilla.core.BugzillaCorePlugin;
//import org.eclipse.mylyn.internal.bugzilla.core.BugzillaRepositoryConnector;
//import org.eclipse.mylyn.internal.discovery.core.model.ConnectorDescriptorKind;
//import org.eclipse.ui.IWorkbench;
//import org.eclipse.ui.IWorkbenchPage;
//import org.eclipse.ui.IWorkbenchWindow;
//import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.views.*;
//public class MainRunner {
//
//	public static void main(String[] args) {
//		   IWorkbench wb = PlatformUI.getWorkbench();
//		   IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
//		 
//
//		   // on new versions it may need to be changed to:
//		   IWorkbenchPage page = win.getActivePage();
//		
//		   
//		   
//		   
//		System.out.println(PlatformUI.isWorkbenchRunning());
//		System.out.println(PlatformUI.getWorkbench().getActiveWorkbenchWindow().toString());
//		
//
//TaskRepository repositoryOne=new TaskRepository("local", "local");
//System.out.println(repositoryOne.toString());
//
//
//System.out.println(repositoryOne.getCategory());
//
//TaskRepositoryManager repositoryManager=new TaskRepositoryManager();
//repositoryManager.addRepository(repositoryOne);
//
//System.out.println(repositoryManager.getAllRepositories().size());
//
//
////repositoryManager. Repository(repositoryOne);
//
//System.out.println(repositoryManager.getAllRepositories().size());
//
//
//
//
////TasksUiPlugin plugin=new TasksUiPlugin();
//
////System.out.println(TasksUiPlugin.get  getLocalTaskRepository().getRepositoryUrl());
//
//
////repositoryOne.
//
//
////TaskRepositoryManager repositoryManager=new TaskRepositoryManager();
////repositoryManager.addRepository(repositoryOne);
//
//
//
//
//
////repositoryManager.get
//
////TasksUiPlugin plugin=new TasksUiPlugin();
////plugin.manager
////TasksUiPlugin.getRepositoryManager().addRepository(repositoryOne);
//
//
//
//
////System.out.println(TasksUiPlugin.getRepositoryManager().getAllRepositories().size());
//
////System.out.println(repositoryOne.);
//
//		
//		
//		ArrayList<TaskRepository>repositories = (ArrayList<TaskRepository>) TasksUiPlugin.getRepositoryManager().getAllRepositories();
//			
//			
//			
//		int listSize=TasksUiPlugin.getTaskList().getAllTasks().size();
//		
//		
//		System.out.println(listSize);
//	TaskActivityManager	taskActivityManager = TasksUiPlugin.getTaskActivityManager();
//	
//	
//	
//	System.out.println(taskActivityManager.getAllDueTasks().size());
//		
//	System.out.println(taskActivityManager.toString());
//		
//		
//		QueryPageSearch pageSearch=new QueryPageSearch();
//		System.out.println(pageSearch.toQuery());
//		//pageSearch.addFilter("", value);
//		
//		
//		
//		
//		
//		String url="https://bugs.eclipse.org/bugs";
//			TaskRepository repository = new TaskRepository(BugzillaCorePlugin.CONNECTOR_KIND, url);
//			
//			System.out.println(repository.toString());
//	
//			//System.out.println(repository.get);
//			
//			BugzillaRepositoryConnector connector = new BugzillaRepositoryConnector();
//	
//			
//			try {
//				TaskData taskData = connector.getTaskData(repository, "237393", null);
//				
//				
//				//pageSearch.addFilter(key, value);
//				
//				
//			} catch (CoreException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//TaskList t=new TaskList();
//t.getAllTasks();
//
//for (AbstractTask task : t.getAllTasks()) {
//	System.out.println(task.toString());
//}
//		
////System.out.println(new MonitorUi().);
//	
//	}
//
//}
