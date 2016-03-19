package decisionTree;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class PairsCSVtoARFFconverter {

	 public static void convert() {

		 PairsFileObject fo=new PairsFileObject();
		  
		  


		 Writer writer = null;

try {
	
	
    writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream("input/pairs.arff"), "utf-8"));
    writer.write("@relation pairsTask"+"\n"+"\n"+"\n");
    
    
    writer.write("@attribute TaskOne numeric"+"\n");
    writer.write("@attribute TaskTwo numeric"+"\n");
    

    writer.write("@attribute Proximity numeric"+"\n");
    writer.write("@attribute SDLMS numeric"+"\n");
    writer.write("@attribute SLSMS numeric"+"\n");
    writer.write("@attribute ALS numeric"+"\n");
    
    
    writer.write("\n");

    writer.write("@data"+"\n");

    
    int countOfBadData=0;
 
    for (PairsRow entry : fo.entries) {

	try{
    	
		writer.write(entry.taskOne+","+entry.taskTwo+","+entry.proximity+","
				+ +entry.sDLMs+","+entry.sLSMs+","+entry.aLs);
		writer.write("\n");
		
		}
	    	catch(NullPointerException n){
	    		
	    		countOfBadData+=1;
	    		
	    		
	    		
	    		
	    	}
	        

		
	}
    System.out.println("number of bad entries:"+countOfBadData);

    
} catch (IOException ex) {
  // report
} finally {
   try {writer.close();} catch (Exception ex) {/*ignore*/}
}

	 }
	
	
	
}
