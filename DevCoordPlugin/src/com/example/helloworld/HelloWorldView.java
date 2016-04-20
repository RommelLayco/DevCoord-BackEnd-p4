  package com.example.helloworld;

   import org.eclipse.swt.widgets.Composite;
   import org.eclipse.swt.widgets.Label;

import java.util.Set;

import org.eclipse.mylyn.monitor.ui.MonitorUi;
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
    	
    	//  Set<IWorkbenchWindow> windows  =MonitorUi.getMonitoredWindows();
    	  
    	  
//    	  org.eclipse.mylyn.monitor.ui.
  
    	  
    	  
         label = new Label(parent, SWT.WRAP);
      //   label.setText(label.getText()+System.getProperty("line.separator")+"windows.size():"+windows.size());

         
//         IWorkbench wb = PlatformUI.getWorkbench();
//		   IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
//		 
//
//
//
//		   // on new versions it may need to be changed to:
//		   IWorkbenchPage page = win.getActivePage();
//		   
//	IViewPart[] iViewParts=	   page.getViews();
//		   
//		// System.out.println(wb.toString());
//	
//	for (IViewPart iViewPart : iViewParts) {
//		//  iViewParts.getClass().getName().equals(TaskListView.class.getName());
//		  label.setText(label.getText()+System.getProperty("line.separator")+"Name Of View:"+iViewPart.getTitle());
//		   label.setText(label.getText()+"     Adapter class null:"+iViewPart.getClass().getName());
//		  
//		   if (iViewPart.getTitle().equals("Task List")) {
//			   label.setText(label.getText()+"     IT is Tasklist:");
//			 //  label.setText("  Class is:"+iViewParts.getClass().getName());
//			   
//				
//			   
//			   
//			   
//			 
//			   
//						   
//		 //  TaskListView view=(TaskListView)iViewPart;
//			   
//		  // label.setText(label.getText()+"Is TaskViewLIst:");
//			   
//			   
//		}
//		   
//		   
//		   
//		   
//		   
////		   iViewPart.get
//
////		   TaskListView 		   
//	}
		   
         
         
      }
      public void setFocus() {
         // set focus to my widget.  For a label, this doesn't
         // make much sense, but for more complex sets of widgets
         // you would decide which one gets the focus.
      }
   }