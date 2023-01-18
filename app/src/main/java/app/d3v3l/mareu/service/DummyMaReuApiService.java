package app.d3v3l.mareu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.MeetingFilter;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.utils.MeetingSortByDate;

public class DummyMaReuApiService implements MaReuApiService {

    private Participant connectedParticipant = null;
    private final List<Meeting> meetings = DummyMaReuGenerator.generateMeetings();
    private final List<Place> places = DummyMaReuGenerator.DUMMY_PLACES;
    private final List<Participant> participants = DummyMaReuGenerator.generateParticipants();
    private int lastMeetingId = meetings.size();
    private Meeting selectedMeeting = null;
    private Participant selectedParticipant = null;
    private Place selectedPlace = null;

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
        List<Meeting> orderMeetings = meetings;
        Collections.sort(orderMeetings, new MeetingSortByDate());
        return orderMeetings;
    }

    @Override
    public List<Meeting> getMyMeetings() {
        List<Meeting> meetings = getMeetings();
        List<Meeting> myMeetings = new ArrayList<>();
        for (Meeting meeting: meetings) {
            if (meeting.getParticipants().contains(connectedParticipant)) {
                myMeetings.add(meeting);
            }
        }
        return myMeetings;
    }

    @Override
    public List<Meeting> getFilteredMeetings(MeetingFilter filters) {

        // MeetingFilter arguments :
        // boolean onlyConnectedParticipantMeetings;
        // Place place;
        // GregorianCalendar date;

        // first filter : only meetings connected participant
        List<Meeting> meetingsToParse;
        if (filters.isOnlyConnectedParticipantMeetings()) {
            meetingsToParse = getMyMeetings();
        } else {
            meetingsToParse = getMeetings();
        }
        List<Meeting> myFilteredMeetings = new ArrayList<>();

        // if date and place not defined, return all list
        if (filters.getPlace() == null && filters.getDate() == null) {
            myFilteredMeetings = meetingsToParse;
        }
        // if place not defined, check on date
        if (filters.getPlace() == null && filters.getDate() != null) {
            long filterTimestamp = filters.getDate().getTime().getTime();
            for (Meeting meeting: meetingsToParse) {
                long timestampStartMeeting = meeting.getStartOfMeeting().getTime().getTime();
                long timestampEndMeeting = meeting.getEndOfMeeting().getTime().getTime();
                if (filterTimestamp >= timestampStartMeeting && filterTimestamp <= timestampEndMeeting) {
                    myFilteredMeetings.add(meeting);
                }
            }
        }
        // if date not defined, check on place
        if (filters.getPlace() != null && filters.getDate() == null) {
            for (Meeting meeting: meetingsToParse) {
                if (meeting.getPlace() == filters.getPlace()) {
                    myFilteredMeetings.add(meeting);
                }
            }
        }
        // if place and date are defined
        if (filters.getPlace() != null && filters.getDate() != null) {
            long filterTimestamp = filters.getDate().getTime().getTime();
            for (Meeting meeting: meetingsToParse) {
                long timestampStartMeeting = meeting.getStartOfMeeting().getTime().getTime();
                long timestampEndMeeting = meeting.getEndOfMeeting().getTime().getTime();
                if (filterTimestamp >= timestampStartMeeting && filterTimestamp <= timestampEndMeeting) {
                    if (meeting.getPlace() == filters.getPlace()) {
                        myFilteredMeetings.add(meeting);
                    }
                }
            }
        }

        return myFilteredMeetings;
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
    public int getLastMeetingId() {
        return lastMeetingId;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
        lastMeetingId++;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        if (meeting.getMeetingCreatorParticipant() == connectedParticipant) {
            meetings.remove(meeting);
        }
    }

    @Override
    public void closeMeeting(Meeting meeting) {
        int IndexOfMeeting = meetings.indexOf(meeting);
        GregorianCalendar now = new GregorianCalendar();
        now.setTimeZone(TimeZone.getTimeZone("UTC"));
        now.getTime();
        meeting.setEndOfMeeting(now);
        meetings.set(IndexOfMeeting, meeting);
    }

    @Override
    // not used
    public void addParticipantToMeeting(Meeting meeting, Participant participant) {
        List<Participant> participantsOfMeeting = new ArrayList<>(meeting.getParticipants());
        participantsOfMeeting.add(participant);
        meetings.get(meetings.indexOf(meeting)).setParticipants(participantsOfMeeting);
    }

    @Override
    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public Participant getParticipantById(int id) {
        List<Participant> participants = getParticipants();
        Participant participantReturned = null;
        for (Participant participant: participants) {
            if (participant.getId() == id) {
                participantReturned = participant;
            }
        }
        return participantReturned;
    }

    @Override
    public int getLastParticipantId() {
        return getParticipants().get(getParticipants().size()-1).getId();
    }

    @Override
    public void createParticipant(Participant participant) {
        participants.add(participant);
    }

    @Override
    public List<Place> getPlaces() {
        return places;
    }

    @Override
    public Place getPlaceById(int id) {
        List<Place> places = getPlaces();
        Place placeReturned = null;
        for (Place place: places) {
            if (place.getId() == id) {
                placeReturned = place;
            }
        }
        return placeReturned;
    }

    @Override
    public Meeting getSelectedMeeting() {
        if ( selectedMeeting == null) {
            selectedMeeting = getMeetings().get(0);
        }
        return selectedMeeting;
    }

    @Override
    public void setSelectedMeeting(Meeting meeting) {
        selectedMeeting = meeting;
    }

    @Override
    public Participant getSelectedParticipant() {
        if ( selectedParticipant == null) {
            selectedParticipant = getParticipants().get(0);
        }
        return selectedParticipant;
    }

    @Override
    public void setSelectedParticipant(Participant participant) {
        selectedParticipant = participant;
    }

    @Override
    public Place getSelectedPlace() {
        if ( selectedPlace == null) {
            selectedPlace = getPlaces().get(0);
        }
        return selectedPlace;
    }

    @Override
    public void setSelectedPlace(Place place) {
        selectedPlace = place;
    }

}
