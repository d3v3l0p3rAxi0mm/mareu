package app.d3v3l.mareu.events;

import app.d3v3l.mareu.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting meeting;

    /**
     * Constructor.
     * @param meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }

}
