package nz.ac.auckland.devcoord.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
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
	private static String  previousCriticalString="";

	//private Text text;

	private Composite compositeOne;
	private Label labeOne;
	private ExpandItem itemOne ;

	private Composite compositeTwo;
	private Label labeTwo;
	private ExpandItem itemTwo ;



	private Action action1;
	private Action action2;
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
		//text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);



		RefreshDevCoord();
		makeActions();
		contributeToActionBars();

		initialiseGUI(parent);



	}

	private void initialiseGUI(Composite parent){
		GridLayout layout = new GridLayout ();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		org.eclipse.swt.widgets.ExpandBar bar=new ExpandBar(parent, 1);



		//=====================================
//		Display display = Display.getDefault();
//	
//		Shell shell=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//		
//		
//		Image image = new Image (display, 16, 16);
//		Image image2 = new Image (display, 16, 16);
//		GC gc = new GC(image2);
//		gc.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
//		gc.fillRectangle(image2.getBounds());
//		gc.dispose();
//		final Tray tray = display.getSystemTray ();
//		if (tray == null) {
//			System.out.println ("The system tray is not available");
//		} else {
//			final TrayItem item = new TrayItem (tray, SWT.NONE);
//			item.setToolTipText("SWT TrayItem");
//			item.addListener (SWT.Show, event -> System.out.println("show"));
//			item.addListener (SWT.Hide, event -> System.out.println("hide"));
//			item.addListener (SWT.Selection, event -> System.out.println("selection"));
//			item.addListener (SWT.DefaultSelection, event -> System.out.println("default selection"));
//			final Menu menu = new Menu (shell, SWT.POP_UP);
//			for (int i = 0; i < 8; i++) {
//				MenuItem mi = new MenuItem (menu, SWT.PUSH);
//				mi.setText ("Item" + i);
//				mi.addListener (SWT.Selection, event -> System.out.println("selection " + event.widget));
//				if (i == 0) menu.setDefaultItem(mi);
//			}
//			item.addListener (SWT.MenuDetect, event -> menu.setVisible (true));
//			item.setImage (image2);
//			item.setHighlightImage (image);
//		}
//		shell.setBounds(50, 50, 300, 200);
//		shell.open ();
//		while (!shell.isDisposed ()) {
//			if (!display.readAndDispatch ()) display.sleep ();
//		}
//		
		//===================================
		
		
		
		
		
		compositeOne = new Composite (bar, SWT.NONE);
		compositeOne.setLayout(layout);
		itemOne = new ExpandItem (bar, SWT.NONE, 0);
		itemOne.setText("All Tasks");
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

					labeOne.setText(getOverlappingTaskPairs());
					itemOne.setHeight(compositeOne.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
					labeOne.getParent().layout();

					
					String nextCriticalString=criticalString();
					String previousCriticalString=labeTwo.getText();
					labeTwo.setText(nextCriticalString);
					if (!previousCriticalString.equals(nextCriticalString)&& !nextCriticalString.equals("")) {
						
						action2.setEnabled(true);
						
					}
					else if(nextCriticalString.equals("")){
						action2.setEnabled(false);
						
						
						
					}
					
					
					


					
					itemTwo.setHeight(compositeTwo.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
					labeTwo.getParent().layout();


					//TaskInfo.printTaskInfoForAllTasks();

					//text.setText("TASKPAIRlistSIZE:"+pairs.size()+System.getProperty("line.separator")+taskWrapper.toString()+criticalString(pairs)+getCriticalScoreString());


				}
			}
		});
	}



	private String getOverlappingTaskPairs(){

		String separator=System.getProperty("line.separator");
		String toReturn="";
		for (TaskPair  pair: pairs) {
			toReturn+=" Task: "+getOtherTaskID(taskWrapper.getTaskID(), pair)+separator;


		}

		return toReturn;

	}
	private int getOtherTaskID(int ID,TaskPair pair){		

		if (pair.getID1()==ID) {		
			return pair.getID2();		
		}		
		else if (pair.getID2()==ID) {		
			return pair.getID1();		
		}		
		else{		

			return -1;		
		}		

	}

	private String criticalString(){
		String separator=System.getProperty("line.separator");
		String toReturn="";
		ArrayList<Task> criticalTasks=returnTasksThatAreCritical();



		for (Task task : criticalTasks) {
			toReturn+="Task: "+task.getTaskID()+"   Owner: "+task.getOwner()+separator;
			toReturn+="	Description: "+task.getDescription()+separator;


		}

		return toReturn;
	}

	private ArrayList<Task> returnTasksThatAreCritical(){


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
				action2.setEnabled(false);


			}
		};
		action2.setText("");
		action2.setToolTipText("Click To Show Critical Tasks");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR));
		action2.setEnabled(true);
	
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		//text.setFocus();

		//	table.setFocus();
		//	label.setFocus();
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