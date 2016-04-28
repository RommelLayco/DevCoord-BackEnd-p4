package plugintwoproject.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.*;

import helper.TaskInfo;

import org.eclipse.jface.viewers.*;
import org.eclipse.mylyn.commons.notifications.ui.AbstractUiNotification;
import org.eclipse.mylyn.context.core.ContextChangeEvent;
import org.eclipse.mylyn.context.core.IContextListener;
import org.eclipse.mylyn.internal.tasks.core.AbstractTask;
import org.eclipse.mylyn.internal.tasks.core.ITaskListChangeListener;
import org.eclipse.mylyn.internal.tasks.core.TaskContainerDelta;
import org.eclipse.mylyn.internal.tasks.ui.ITaskListNotificationProvider;
import org.eclipse.mylyn.monitor.core.IInteractionEventListener;
import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.ITaskActivityListener;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.jface.action.*;

import org.eclipse.ui.*;

@SuppressWarnings("restriction")
public class DevCoord extends ViewPart implements  ITaskListNotificationProvider, ITaskListChangeListener,IContextListener,IInteractionEventListener  {
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "projtwo.views.DevCoord";



	private Text text;

	private Action action1;




	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public DevCoord() {
		super();
		/**
		 * Classes being used
		 * */
		org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskList().addChangeListener(this);
		org.eclipse.mylyn.context.core.ContextCore.getContextManager().addListener(this);
		//org.eclipse.mylyn.tasks.ui.TasksUi.getTaskActivityManager().addActivityListener(this);
		org.eclipse.mylyn.monitor.ui.MonitorUi.addInteractionListener(this);
		
	//	Collection<AbstractTask> abstractTasks=	org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskList().getAllTasks();

//		org.eclipse.mylyn.tasks.ui.TasksUi.getTaskActivityManager().getActiveTask().
	
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {

		text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		RefreshDevCoord();
		makeActions();
		contributeToActionBars();
	}


	private void RefreshDevCoord(){
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
	//	text.setText(TaskInfo.getTasksInfoAsAString()+TaskInfo.getContextInfoAsAString());
				text.setText(TaskInfo.getContextInfoAsAString());
				
			}
		});

}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());

	}


	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);

	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				RefreshDevCoord();

			}
		};
		action1.setText("Refresh DevCoord");
		action1.setToolTipText("Press to Referesh DevCoord");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));


	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		text.setFocus();

	}

	@Override
	public void containersChanged(Set<TaskContainerDelta> arg0) {

		RefreshDevCoord();

	}

	@Override
	public Set<AbstractUiNotification> getNotifications() {
		return null;
	}

	@Override
	public void contextChanged(ContextChangeEvent arg0) {
		RefreshDevCoord();

	}

	

	@Override
	public void interactionObserved(InteractionEvent arg0) {
		System.out.println("arg0:"+arg0.getKind());
		System.out.println("		arg0.getStructureHandle():"+arg0.getStructureHandle());
		
		TaskInfo.setLatestEvent(arg0);
		
		RefreshDevCoord();
		
	}

	@Override
	public void startMonitoring() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopMonitoring() {
		// TODO Auto-generated method stub
		
	}


}
