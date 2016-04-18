  package com.example.helloworld;

   import org.eclipse.swt.widgets.Composite;
   import org.eclipse.swt.widgets.Label;
import org.eclipse.mylyn.internal.tasks.core.TaskList;
import org.eclipse.mylyn.internal.tasks.ui.views.TaskListView;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

   public class HelloWorldView extends ViewPart {
      Label label;
      public HelloWorldView() {
      }
      public void createPartControl(Composite parent) {
         label = new Label(parent, SWT.WRAP);
         
         
         IWorkbench wb = PlatformUI.getWorkbench();
		   IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		 

		   // on new versions it may need to be changed to:
		   IWorkbenchPage page = win.getActivePage();
		   
	IViewPart[] iViewParts=	   page.getViews();
		   
		// System.out.println(wb.toString());
	
	for (IViewPart iViewPart : iViewParts) {
		
		   if (iViewPart.getTitle().equals("Task List")) {
			   label.setText(label.getText()+"      "+iViewPart.getTitle());
			
		}
		   
//		   iViewPart.get

//		   TaskListView 		   
	}
		   
         
         
      }
      public void setFocus() {
         // set focus to my widget.  For a label, this doesn't
         // make much sense, but for more complex sets of widgets
         // you would decide which one gets the focus.
      }
   }