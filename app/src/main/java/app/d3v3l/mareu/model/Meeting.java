package app.d3v3l.mareu.model;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Model object representing a Meeting
 */
public class Meeting {

    /** Id of Meeting */
    private final int ID;
    /** Place where takes place this meeting */
    private Place place;
    /** List of participant */
    private final Participant meetingCreatorParticipant;
    /** List of participant */
    private List<Participant> participants;
    /** Date of meeting beginning */
    private final GregorianCalendar startOfMeeting;
    /** Date of meeting closure */
    private GregorianCalendar endOfMeeting;
    /** Subject of meeting */
    private final String title;
    /** Subject of meeting */
    private final String subject;

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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Participant getMeetingCreatorParticipant() {
        return meetingCreatorParticipant;
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

    public GregorianCalendar getEndOfMeeting() {
        return endOfMeeting;
    }

    public void setEndOfMeeting(GregorianCalendar endOfMeeting) {
        this.endOfMeeting = endOfMeeting;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }


    // *********************************
    // Advanced Methods ****************
    // *********************************

    /*
     * @return the duration of this Meeting based of dates of beginning and finish
     */
    public long getMeetingDuration() {
        long start = startOfMeeting.getTime().getTime();
        long end = endOfMeeting.getTime().getTime();
        return ((end - start)/60000);
    }

    /*
     * @return the number of Participants of this meeting
     */
    public int getNumberOfParticipants() {
        return participants.size();
    }

    /*
     * @return the number of available seats for new Participants
     */
    public int getAvailableSeats() {
        return place.getCapacity() - getNumberOfParticipants();
    }

    /*
     * @return the status of this Meeting (Not started, In progress, Finished)
     */
    public String getMeetingStatus() {
        String meetingStatus;
        GregorianCalendar now = new GregorianCalendar();
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

    /*
     * @return the list of Participants as a string
     */
    public String getListOfParticipants() {
        List<Participant> participants = getParticipants();
        StringBuilder participantsList = new StringBuilder();
        int i = 0;
        for (Participant p: participants) {
            participantsList.append(p.getFirstName()).append(" ").append(p.getLastName());
            if (i < participants.size() - 1 ) {
                participantsList.append(" ● ");
            }
            i++;
        }
        return participantsList.toString();
    }
}
