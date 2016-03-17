package LIBSVM;

public enum Component {
	BUGZILLA, UI, JIRA, TASK, CORE, JAVA,
	WIKITEXT, TRAC, FRAMEWORK, DOC, XPLANNER;
	
	/**
	 * Convert string from csv into enum
	 * @param word a string
	 * @return component enum
	 */
	public static Component componentEnum(String word){
		if(word.equals("Bugzilla")){
			return BUGZILLA;
		} else if(word.equals("UI")){
			return UI;
		} else if(word.equals("Jira")){
			return JIRA;
		} else if(word.equals("Tasks")){
			return TASK;
		} else if(word.equals("Core")){
			return CORE;
		} else if(word.equals("Trac")){
			return TRAC;
		} else if(word.equals("Java")){
			return JAVA;
		} else if(word.equals("Wikitext")){
			return WIKITEXT;
		} else if(word.equals("Framework")){
			return FRAMEWORK;
		} else if(word.equals("Doc")){
			return DOC;
		} else if(word.equals("XPlanner")){
			return XPLANNER;
		} else{
			return null;
		}
				
	}
}
