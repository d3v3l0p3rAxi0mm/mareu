package app.d3v3l.mareu.events;

import app.d3v3l.mareu.model.Meeting;

public class AddMeetingEvent {
    public Meeting mAddMeeting;

    /**
     * Constructor.
     * @param meeting
     */
    public AddMeetingEvent(Meeting meeting) {
        this.mAddMeeting = meeting;
    }

}


