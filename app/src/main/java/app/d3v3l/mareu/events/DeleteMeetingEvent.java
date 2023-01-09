package app.d3v3l.mareu.events;

import app.d3v3l.mareu.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting mDeleteMeeting;

    /**
     * Constructor.
     * @param meeting Meeting Object sent via EventBus
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.mDeleteMeeting = meeting;
    }

}
