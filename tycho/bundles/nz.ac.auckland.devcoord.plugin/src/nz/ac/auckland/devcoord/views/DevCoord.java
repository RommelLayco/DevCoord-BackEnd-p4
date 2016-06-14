package nz.ac.auckland.devcoord.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.*;

import nz.ac.auckland.devcoord.controller.InteractionEventHelper;
import nz.ac.auckland.devcoord.controller.TaskInfo;
import nz.ac.auckland.devcoord.controller.TaskWrapper;

import org.eclipse.jface.viewers.*;
import org.eclipse.mylyn.commons.notifications.ui.AbstractUiNotification;
import org.eclipse.mylyn.context.core.ContextChangeEvent;
import org.eclipse.mylyn.context.core.IContextListener;
import org.eclipse.mylyn.internal.monitor.ui.MonitorUiPlugin;
import org.eclipse.mylyn.internal.tasks.core.AbstractTask;
import org.eclipse.mylyn.internal.tasks.core.ITaskListChangeListener;
import org.eclipse.mylyn.internal.tasks.core.TaskContainerDelta;
import org.eclipse.mylyn.internal.tasks.ui.ITaskListNotificationProvider;
import org.eclipse.mylyn.monitor.core.IInteractionEventListener;
import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.monitor.ui.MonitorUi;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.ITaskActivityListener;

import java.lang.annotation.Inherited;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.jface.action.*;

import org.eclipse.ui.*;

@SuppressWarnings("restriction")
/**The main plugin itself,contains a View that is shown in the plug window.*/
public class DevCoord extends ViewPart implements  ITaskListNotificationProvider, ITaskListChangeListener,IContextListener,IInteractionEventListener  {
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "nz.ac.auckland.devcoord.views.DevCoord";

	/**This is static {@link #helper.TaskWrapper} object is used to store the latest
	 * Wrapped {@link InteractionEvent} object. */
	public static TaskWrapper taskWrapper;
	private Text text;
	private Action action1;
/**
 * Automated generation from the HelloWorld Example.*/
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
		MonitorUiPlugin.getDefault().addInteractionListener(this);
		
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

/**
 * This method is Refreshed when-
 * A new task is added/edited
 * A new {@link #InterationEvent} is lodged
 * 
 * This method places that event in the static handle {@link #taskWrapper} to be used by
 * other classes.
 * */
	private void RefreshDevCoord(){
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
					if (taskWrapper!=null) {
					text.setText(taskWrapper.toString()+TaskInfo.getInteractionEventListAsAString());
				}
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
	/**{@inheritDoc}*/
	@Override
	public void containersChanged(Set<TaskContainerDelta> arg0) {
		RefreshDevCoord();
	}

	@Override
	public Set<AbstractUiNotification> getNotifications() {
		return null;
	}
	
	/**{@inheritDoc}*/
	@Override
	public void contextChanged(ContextChangeEvent arg0) {
		RefreshDevCoord();

	}


	/**{@inheritDoc}
	 * 
	 * It creates a new {@link TaskWrapper} object and stores it in {@link #taskWrapper}
	 * */
	@Override
	public void interactionObserved(InteractionEvent arg0) {
		System.out.println("EVENT TIME:"+arg0.getDate().getTime());
		taskWrapper=InteractionEventHelper.getTaskWrapperObject(arg0);
		//update interaction event here,
		RefreshDevCoord();
	}

	@Override
	public void startMonitoring() {
	}

	@Override
	public void stopMonitoring() {
	}

}