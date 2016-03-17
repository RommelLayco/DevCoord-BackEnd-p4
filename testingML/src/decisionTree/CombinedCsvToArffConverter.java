package decisionTree;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import decisionTree.DescriptionEnum.description;
 import decisionTree.DescriptionEnum;
/**
 * reference:http://mypetprojects.blogspot.co.nz/2010/05/decision-tree-with-weka-part-i.html
 * */
public class CombinedCsvToArffConverter{

	/**
	 * Using only 3.1 release data.
	 * */
  public static void convert(String inputFilepath) {

	  CombinedFileObject fo=new CombinedFileObject();
	  
	  


Writer writer = null;

try {
	String descriptionOptions="{"
			+DescriptionEnum.getValueOf(description.NO)+","
			+DescriptionEnum.getValueOf(description.NOT)+","
			+DescriptionEnum.getValueOf(description.SOME)+","
			+DescriptionEnum.getValueOf(description.SOMEWHAT)+","
			+DescriptionEnum.getValueOf(description.SIGNIFICANT)+","
			+DescriptionEnum.getValueOf(description.POSSIBLE)+","
			
			+DescriptionEnum.getValueOf(description.VERY)+","
			+DescriptionEnum.getValueOf(description.DEFINITELY)
	
					+ "}";
	
    writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream("input/combined.arff"), "utf-8"));
    writer.write("@relation tasks"+"\n"+"\n"+"\n");
    
    writer.write("@attribute id numeric"+"\n");
//    writer.write("@attribute release{3.1,3.2}"+"\n");
    writer.write("@attribute taskOne numeric"+"\n");
    writer.write("@attribute taskTwo numeric"+"\n");
    
    writer.write("@attribute coderOneDiscussionScore"+descriptionOptions+"\n");
    
    writer.write("@attribute coderTwoDiscussionScore"+descriptionOptions+"\n");
    writer.write("@attribute coderOneConflictScore"+descriptionOptions+"\n");
    writer.write("@attribute coderTwoConflictScore"+descriptionOptions+"\n");
    writer.write("\n");

    writer.write("@data"+"\n");
//    writer.write("1,3.2,276383,280540,NOT,NOT,NOT,NOT");
    
    int countOfBadData=0;
    ArrayList<Integer> idsOfBadData=new ArrayList<Integer>();
    for (CombinedRow entry : fo.entries) {
if (entry.release.equals("3.1")) {
	try{
    	
	      //  writer.write(entry.id+","+entry.release+","+entry.taskOne+","
	    		  writer.write(entry.id+","+entry.taskOne+","
	    			       
	    		  + entry.taskTwo+","+DescriptionEnum.getValueOf(entry.coderOneDiscussionScore)+","
	        		+DescriptionEnum.getValueOf(entry.coderTwoDiscussionScore)+","
	        				+DescriptionEnum.getValueOf(entry.coderOneConflictScore)+","+DescriptionEnum.getValueOf(entry.coderTwoConflictScore)+"\n");
	    	}
	    	catch(NullPointerException n){
	    		
	    		countOfBadData+=1;
	    		idsOfBadData.add(entry.id);
	    		
	    		
	    		
	    		
	    	}
	        
}
		
	}
    System.out.println("number of bad entries:"+countOfBadData);
System.out.println("Thier IDs are");

    for (Integer integer : idsOfBadData) {
System.out.println(integer);
	}
    
} catch (IOException ex) {
  // report
} finally {
   try {writer.close();} catch (Exception ex) {/*ignore*/}
}




  }
}