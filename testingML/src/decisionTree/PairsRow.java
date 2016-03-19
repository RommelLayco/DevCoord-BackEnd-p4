package decisionTree;



public class PairsRow {

	int taskOne;
	int taskTwo;
	double proximity;
	int sDLMs;
	int sLSMs;
	int aLs;
	
	
	public PairsRow(String[] entry){
		
		taskOne=Integer.parseInt(entry[0]);
		taskTwo=Integer.parseInt(entry[1]);
		proximity=Double.parseDouble(entry[2]);
		sDLMs=Integer.parseInt(entry[3]);
		sLSMs=Integer.parseInt(entry[4]);
		aLs=Integer.parseInt(entry[5]);


	}
	
	public void display(){
		
		System.out.print("taskOne:"+taskOne+"  ");
		System.out.print("taskTwo:"+taskTwo+"  ");
		System.out.print("proximity:"+proximity+"  ");
		System.out.print("sDLMs:"+sDLMs+"  ");
		System.out.print("sLSMs:"+sLSMs+"  ");
		System.out.print("aLs:"+aLs+"  ");

		
		
		System.out.println();
		
		
	}
}
