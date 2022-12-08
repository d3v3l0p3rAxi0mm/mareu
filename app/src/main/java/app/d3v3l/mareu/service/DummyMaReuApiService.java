package app.d3v3l.mareu.service;

import java.util.List;

import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;

public class DummyMaReuApiService implements MaReuApiService {

    private Participant connectedParticipant = null;
    private List<Meeting> meetings = DummyMaReuGenerator.generateMeetings();
    private List<Place> places = DummyMaReuGenerator.DUMMY_PLACES;
    private List<Participant> participants = DummyMaReuGenerator.generateParticipants();

    @Override
    public Participant getConnectedParticipant() {
        return connectedParticipant;
    }

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        if (meeting.getMeetingCreatorParticipant() == connectedParticipant) {
            meetings.remove(meeting);
        }
    }

    @Override
    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public List<Place> getPlaces() {
        return places;
    }
}
