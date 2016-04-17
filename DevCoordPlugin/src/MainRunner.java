import devcoordplugin.Activator;
import devcoordplugin.handlers.SampleHandler;

import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.eclipse.mylyn.*;
import org.eclipse.mylyn.internal.monitor.ui.MonitorUiPlugin;
import org.eclipse.mylyn.internal.tasks.core.TaskList;
import org.eclipse.mylyn.internal.tasks.core.sync.GetTaskHistoryJob;

public class MainRunner {

	public static void main(String[] args) {
		

		System.out.println(MonitorUiPlugin.getDefault().toString());
		
//System.out.println(new MonitorUi().);
	
	}

}
