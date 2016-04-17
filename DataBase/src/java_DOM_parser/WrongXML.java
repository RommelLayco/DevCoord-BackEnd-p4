package java_DOM_parser;

public class WrongXML extends Exception{
	
	/**
	 * An exeception to tell developer we are reading
	 * in the wrong xml file and hence will not find the correct input.
	 * @param message
	 */
	public WrongXML(String message) {
		super(message);
	}
}
