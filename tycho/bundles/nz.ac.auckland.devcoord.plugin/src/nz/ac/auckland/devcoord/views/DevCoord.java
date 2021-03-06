package nz.ac.auckland.devcoord.views;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.*;
import nz.ac.auckland.devcoord.controller.Controller;
import nz.ac.auckland.devcoord.controller.InteractionEventHelper;
import nz.ac.auckland.devcoord.controller.TaskInfo;
import nz.ac.auckland.devcoord.controller.TaskWrapper;
import nz.ac.auckland.devcoord.database.Context_Structure;
import nz.ac.auckland.devcoord.database.TaskPair;
import nz.ac.auckland.devcoord.machineLearning.decisionTree.TrainDataGeneration;
import nz.ac.auckland.devcoord.machineLearning.testData.CriticalityUtility;
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
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ILock;
import org.eclipse.core.runtime.jobs.Job;
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
	private Composite compositeOne;
	private Label labeOne;
	private ExpandItem itemOne ;
	private Composite compositeTwo;
	private Label labeTwo;
	private ExpandItem itemTwo ;
	private Composite compositeThree;
	private Label labeThree;
	private ExpandItem itemThree ;
	private Action action1;
	private Action action2;
	private Controller controller;
	// private static ILock lock = Platform.getJobManager().newLock();
	private static ILock lock = Job.getJobManager().newLock();
	
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
		try{
			lock.acquire();
			controller = new Controller();
		} finally {
			lock.release();
		}
		TrainDataGeneration.convertTrainCSVToArff();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		RefreshDevCoord();
		makeActions();
		contributeToActionBars();
		initialiseGUI(parent);
	}
	
	private void initialiseGUI(Composite parent){
		GridLayout layout = new GridLayout ();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		ScrolledComposite scroll=new ScrolledComposite(parent, SWT.V_SCROLL);
		org.eclipse.swt.widgets.ExpandBar bar=new ExpandBar(scroll, 1);
		compositeOne = new Composite (bar, SWT.NONE);
		compositeOne.setLayout(layout);
		itemOne = new ExpandItem (bar, SWT.NONE, 0);
		itemOne.setText("Tasks sharing same context structure");
		itemOne.setHeight(compositeOne.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemOne.setControl(compositeOne);
		compositeOne.setLayout(layout);
		labeOne=new Label(compositeOne, 1);
		labeOne.setText("0000");
		labeOne.setEnabled(true);
		compositeTwo = new Composite (bar, SWT.None);
		compositeTwo.setLayout(layout);
		itemTwo = new ExpandItem (bar, SWT.None, 0);
		itemTwo.setText("Critical Tasks");
		itemTwo.setHeight(compositeTwo.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemTwo.setControl(compositeTwo);
		compositeTwo.setLayout(layout);
		labeTwo=new Label(compositeTwo, 1);
		labeTwo.setText("0000");
		labeTwo.setEnabled(true);
		compositeThree = new Composite (bar, SWT.None);
		compositeThree.setLayout(layout);
		itemThree = new ExpandItem (bar, SWT.None, 0);
		itemThree.setText("All Tasks");
		itemThree.setHeight(compositeThree.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemThree.setControl(compositeThree);
		compositeThree.setLayout(layout);
		labeThree=new Label(compositeThree, 1);
		labeThree.setText("0000");
		labeThree.setEnabled(true);
		/*Reference scroll stuff:http://stackoverflow.com/questions/22964093/programmatically-scroll-expandbar*/
		Listener updateScrolledSize = new Listener()
		{
			@Override
			public void handleEvent(Event arg0)
			{
				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run()
					{
						scroll.setMinSize(bar.computeSize(SWT.DEFAULT, SWT.DEFAULT));
					}
				});
			}
		};
		bar.addListener(SWT.Expand, updateScrolledSize);
		bar.addListener(SWT.Collapse, updateScrolledSize);
		scroll.setContent(bar);
		scroll.setExpandHorizontal(true);
		scroll.setExpandVertical(true);
		scroll.setMinSize(bar.computeSize(SWT.DEFAULT, SWT.DEFAULT));
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
					action1.setChecked(true);
					labeOne.setText(DevCoordUtility.getOverlappingTaskPairs(pairs,taskWrapper));
					itemOne.setHeight(compositeOne.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
					labeOne.getParent().layout();
					String nextCriticalString=DevCoordUtility.criticalString(pairs,taskWrapper);
					String previousCriticalString=labeTwo.getText();
					labeTwo.setText(nextCriticalString);
					if (!previousCriticalString.equals(nextCriticalString)&& !nextCriticalString.equals("")) {
						action2.setEnabled(true);
						itemTwo.setExpanded(true);
					}
					else if(nextCriticalString.equals("")){
						action2.setEnabled(false);
					}
					itemTwo.setHeight(compositeTwo.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
					labeTwo.getParent().layout();
					labeThree.setText(TaskInfo.getTasksInfoAsAString());
					itemThree.setHeight(compositeThree.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
					labeThree.getParent().layout();
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
		manager.add(action2);
		manager.add(new Separator());
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
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
		action2 = new Action() {
			public void run() {
				itemTwo.setExpanded(false);
				action2.setEnabled(false);
			}
		};
		action2.setText("");
		action2.setToolTipText("Click To collapse Critical Tasks");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR));
		action2.setEnabled(true);
	}
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
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
	 * 
	 * This is where the event is detected and processed to find task pairs
	 * 
	 * code to run database processing in background taken from
	 * 
	 * https://www.eclipse.org/articles/Article-Concurrency/jobs-api.html
	 * 
	 * */
	@Override
	public void interactionObserved(InteractionEvent arg0) {
		System.err.println("EVENT TIME:"+ LocalTime.now());
		final Job job = new Job("Database calls") {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					lock.acquire();
					System.err.println("after lock time start:"+ LocalTime.now());
					taskWrapper=InteractionEventHelper.getTaskWrapperObject(arg0);
					if(taskWrapper != null ){
						//update interaction event here
						Context_Structure file = controller.updateInfoOfActiveTask(taskWrapper);
						int task_id = taskWrapper.getTaskID();
						//getTask pairs
						pairs = controller.getTaskPairs(file, task_id, 14);
						//machine learning 
						pairs = CriticalityUtility.fillInCriticality(pairs);
						//persist taskpairs
						controller.saveTaskPairs(pairs);
					}
					return Status.OK_STATUS;
				} finally {
					lock.release();
					RefreshDevCoord();
				}
			}
		};
		job.schedule();
	}
	@Override
	public void startMonitoring() {
	}
	@Override
	public void stopMonitoring() {
	}
}