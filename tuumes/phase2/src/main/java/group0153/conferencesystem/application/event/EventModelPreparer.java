package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;

import java.util.ArrayList;
import java.util.Date;

public class EventModelPreparer {
    private final ArrayList<Event> events;

    public EventModelPreparer(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> getEventsByTime(Date startTime, Date endTime) {
        ArrayList<Event> result = new ArrayList<>();
        for (Event event : events){
            if (event.getStartTime().equals(startTime) || event.getStartTime().after(startTime)){
                if (event.getEndTime().equals(endTime) || event.getEndTime().before(endTime)){
                    result.add(event);
                }
            }
        }
        return result;
    }

//    private ArrayList<Event>
}
