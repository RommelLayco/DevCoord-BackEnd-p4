package decisionTree;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import weka.classifiers.*;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
/**
 * Credit:http://stackoverflow.com/questions/9175116/visualizing-weka-classification-tree
 * */
public class MakeTree {
	
	
	public static void make(boolean dRH){
		String inputString;
		
		if (dRH) {
			inputString=InputEnum.toString(InputEnum.PAIRS_3_2_Train_Output_DRH);
		} else {
			inputString=InputEnum.toString(InputEnum.PAIRS_3_2_Train_Output_NODRH);

		}
		
		// train classifier
	     J48 cls = new J48();
	     Instances data;
		try {
			data = new Instances(new BufferedReader(new FileReader(inputString)));
			 data.setClassIndex(data.numAttributes() - 1);
			
		     cls.buildClassifier(data);

		     // display classifier
		     final javax.swing.JFrame jf = 
		       new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48(C4.5)");
		     jf.setSize(500,400);
		     jf.getContentPane().setLayout(new BorderLayout());
		     TreeVisualizer tv = new TreeVisualizer(null,
		         cls.graph(),
		         new PlaceNode2());
		     jf.getContentPane().add(tv, BorderLayout.CENTER);
		     jf.addWindowListener(new java.awt.event.WindowAdapter() {
		       public void windowClosing(java.awt.event.WindowEvent e) {
		         jf.dispose();
		       }
		     });

		     jf.setVisible(true);
		createReport(cls);
		
		}
		
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		
		
	}
	
	private static void createReport(J48 cls){
		 Writer writer = null;
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(InputEnum.outputToString(InputEnum.WEKA_DECISION_TREE_REPORT))));
					writer.write(cls.toString());

					
					writer.close();
					
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

}
