package app.d3v3l.mareu.model;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Model object representing a Meeting
 */

public class Meeting {

    /** Id of Meeting */
    private int ID;
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
    private String title;
    /** Subject of meeting */
    private String subject;

    public Meeting(int ID, Place place, Participant meetingCreatorParticipant, List<Participant> participants, GregorianCalendar startOfMeeting, GregorianCalendar endOfMeeting, String title, String subject) {
        this.ID = ID;
        this.place = place;
        this.meetingCreatorParticipant = meetingCreatorParticipant;
        this.participants = participants;
        this.startOfMeeting = startOfMeeting;
        this.endOfMeeting = endOfMeeting;
        this.title = title;
        this.subject = subject;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // *********************************
    // Advanced Methods ****************
    // *********************************

    public long getMeetingDuration() {
        long start = startOfMeeting.getTime().getTime();
        long end = endOfMeeting.getTime().getTime();
        long duration = (end - start)/60000;
        return duration;
    }

    public int getNumberOfParticipants() {
        return participants.size();
    }

    public int getAvailableSeats() {
        return place.getCapacity() - getNumberOfParticipants();
    }

    public String getMeetingStatus() {
        String meetingStatus;
        GregorianCalendar now = new GregorianCalendar();
        now.setTimeZone(TimeZone.getTimeZone("UTC"));
        long nowTimeStamp = now.getTime().getTime();
        long start = startOfMeeting.getTime().getTime();
        long end = endOfMeeting.getTime().getTime();
        if (nowTimeStamp >= start && nowTimeStamp <= end) {
            meetingStatus = "In Progress";
        } else if (nowTimeStamp >= end) {
            meetingStatus = "Finished";
        } else {
            meetingStatus = "Not started";
        }
        return meetingStatus;
    }

}
