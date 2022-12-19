package app.d3v3l.mareu.service;

import java.util.List;

import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.MeetingFilter;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;

public interface MaReuApiService {

    /**
     * Get Participant who is connected
     * @return {@link Participant}
     */
    Participant getConnectedParticipant();

    /**
     * Set Participant who is connecting
     * @param {@link Participant}
     */
    void setConnectedParticipant(Participant participant);

    /**
     * Get all meetings from now order bu date ASC
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Get filtered meetings
     * @return {@link List}
     * @param {@link event}
     */
    List<Meeting> getFilteredMeetings(MeetingFilter event);

    /**
     * Get meeting by Id
     * @return {@link Meeting}
     */
    List<Meeting> getMyMeetings();

    /**
     * Get meeting by Id
     * @return {@link Meeting}
     */
    Meeting getMeetingById(int id);

    /**
     * Get Last Meeting Id of Meetings List
     * @return {@link int}
     */
    int getLastMeetingId();

    /**
     * add a new meeting
     * @param meeting
     */
    void addMeeting(Meeting meeting);

    /**
     * delete a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * close a meeting
     * @param meeting
     */
    void closeMeeting(Meeting meeting);

    /**
     * add a participant to a meeting
     * @param meeting
     * @param participant
     */
    void addParticipantToMeeting(Meeting meeting, Participant participant);



    /**
     * Get all participants
     * @return {@link List}
     */
    List<Participant> getParticipants();


    /**
     * Get a participant by his/her Id
     * @return {@link Participant}
     */
    Participant getParticipantById(int id);


    /**
     * Get Last participant Id of Participants List
     * @return {@link int}
     */
    int getLastParticipantId();

    /**
     * Create a new participant
     * @param participant
     */
    void createParticipant(Participant participant);

    /**
     * Get all Internal participants
     * @return {@link List}
     */
    List<Participant> getInternalParticipants();


    /**
     * Get all places
     * @return {@link List}
     */
    List<Place> getPlaces();

    /**
     * Get a place by its Id
     * @return {@link Place}
     */
    Place getPlaceById(int id);


}
