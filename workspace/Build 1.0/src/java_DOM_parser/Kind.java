package java_DOM_parser;

public enum Kind {
	SELECTION, EDIT;
	
	/**
	 * Need to implement as the input from the xml file 
	 * is lower case.
	 * @param text
	 * @return
	 */
	public static Kind fromString(String text) {
	    if (text != null) {
	      for (Kind k : Kind.values()) {
	        if (text.equalsIgnoreCase(k.toString())) {
	          return k;
	        } 
	      }
	    }
	    return null;
	  }
	
	

}
