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
 * This class is used to to make a decision tree out of a set of train data,
 * classify the test set,create a visual representation of the train decision tree and create 
 * a text report about the test set classification.
 * Credit for tree visualising:http://stackoverflow.com/questions/9175116/visualizing-weka-classification-tree
 * */
public class MakeTree {
	
	/**
	 * Used to to make a decision tree out of a set of train data,
	 * classify the test set,create a visual representation of the train decision tree.
	 **/
	public static void make(boolean dRH,boolean unpruned){
		String trainString;
		String testString;
		if (dRH) {
			trainString=InputEnum.toString(InputEnum.PAIRS_3_2_Train_Output_DRH);
			testString=InputEnum.toString(InputEnum.PAIRS_3_2_Test_Output_DRH);
			
		} else {
			trainString=InputEnum.toString(InputEnum.PAIRS_3_2_Train_Output_NODRH);
			testString=InputEnum.toString(InputEnum.PAIRS_3_2_Test_Output_NODRH);
		}
		
		// train classifier
	     J48 cls = new J48();
	     Instances train;
	     Instances test;
	     
		try {
			train = new Instances(new BufferedReader(new FileReader(trainString)));
			train.setClassIndex(train.numAttributes() - 4);
			
			
			test = new Instances(new BufferedReader(new FileReader(testString)));
			test.setClassIndex(test.numAttributes() - 4);
			


		
			cls.setUnpruned(unpruned);
			
			
		     cls.buildClassifier(train);
		     
		     Evaluation eval = new Evaluation(train);
		     eval.evaluateModel(cls, test);

		     
		     final javax.swing.JFrame jf = 
		       new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48(C4.5) "+trainString+" Unpruned="+unpruned);
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
		createReport(cls,eval,dRH,unpruned);
		
		}
		
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		
		
	}
	/**
	 * Used to create  a text report about the test set classification.
	 *  */
	private static void createReport(J48 cls,Evaluation evaluation,boolean DRH,boolean unpruned){
		
		 Writer writer = null;
		 String output;
		 
		 if (DRH) {
			 if (unpruned) {
					output=InputEnum.outputToString(InputEnum.WEKA_DECISION_TREE_REPORT_DRH_UNPRUNED);
				
			} else {

				output=InputEnum.outputToString(InputEnum.WEKA_DECISION_TREE_REPORT_DRH_PRUNED);
			}
			
		} else {
			if (unpruned) {
				output=InputEnum.outputToString(InputEnum.WEKA_DECISION_TREE_REPORT_NODRH_UNPRUNED);
				
			} else {
				output=InputEnum.outputToString(InputEnum.WEKA_DECISION_TREE_REPORT_NODRH_PRUNED);

			}
			
		}
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(output)));
					writer.write(cls.toString()+"\n"+evaluation.toSummaryString("\nResults\n======\n", true));

					
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
