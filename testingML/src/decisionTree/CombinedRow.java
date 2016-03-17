package decisionTree;

import decisionTree.DescriptionEnum.description;

public class CombinedRow {
	int id;
	String release;
	int taskOne;
	int taskTwo;
	description coderOneDiscussionScore;
	description coderTwoDiscussionScore;
	description coderOneConflictScore;
	description coderTwoConflictScore;
	
	
	public CombinedRow(String[] entry){
		id=Integer.parseInt(entry[0]);
		release=entry[1];
		taskOne=Integer.parseInt(entry[2]);
		taskTwo=Integer.parseInt(entry[3]);
		coderOneDiscussionScore=DescriptionEnum.parse(entry[6]);
		coderTwoDiscussionScore=DescriptionEnum.parse(entry[7]);
		coderOneConflictScore=DescriptionEnum.parse(entry[8]);
		coderTwoConflictScore=DescriptionEnum.parse(entry[9]);
			
		
		
	}
	
	public void display(){
		
		System.out.print("ID:"+id+"  ");
		System.out.print("RELEASE:"+release+"  ");
		System.out.print("TASKONE:"+taskOne+"  ");
		System.out.print("TASKTWO:"+taskTwo+"  ");
		System.out.print("coderOneDiscussionScore:"+coderOneDiscussionScore+"  ");
		System.out.print("coderTwoDiscussionScore:"+coderTwoDiscussionScore+"  ");
		System.out.print("coderOneConflictScore:"+coderOneConflictScore+"  ");
		System.out.print("coderTwoConflictScore:"+coderTwoConflictScore+"  ");
		
		
		System.out.println();
		
		
	}

}
