package nz.ac.auckland.devcoord.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.*;

import nz.ac.auckland.devcoord.controller.Controller;
import nz.ac.auckland.devcoord.controller.InteractionEventHelper;
import nz.ac.auckland.devcoord.controller.TaskWrapper;
import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.Task;
import nz.ac.auckland.devcoord.database.TaskPair;
import nz.ac.auckland.devcoord.machinelearning.decisionTree.TrainDataGeneration;
import nz.ac.auckland.devcoord.machinelearning.testData.CriticalityUtility;

import org.eclipse.jface.viewers.*;
import org.eclipse.mylyn.commons.notifications.ui.AbstractUiNotification;
import org.eclipse.mylyn.context.core.ContextChangeEvent;
import org.eclipse.mylyn.context.core.IContextListener;
import org.eclipse.mylyn.internal.monitor.ui.MonitorUiPlugin;
import org.eclipse.mylyn.internal.tasks.core.ITaskListChangeListener;
import org.eclipse.mylyn.internal.tasks.core.TaskContainerDelta;
import org.eclipse.mylyn.internal.tasks.ui.ITaskListNotificationProvider;
import org.eclipse.mylyn.monitor.core.IInteractionEventListener;
import org.eclipse.mylyn.monitor.core.InteractionEvent;

import java.util.ArrayList;
import java.util.List;
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
	public static List<TaskPair> pairs;
	private Text text;
	private Action action1;
	private Controller controller;
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
		controller = new Controller();
		TrainDataGeneration.convertTrainCSVToArff();

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
					//TaskInfo.printTaskInfoForAllTasks();
					text.setText("TASKPAIRlistSIZE:"+pairs.size()+System.getProperty("line.separator")+taskWrapper.toString()+criticalString(pairs)+getCriticalScoreString());
				}
			}
		});
	}
private String getCriticalScoreString(){
	
	String separator=System.getProperty("line.separator");
	String toReturn="----------------------------"+separator;
	for (TaskPair  pair: pairs) {
		toReturn+=" Tasks: "+pair.getID1()+"  "+pair.getID2()+separator+
				"   CriticalSCrore: "+pair.getProximityScore()+separator+
				"   IsCritical: "+pair.isCritical()+separator;

	}

	return toReturn;
	
}
	private String criticalString(List<TaskPair> pairs){
		String separator=System.getProperty("line.separator");
		String toReturn="----------------------------"+separator;
		toReturn+="For Task "+taskWrapper.getTaskID()+",the following tasks are critical,Please consult the respective owner of the task-"+separator;
		ArrayList<Task> criticalTasks=returnTasksThatAreCritical(pairs);



		for (Task task : criticalTasks) {
			toReturn+="		Task: "+task.getTaskID()+"   Owner: "+task.getOwner()+separator;

		}

		return toReturn;
	}

	private ArrayList<Task> returnTasksThatAreCritical(List<TaskPair> pairs){


		ArrayList<Task> toReturn=new ArrayList<Task>();

		for (TaskPair taskPair : pairs) {
			if(taskPair.isCritical()){
				if(taskPair.getTask1().getTaskID()==taskWrapper.getTaskID()){

					toReturn.add(taskPair.getTask2());

				}
				else{

					toReturn.add(taskPair.getTask1());

				}



			}

		}


		return toReturn;

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

		if(taskWrapper != null){
			//update interaction event here
			Context_Structure file = controller.updateInfoOfActiveTask(taskWrapper);
			int task_id = taskWrapper.getTaskID();

			//getTask pairs
			pairs = controller.getTaskPairs(file, task_id, 14);


			//machine learning 
			pairs = CriticalityUtility.fillInCriticality(pairs);

			//persist taskpairs
			controller.saveTaskPairs(pairs);

			RefreshDevCoord();
		}
		RefreshDevCoord();
	}

	@Override
	public void startMonitoring() {
	}

	@Override
	public void stopMonitoring() {
	}

}