package app.d3v3l.mareu.service;

import java.util.List;

import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.MeetingFilter;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;

public interface MaReuApiService {

    /**
     * @return the participant who is connected
     */
    Participant getConnectedParticipant();

    /**
     * Keep in memory the connected User
     * @param participant the participant who is connected
     */
    void setConnectedParticipant(Participant participant);

    /**
     * @return An ordered list of Meetings
     */
    List<Meeting> getMeetings();

    /**
     * @return An ordered list of the date or place filtered Meetings
     */
    List<Meeting> getFilteredMeetings(MeetingFilter event);

    /**
     * @return An ordered list of the connected participant Meetings
     */
    List<Meeting> getMyMeetings();

    /**
     * @param id ID of the meeting
     * @return the meeting Object with given ID
     */
    Meeting getMeetingById(int id);

    /**
     * @return the max Id of Meetings
     */
    int getLastMeetingId();

    /**
     * Add a meeting in the global List of Meetings
     * @param meeting meeting to add
     */
    void addMeeting(Meeting meeting);

    /**
     * Delete a meeting from the global List of Meetings
     * @param meeting meeting to delete
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Close a meeting
     * @param meeting meeting to close
     */
    void closeMeeting(Meeting meeting);

    /**
     * Add a participant to a meeting
     * @param participant Participant to add
     * @param meeting Meeting in which a participant will be added
     */
    void addParticipantToMeeting(Meeting meeting, Participant participant);

    /**
     * @return all participants of the service Participants List
     */
    List<Participant> getParticipants();

    /**
     * @param id The Id of a participant
     * @return the participant with Id given
     */
    Participant getParticipantById(int id);

    /**
     * @return the id of the last added participant
     */
    int getLastParticipantId();

    /**
     * @param participant participant to add in the Service List of Participants
     */
    void createParticipant(Participant participant);

    /**
     * @return The List of Meeting Rooms
     */
    List<Place> getPlaces();

    /**
     * @param id Id of a place
     * @return The Place Object with a given Id
     */
    Place getPlaceById(int id);

}
