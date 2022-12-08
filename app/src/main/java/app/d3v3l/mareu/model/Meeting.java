package app.d3v3l.mareu.model;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Model object representing a Meeting
 */

public class Meeting {

    /** Place where takes place this meeting */
    private Place place;
    /** List of participant */
    private Participant meetingCreatorParticipant;
    /** List of participant */
    private List<Participant> participants;
    /** Date of meeting beginning */
    private GregorianCalendar startOfMeeting;
    /** Date of meeting closure */
    private GregorianCalendar endOfMeeting;
    /** Subject of meeting */
    private String subject;

    public Meeting(Place place, Participant meetingCreatorParticipant, List<Participant> participants, GregorianCalendar startOfMeeting, GregorianCalendar endOfMeeting, String subject) {
        this.place = place;
        this.meetingCreatorParticipant = meetingCreatorParticipant;
        this.participants = participants;
        this.startOfMeeting = startOfMeeting;
        this.endOfMeeting = endOfMeeting;
        this.subject = subject;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Participant getMeetingCreatorParticipant() {
        return meetingCreatorParticipant;
    }

    public void setMeetingCreatorParticipant(Participant meetingCreatorParticipant) {
        this.meetingCreatorParticipant = meetingCreatorParticipant;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public GregorianCalendar getStartOfMeeting() {
        return startOfMeeting;
    }

    public void setStartOfMeeting(GregorianCalendar startOfMeeting) {
        this.startOfMeeting = startOfMeeting;
    }

    public GregorianCalendar getEndOfMeeting() {
        return endOfMeeting;
    }

    public void setEndOfMeeting(GregorianCalendar endOfMeeting) {
        this.endOfMeeting = endOfMeeting;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
