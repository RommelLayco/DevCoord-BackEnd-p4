import devcoordplugin.Activator;
import devcoordplugin.handlers.SampleHandler;

public class PlugInRunner {

	public static void main(String[] args) {

SampleHandler handler= new  SampleHandler();
		
handler.setEnabled(new Activator());


		
		

	}

}
