package helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.mylyn.monitor.core.InteractionEvent;

public class InteractionEventHelper {

	public static boolean shouldReplace(InteractionEvent previous,InteractionEvent next){
		
		
			
			if (((next.getDate().getTime()*1000)-(previous.getDate().getTime())*1000)<1) {
				return false;
			}
			
		
			
			else{
				
				return true;
			}
			
		
		
		
	}
	
	public static InteractionEvent getFirstEventFromThisListWithThisDateAndTime(List<InteractionEvent> events,long time){
		return null;
		
		
		
	}
	
	
	public static List<InteractionEvent> getEventsOfTheLastTwoSeconds(List<InteractionEvent> events,long time){
		ArrayList<InteractionEvent> eventsToReturn=new ArrayList<InteractionEvent>();
		
		
		for (InteractionEvent interactionEvent : events) {
			if (time-interactionEvent.getDate().getTime()<2000) {
				eventsToReturn.add(interactionEvent);
			}
		}
		
		
		
		return eventsToReturn;
		
		
		
	}
	
	
}
