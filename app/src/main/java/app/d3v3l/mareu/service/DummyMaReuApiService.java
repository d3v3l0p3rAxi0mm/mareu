package app.d3v3l.mareu.service;

import android.util.Log;

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
    private List<Meeting> meetings = DummyMaReuGenerator.generateMeetings();
    private List<Place> places = DummyMaReuGenerator.DUMMY_PLACES;
    private List<Participant> participants = DummyMaReuGenerator.generateParticipants();
    private int lastMeetingId = meetings.size();

    @Override
    // Unit test done
    public Participant getConnectedParticipant() {
        return connectedParticipant;
    }

    @Override
    // Unit test done
    public void setConnectedParticipant(Participant participant) {
        connectedParticipant = participant;
    }

    @Override
    // Unit test done
    public List<Meeting> getMeetings() {
        List<Meeting> orderMeetings = meetings;
        Collections.sort(orderMeetings, new MeetingSortByDate());
        return orderMeetings;
    }

    @Override
    // Unit test done
    public List<Meeting> getFilteredMeetings(MeetingFilter filters) {

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
    // Unit test done
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
    // Unit test done
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
    // Unit test done
    public int getLastMeetingId() {
        return lastMeetingId;
    }

    @Override
    // Unit test done
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
        lastMeetingId++;
    }

    @Override
    // Unit test done
    public void deleteMeeting(Meeting meeting) {
        if (meeting.getMeetingCreatorParticipant() == connectedParticipant) {
            meetings.remove(meeting);
        }
    }

    @Override
    //TODO Unit test
    public void closeMeeting(Meeting meeting) {
        Meeting updatedMeeting = meeting;
        GregorianCalendar now = new GregorianCalendar();
        now.setTimeZone(TimeZone.getTimeZone("UTC"));
        now.getTime();
        updatedMeeting.setEndOfMeeting(now);
        meetings.set(meetings.indexOf(meeting), updatedMeeting);
    }

    @Override
    // Unit test done
    public void addParticipantToMeeting(Meeting meeting, Participant participant) {
        List<Participant> participantsOfMeeting = new ArrayList<>(meeting.getParticipants());
        participantsOfMeeting.add(participant);
        meetings.get(meetings.indexOf(meeting)).setParticipants(participantsOfMeeting);
    }

    @Override
    // Unit test done
    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    // Unit test done
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
    // Unit test done
    public int getLastParticipantId() {
        return getParticipants().get(getParticipants().size()-1).getId();
    }

    @Override
    // Unit test done
    public void createParticipant(Participant participant) {
        participants.add(participant);
    }

    @Override
    //TODO Unit test
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
    // Unit test done
    public List<Place> getPlaces() {
        return places;
    }

    @Override
    // Unit test done
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
}
