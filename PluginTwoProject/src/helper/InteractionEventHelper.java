package helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.monitor.core.InteractionEvent.Kind;

public class InteractionEventHelper implements Comparator<InteractionEvent>{

	public static boolean shouldReplace(InteractionEvent previous,InteractionEvent next){
		
		
			
			if (((next.getDate().getTime()*1000)-(previous.getDate().getTime())*1000)<1) {
				return false;
			}
			
		
			
			else{
				
				return true;
			}
			
		
		
		
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
	
	public static List<InteractionEvent> getEventsOfTheLastOneSeconds(List<InteractionEvent> events,long time){
		ArrayList<InteractionEvent> eventsToReturn=new ArrayList<InteractionEvent>();
		
		
		for (InteractionEvent interactionEvent : events) {
			if (time-interactionEvent.getDate().getTime()<1000) {
				eventsToReturn.add(interactionEvent);
			}
		}
		
		
		
		return eventsToReturn;
		
		
		
	}
	public static InteractionEvent getFirstEvent(List<InteractionEvent> events) {
		
		
		
		Collections.sort(events, new InteractionEventHelper() );
		return events.get(0);

	}




	@Override
	public int compare(InteractionEvent o1, InteractionEvent o2) {

		
		

			
			if (o1.getKind().equals(Kind.SELECTION)) {
				return -1;
				
			} 
			else if(o2.getKind().equals(Kind.SELECTION)){
				
				return 1;
				
			}
			
			else if (o1.getKind().equals(Kind.EDIT)) {
				return -1;
				
			} 
			else if (o2.getKind().equals(Kind.EDIT)) {
				return 1;
				
			} 
			else {
				if (o1.getDate().compareTo(o2.getDate())<0) {
					return 1;
					
					
				} else{
					return -1;
					
					
				}
			}
			
			
		
		
		// TODO Auto-generated method stub
		//return 0;
	}
	
	
}
