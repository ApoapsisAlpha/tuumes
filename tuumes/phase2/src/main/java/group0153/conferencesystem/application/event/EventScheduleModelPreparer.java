package group0153.conferencesystem.application.event;

import group0153.conferencesystem.adapters.gateways.event.EventModel;
import group0153.conferencesystem.entities.event.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EventScheduleModelPreparer {
    EventPersistencePort eventPersistencePort;

    EventScheduleModelPreparer(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    ArrayList<EventModel> getScheduleModelByDay(Date date) {
        Calendar.Builder calendar =
        ArrayList<Event> res = eventPersistencePort.getAllEvents();
        for (Event event : res) {
            if (event.)
        }
    }
}
