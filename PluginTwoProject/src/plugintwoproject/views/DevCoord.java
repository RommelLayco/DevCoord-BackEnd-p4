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
import org.eclipse.mylyn.internal.tasks.core.ITaskListChangeListener;
import org.eclipse.mylyn.internal.tasks.core.TaskContainerDelta;
import org.eclipse.mylyn.internal.tasks.ui.ITaskListNotificationProvider;

import java.util.Set;

import org.eclipse.jface.action.*;

import org.eclipse.ui.*;

@SuppressWarnings("restriction")
public class DevCoord extends ViewPart implements ITaskListNotificationProvider, ITaskListChangeListener,IContextListener   {
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
		text.setText(TaskInfo.getTasksInfoAsAString()+TaskInfo.getContextInfoAsAString());
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


}
