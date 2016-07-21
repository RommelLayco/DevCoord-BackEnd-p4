package nz.ac.auckland.devcoord.machinelearningtest;

import java.io.File;

public class FileUtility {

	public static boolean fileExists(String path){
		
		
		File file = new File(path);
    	
	return	file.exists();
		
	}
	
	public static void deleteFile(String path){
	
		
		File file = new File(path);
		
		System.err.println("Working Directory of where file is being deleted = " +
	              System.getProperty("user.dir"));
		
		file.delete();
		
		
		
	}
	
	
}
