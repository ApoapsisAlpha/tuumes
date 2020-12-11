package group0153.conferencesystem.entities.event.old;

import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public abstract class Event {
    private final String id;                      // id of this event.
    private String eventName;                     // name of the event.
    private String description;                   // description of the event.
    private Date startTime;                       // start time of event.
    private Date endTime;                         // end time of event.
    private String roomId;                        // room number where the event take place.
    private int userLimit;                        // maximum amount of people allowed at this event.
    private int userCount;                        // number of people currently scheduled to go to this event.
    private ArrayList<String> userIds;            // list of ids of the users registered to this event.
    private boolean isVipOnlyEvent;               // whether this event is VIP-only.

    /**
     * @param id             the id of the Event
     * @param eventName      the name of the Event
     * @param description    the description of the event
     * @param startTime      the time the Event starts
     * @param endTime        the time the Event ends
     * @param roomId         the id of the Room where the Event takes place
     * @param userLimit      the total number of Users this Event can have registered
     * @param isVipOnlyEvent a boolean whether this Event is for VIPs only
     */
    public Event(String id, String eventName, String description, Date startTime, Date endTime,
                 String roomId, int userLimit, boolean isVipOnlyEvent) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.userLimit = userLimit;
        this.userCount = 0;
        this.userIds = new ArrayList<>();
        this.isVipOnlyEvent = isVipOnlyEvent;
    }

    /**
     * set whether the event is VIP-only.
     *
     * @param bool whether this Event is to be for VIPs only
     */
    public void setIsVipOnlyEvent(boolean bool) {
        this.isVipOnlyEvent = bool;
    }

    /**
     * get the name of the event
     *
     * @return String representing this Event's name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * set the name of the event.
     *
     * @param eventName the new name of this Event
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * get the start time of the event.
     *
     * @return the start time of this Event
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * set the start time of the event.
     *
     * @param startTime the new start time of this Event
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Return the Event's type.
     *
     * @return A string representing the event type.
     * "OneSpeakerEvent"
     * "MultiSpeakerEvent"
     * "NoSpeakerEvent"
     */
    public abstract String getEventType();

    /**
     * get the end time of the event.
     *
     * @return the time the Event ends.
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * set the end time of the event.
     *
     * @param endTime the new time the Event ends.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * get the event id.
     *
     * @return the id of this Event.
     */
    public String getId() {
        return this.id;
    }

    /**
     * get the maximum number of attendees allowed of this event.
     *
     * @return the int total number of users allowed to attend this Event.
     */
    public int getUserLimit() {
        return this.userLimit;
    }

    /**
     * set the user limit of the event.
     *
     * @param userLimit the new int total number of users allowed to attend this Event.
     */
    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    /**
     * get the current number of attendees.
     *
     * @return the current number of users registered to attend this Event.
     */
    public int getUserCount() {
        return this.userCount;
    }

    /**
     * set the number of users currently registered to the event.
     *
     * @param userCount the new number of users registered to attend this Event.
     */
    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    /**
     * get a list of the userIds of this event.
     *
     * @return ArrayList containing the String ids of users attending this Event.
     */
    public ArrayList<String> getUserIds() {
        return userIds;
    }

    /**
     * set the id list of users registered to the event.
     *
     * @param userIds the new ArrayList containing the String ids of users attending this Event.
     */
    public void setUserIds(ArrayList<String> userIds) {
        this.userIds = userIds;
    }

    /**
     * get the room id of this event.
     *
     * @return the id of the room this Event is to take place in.
     */
    public String getRoomId() {
        return this.roomId;
    }

    /**
     * set the room of the event.
     *
     * @param roomId the new id of the room this Event is to take place in.
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * get the description of this event.
     *
     * @return the String description of this Event.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the description of the event.
     *
     * @param description the new String description of this Event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get whether this event is a VIP-only event.
     *
     * @return boolean representing whether only VIPs can attend this Event.
     */
    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }

    /**
     * add userId to this event.
     *
     * @param userId the id of the User registering for this Event.
     * @throws UnsuccessfulCommandException User could not be registered.
     */
    public void addUserId(String userId) throws UnsuccessfulCommandException {
        for (String otherUserId : this.getUserIds()) {
            if (userId.equals(otherUserId))
                throw new UnsuccessfulCommandException("User is already registered for this event.");
        }
        if (!this.hasSpotsLeft())
            throw new UnsuccessfulCommandException("This event's user limit has already been reached.");
        this.userIds.add(userId);
    }

    /**
     * Return whether there is enough room in this Event for another User to register.
     *
     * @return True if userCount < userLimit.
     */
    public boolean hasSpotsLeft() {
        return this.getUserCount() < this.getUserLimit();
    }

    /**
     * Unregister the specified user from this Event.
     *
     * @param userId Remove userId from this event. Nothing happens if the user was not
     *               in this event to begin with.
     */
    public void removeUserId(String userId) {
        this.userIds.remove(userId);
    }

    /**
     * increase the capacity of the event by amount.
     *
     * @param amount: the amount to increase.
     */
    public void increaseUserCount(int amount) {
        this.userCount += amount;
    }

    /**
     * decrease the capacity of the event by amount.
     *
     * @param amount: the amount to decrease.
     */
    public void decreaseUserCount(int amount) {
        this.userCount -= amount;
    }

    /**
     * @param speakerId The id of the speaker to be added.
     * @throws UnsuccessfulCommandException The speaker could not be successfully added.
     */
    public abstract void addSpeakerId(String speakerId) throws UnsuccessfulCommandException;

    /**
     * @param speakerId The id of the speaker to be removed.
     * @throws UnsuccessfulCommandException The speaker could not be removed.
     */
    public abstract void removeSpeakerId(String speakerId) throws UnsuccessfulCommandException;

    /**
     * Return the ids of the speakers that are to speak at this Event.
     *
     * @return ArrayList of String ids of Speakers set to speak at this Event.
     * @throws UnsuccessfulCommandException This Event type does not allow Speakers.
     */
    public abstract ArrayList<String> getSpeakerIds() throws UnsuccessfulCommandException;

    /**
     * A class for building and returning an event.
     * Use the build method to return an instance of the event being built.
     */
    public static class Builder {
        private String eventName;                     // name of the event
        private String description;                   // description of the event.
        private Date startTime;                       // start time of event.
        private Date endTime;                         // end time of event.
        private String roomId;                        // room number where the event take place.
        private int userLimit;                        // maximum amount of people allowed at this event.
        private boolean isVipOnlyEvent;               // whether this event is VIP-only.
        private ArrayList<String> speakerIds;         // ids of speakers to speak at this event.
        private int speakerLimit;                     // maximum number of speakers allowed at this event.
        /**
         * Set the name of the Event.
         *
         * @param eventName the String name of the Event.
         */
        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        /**
         * Set the description of the Event.
         *
         * @param description the String description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * Set the start time of the Event.
         *
         * @param startTime the Date start time of the Event.
         */
        public void setStartTime(String startTime) throws UnsuccessfulCommandException {
            this.startTime = convertToDate(startTime);
        }

        /**
         *
         * @param time The time given in the form year\month\monthDay\hour\minute. Seconds is set to 0.
         * @return The representative date object.
         */
        private Date convertToDate(String time) throws UnsuccessfulCommandException {
            ArrayList<String> parameters = new ArrayList<>();
            ArrayList<Integer> convertedParameters = new ArrayList<>();
            int l = 0;
            int r = 1;
            while (r < time.length()) {
                if (time.charAt(r) == '/') {
                    int value = Integer.parseInt(time.substring(l, r));
                    convertedParameters.add(value);
                    l = r + 1;
                    ++r;
                }
                ++r;
            }
            int value = Integer.parseInt(time.substring(l, r));
            convertedParameters.add(value);
            if (convertedParameters.size() != 5) throw new UnsuccessfulCommandException("The input does not match the required format.");
            return new Date(convertedParameters.get(0), convertedParameters.get(1), convertedParameters.get(2), convertedParameters.get(3),
                    convertedParameters.get(4));
        }

        /**
         * Set the end time of the Event.
         *
         * @param endTime the Date end time of the Event.
         */
        public void setEndTime(String endTime) throws UnsuccessfulCommandException {
            this.endTime = convertToDate(endTime);
        }

        /**
         * Set the id of the room the Event takes place in.
         *
         * @param roomId the String id of the room the Event takes place in.
         */
        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        /**
         * Set the maximum number of attendees allowed at this Event.
         *
         * @param userLimit the int capacity of this Event.
         */
        public void setUserLimit(int userLimit) {
            this.userLimit = userLimit;
        }

        /**
         * Set whether the Event is VIP-only
         *
         * @param isVipOnlyEvent boolean representing whether this Event is for VIPs only
         */
        public void setIsVipOnlyEvent(boolean isVipOnlyEvent) {
            this.isVipOnlyEvent = isVipOnlyEvent;
        }

        /**
         * Set the ArrayList of speaker ids of the Event.
         *
         * @param speakerIds ArrayList of String ids of speakers of the Event.
         */
        public void setSpeakerIds(ArrayList<String> speakerIds) {
            this.speakerIds = speakerIds;
        }

//        public void setSpeakerId(String speakerId) {
//        }

        /**
         * Set the number of speakers at this Event
         *
         * @param speakerLimit the int number of speakers to speak at this Event.
         */
        public void setSpeakerLimit(int speakerLimit) {
            this.speakerLimit = speakerLimit;
        }

        /**
         * Build the Event specified containing the information set in the setters.
         *
         * @param eventType the String type of Event (SpeakerEvent or NoSpeakerEvent)
         * @return an instance of the specified type of Event with the required information set
         */
        public Event build(String eventType) {
            if ("SpeakerEvent".equalsIgnoreCase(eventType))
                return new SpeakerEvent(UUID.randomUUID().toString(), eventName, description, startTime, endTime,
                        roomId, userLimit, isVipOnlyEvent, speakerIds, speakerLimit);
            if ("NoSpeakerEvent".equalsIgnoreCase(eventType))
                return new NoSpeakerEvent(UUID.randomUUID().toString(), eventName, description, startTime, endTime,
                        roomId, userLimit, isVipOnlyEvent);
            return null;
        }
    }
}
