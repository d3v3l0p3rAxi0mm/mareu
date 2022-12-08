package app.d3v3l.mareu.service;

import java.util.List;

import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;

public interface MaReuApiService {

    /**
     * Get Participant who is connected
     * @return {@link Participant}
     */
    Participant getConnectedParticipant();

    /**
     * Get all meetings from now
     * @return {@link List}
     */
    List<Meeting> getMeetings();

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
     * Get all participants
     * @return {@link List}
     */
    List<Participant> getParticipants();


    /**
     * Get all places
     * @return {@link List}
     */
    List<Place> getPlaces();


}
