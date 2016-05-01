package helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.mylyn.context.core.IInteractionContext;
import org.eclipse.mylyn.context.core.IInteractionContextManager;
import org.eclipse.mylyn.monitor.core.InteractionEvent;
import org.eclipse.mylyn.monitor.core.InteractionEvent.Kind;

/**
 * Contains helper methods dealing with interaction events
 * */
public class InteractionEventHelper implements Comparator<InteractionEvent>{

	/**
	 * Takes in an event as an argument,and returns another event that is the initial event,which triggered the other events.
	 * 
	 * */
	public static InteractionEvent getInitialEvent(InteractionEvent childEvent){
		IInteractionContextManager iContextManager=org.eclipse.mylyn.context.core.ContextCore.getContextManager();
		IInteractionContext  iContext =iContextManager.getActiveContext();
		List<InteractionEvent> eventsBeforeStrip=iContext.getInteractionHistory();
		if(eventsBeforeStrip.size()>0){
			List<InteractionEvent> eventsAfterStrip=InteractionEventHelper.getEventsOfTheLastOneSeconds(eventsBeforeStrip, childEvent.getDate().getTime());
			if (eventsAfterStrip.size()>0) {
				InteractionEvent toReturn=InteractionEventHelper.getFirstEventOfList(eventsAfterStrip);
				return toReturn;
			}
		}
		return null;
	}

/**
 * Creates a TaskWrapper object using the InteractioEvent given.
 * Takes in an event,and a TaskWrapper object from the intial event that caused the other event.
 * */
	public static TaskWrapper getTaskWrapperObject(InteractionEvent event){
		InteractionEvent initialEvent= getInitialEvent(event);
		TaskWrapper toRetrun;
		if (initialEvent==null) {
			toRetrun=TaskWrapper.getTaskWrapper(event);
		} else {
			toRetrun=TaskWrapper.getTaskWrapper(initialEvent);
		}
		return toRetrun;
	}

/**
 * Returns a list of InteractionEvents that contain the events that have occured within
 *  two seconds difference of the InteractionEvent provided.
 * */
	public static List<InteractionEvent> getEventsOfTheLastTwoSeconds(List<InteractionEvent> events,long time){
		ArrayList<InteractionEvent> eventsToReturn=new ArrayList<InteractionEvent>();
		for (InteractionEvent interactionEvent : events) {
			if (time-interactionEvent.getDate().getTime()<2000) {
				eventsToReturn.add(interactionEvent);
			}
		}
		return eventsToReturn;
	}

	/**
	 * Returns a list of InteractionEvents that contain the events that have occured within
	 *  one second difference of the InteractionEvent provided.
	 * */
	public static List<InteractionEvent> getEventsOfTheLastOneSeconds(List<InteractionEvent> events,long time){
		ArrayList<InteractionEvent> eventsToReturn=new ArrayList<InteractionEvent>();
		for (InteractionEvent interactionEvent : events) {
			if (time-interactionEvent.getDate().getTime()<1000) {
				eventsToReturn.add(interactionEvent);
			}
		}
		return eventsToReturn;
	}
	
	/**
	 * Takes in an unsorted list of InteractionEvents and returns the first event from the sorted list.
	 * */
	public static InteractionEvent getFirstEventOfList(List<InteractionEvent> events) {
		Collections.sort(events, new InteractionEventHelper() );
		return events.get(0);

	}

/**
 * Compares two InteractionEvents
 * 
 * Selection kind is given precedence over Edit kind,which is given precedence over Propagation kind
 * If kinds are same then earliest occurring event is given precedence(measured in milli seconds).
 * 
 * 
 * */
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
			} 
			else{
				return -1;
			}
		}
	}
}
