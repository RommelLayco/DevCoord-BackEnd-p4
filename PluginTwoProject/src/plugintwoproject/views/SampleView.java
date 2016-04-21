package plugintwoproject.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.*;

import helper.TaskInfo;

import org.eclipse.jface.viewers.*;
import org.eclipse.mylyn.commons.notifications.ui.AbstractUiNotification;
import org.eclipse.mylyn.internal.tasks.ui.ITaskListNotificationProvider;
import java.util.Set;

import org.eclipse.jface.action.*;

import org.eclipse.ui.*;




/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

@SuppressWarnings("restriction")
public class SampleView extends ViewPart implements ITaskListNotificationProvider  {
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "projtwo.views.SampleView";


	
private Text text;
	
	private Action action1;




	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public SampleView() {
		super();
		org.eclipse.mylyn.internal.tasks.ui.TasksUiPlugin.getTaskListNotificationManager().addNotificationProvider(this);;




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


		
		text.setText(TaskInfo.getTasksInfoAsAString());


		
		
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
//		viewer.getControl()   .setFocus();
	}

	@Override
	public Set<AbstractUiNotification> getNotifications() {
		System.out.println("NOTIDIED");
		text.setText(TaskInfo.getTasksInfoAsAString());
			return null;
	}

	



}
