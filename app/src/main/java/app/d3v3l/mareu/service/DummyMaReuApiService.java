package app.d3v3l.mareu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

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
    public void setConnectedParticipant(Participant participant) {
        connectedParticipant = participant;
    }

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public List<Meeting> getMyMeetings(int idParticipant) {

        List<Meeting> meetings = getMeetings();
        List<Meeting> myMeetings = new ArrayList<>();
        for (Meeting meeting: meetings) {
            if (meeting.getMeetingCreatorParticipant().getId() == idParticipant) {
                myMeetings.add(meeting);
            }
        }
        return myMeetings;
    }

    @Override
    public Meeting getMeetingById(int id) {
        List<Meeting> meetings = getMeetings();
        Meeting meetingReturned = null;
        for (Meeting meeting: meetings) {
            if (meeting.getID() == id) {
                meetingReturned = meeting;
            }
        }
        return meetingReturned;
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
    public void closeMeeting(Meeting meeting) {
        Meeting updatedMeeting = meeting;
        GregorianCalendar now = new GregorianCalendar();
        now.setTimeZone(TimeZone.getTimeZone("UTC"));
        now.getTime();
        updatedMeeting.setEndOfMeeting(now);
        meetings.set(meetings.indexOf(meeting), updatedMeeting);
    }

    @Override
    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public List<Participant> getInternalParticipants() {
        List<Participant> internals = new ArrayList<>();
        for (Participant internal: participants) {
            if (internal.isInternal()) {
                internals.add(internal);
            }
        }
        return internals;
    }

    @Override
    public List<Place> getPlaces() {
        return places;
    }
}
