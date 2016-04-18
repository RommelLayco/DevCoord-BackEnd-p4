import devcoordplugin.Activator;
import devcoordplugin.handlers.SampleHandler;

import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.mylyn.*;
import org.eclipse.mylyn.internal.monitor.ui.MonitorUiPlugin;
import org.eclipse.mylyn.internal.tasks.core.AbstractTask;
import org.eclipse.mylyn.internal.tasks.core.TaskList;
import org.eclipse.mylyn.internal.tasks.core.sync.GetTaskHistoryJob;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.internal.bugzilla.*;
import org.eclipse.mylyn.internal.bugzilla.core.BugzillaCorePlugin;
import org.eclipse.mylyn.internal.bugzilla.core.BugzillaRepositoryConnector;
public class MainRunner {

	public static void main(String[] args) {
		
		String url="https://bugs.eclipse.org/bugs";
			TaskRepository repository = new TaskRepository(BugzillaCorePlugin.CONNECTOR_KIND, url);
			
			System.out.println(repository.toString());
	
			//System.out.println(repository.get);
			
			BugzillaRepositoryConnector connector = new BugzillaRepositoryConnector();
	
			try {
				TaskData taskData = connector.getTaskData(repository, "1", null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
TaskList t=new TaskList();
t.getAllTasks();

for (AbstractTask task : t.getAllTasks()) {
	System.out.println(task.toString());
}
		
//System.out.println(new MonitorUi().);
	
	}

}
