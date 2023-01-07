package app.d3v3l.mareu.events;

import app.d3v3l.mareu.model.Meeting;

public class CloseMeetingEvent {
    public Meeting mCloseMeeting;

    /**
     * Constructor.
     * @param meeting Meeting Object sent via EventBus
     */
    public CloseMeetingEvent(Meeting meeting) {
        this.mCloseMeeting = meeting;
    }

}

