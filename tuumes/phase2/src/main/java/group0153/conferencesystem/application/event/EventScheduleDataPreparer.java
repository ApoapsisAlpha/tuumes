package group0153.conferencesystem.application.event;

import group0153.conferencesystem.adapters.gateways.event.EventModel;
import group0153.conferencesystem.entities.event.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EventScheduleDataPreparer {
    EventPersistencePort eventPersistencePort;

    EventScheduleDataPreparer(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    /**
     *
     * @param date Date to be checked.
     * @param lDate The earliest date in the interval.
     * @param rDate The latest date in the interval.
     * @return Returns true iff date occurs between lDate and rDate (inclusive of endpoints).
     */
    private boolean inTimeInterval(Date date, Date lDate, Date rDate) {
        return !date.before(lDate) && !date.after(rDate);
    }

    ArrayList<EventModel> getScheduleModelByInInterval(Date lDate, Date rDate) {
        ArrayList<Event> events = eventPersistencePort.getAllEvents();
        ArrayList<EventModel> res = new ArrayList<>();
        for (Event event : events) {
            if (this.inTimeInterval(event.getStartTime(), lDate, rDate) && this.inTimeInterval(event.getEndTime(), lDate, rDate)) {
                res.add(event);
            }
        }
    }
}
