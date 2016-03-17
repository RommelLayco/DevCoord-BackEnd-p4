package LIBSVM;

/**
 * Enum used to check if task in same Operating System.
 * @author Rommel
 *
 */
public enum OS {
	ALL, WINDOWS_XP, WINDOWS_VISTA, WINDOWS_SERVER,
	WINDOWS7, LINUX, MAC, MAC_COCOA;
	
	public static OS osEnum(String word){
		if(word.equals("All")){
			return ALL;
		} else if(word.equals("Windows XP")){
			return WINDOWS_XP;
		} else if(word.equals("Windows Vista")){
			return WINDOWS_VISTA;
		} else if(word.equals("Windows Server 2003")){
			return WINDOWS_SERVER;
		} else if(word.equals("Windows 7")){
			return WINDOWS7;
		} else if(word.equals("Linux")){
			return LINUX;
		} else if(word.equals("Mac OS X")){
			return MAC;
		} else if(word.equals("Mac OS X - Cocoa")){
			return MAC_COCOA;
		} else{
			return null;
		}
				
	}
}
