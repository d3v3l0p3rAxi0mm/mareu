package app.d3v3l.mareu.model;

import java.util.GregorianCalendar;

public class MeetingFilter {

    /** Connected Participant */
    private final boolean onlyConnectedParticipantMeetings;
    /** Meeting Room */
    private Place place;
    /** Date, time of Meeting */
    private GregorianCalendar date;

    public MeetingFilter(boolean onlyConnectedParticipantMeetings, Place place, GregorianCalendar date) {
        this.onlyConnectedParticipantMeetings = onlyConnectedParticipantMeetings;
        this.place = place;
        this.date = date;
    }

    public boolean isOnlyConnectedParticipantMeetings() {
        return onlyConnectedParticipantMeetings;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

}
