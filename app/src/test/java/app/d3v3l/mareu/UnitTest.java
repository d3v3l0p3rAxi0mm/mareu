package app.d3v3l.mareu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.MeetingFilter;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.DummyMaReuGenerator;
import app.d3v3l.mareu.service.MaReuApiService;


public class UnitTest {

    private MaReuApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }


    /**
     * Test getConnectedParticipant() and setConnectedParticipant()
     */
    @Test
    public void getAndSetConnectedParticipantWithSucess() {
        // declare the participant
        Participant participant = DummyMaReuGenerator.DUMMY_PARTICIPANTS.get(0);
        // declare participant as connected user
        service.setConnectedParticipant(participant);
        Participant p = service.getConnectedParticipant();
        assertEquals(participant, p);
    }

    /**
     * Test getMeetings() must return DUMMY_MEETINGS
     */
    @Test
    public void getMeetingsWithSuccess() {
        // Expected List of meetings
        List<Meeting> expectedMeetings = DummyMaReuGenerator.DUMMY_MEETINGS;
        // use method getMeetings() to test
        List<Meeting> meetings = service.getMeetings();
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    /**
     * Test getMyMeetings() must return the Meeting for Unit Test in DummyMaReuApiGenerator
     */
    @Test
    public void getMyMeetingsWithSuccess() {
        // declare the participant
        Participant participant = DummyMaReuGenerator.DUMMY_PARTICIPANTS.get(7);
        // declare participant as connected user
        service.setConnectedParticipant(participant);
        // declare the Expected Meetings List
        List<Meeting> expectedMyMeetings = Arrays.asList(DummyMaReuGenerator.DUMMY_MEETINGS.get(10),DummyMaReuGenerator.DUMMY_MEETINGS.get(11));
        // use method getMyMeetings() to test
        List<Meeting> myMeetings = service.getMyMeetings();
        assertThat(myMeetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMyMeetings.toArray()));
    }

    /**
     * Test getFilteredMeetings() in different cases with My Meetings checkbox unchecked
     */
    @Test
    public void getFilteredMeetingsWithSuccess() {
        // test 1 : no filter
        MeetingFilter filters1 = new MeetingFilter(false,null,null);
        // Expected List of meetings
        List<Meeting> expectedMeetingsFilter1 = DummyMaReuGenerator.DUMMY_MEETINGS;
        // use method getMeetings() to test
        List<Meeting> meetingsFilter1 = service.getFilteredMeetings(filters1);
        assertThat(meetingsFilter1, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetingsFilter1.toArray()));

        // test 2 : filter : filter on Place only
        MeetingFilter filters2 = new MeetingFilter(false,DummyMaReuGenerator.DUMMY_PLACES.get(7),null);
        // declare the Expected Meetings List
        List<Meeting> expectedMyMeetingsFilter2 = Arrays.asList(DummyMaReuGenerator.DUMMY_MEETINGS.get(10),DummyMaReuGenerator.DUMMY_MEETINGS.get(11));
        List<Meeting> meetingsFilter2 = service.getFilteredMeetings(filters2);
        assertThat(meetingsFilter2, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMyMeetingsFilter2.toArray()));

        // test 3 : filter : filter on date & time only
        MeetingFilter filters3 = new MeetingFilter(false,null, new GregorianCalendar(2023,4,12,1,0));
        // declare the Expected Meetings List
        List<Meeting> expectedMyMeetingsFilter3 = Arrays.asList(DummyMaReuGenerator.DUMMY_MEETINGS.get(10));
        List<Meeting> meetingsFilter3 = service.getFilteredMeetings(filters3);
        assertThat(meetingsFilter3, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMyMeetingsFilter3.toArray()));

        // test 4 : filter : filter on date/time and Place
        MeetingFilter filters4 = new MeetingFilter(false,DummyMaReuGenerator.DUMMY_PLACES.get(7), new GregorianCalendar(2023,4,12,3,0));
        // declare the Expected Meetings List
        List<Meeting> expectedMyMeetingsFilter4 = Arrays.asList(DummyMaReuGenerator.DUMMY_MEETINGS.get(11));
        List<Meeting> meetingsFilter4 = service.getFilteredMeetings(filters4);
        assertThat(meetingsFilter4, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMyMeetingsFilter4.toArray()));

    }

    /**
     * Test getFilteredMeetings() in different cases with My Meetings checkbox checked
     */
    @Test
    public void getFilteredMyMeetingsWithSuccess() {

        // declare the participant
        Participant participant = DummyMaReuGenerator.DUMMY_PARTICIPANTS.get(3);
        // declare participant as connected user
        service.setConnectedParticipant(participant);

        // test 1 : no filter
        MeetingFilter filters1 = new MeetingFilter(true,null,null);
        // Expected List of meetings
        List<Meeting> expectedMeetingsFilter1 = Arrays.asList(DummyMaReuGenerator.DUMMY_MEETINGS.get(12),DummyMaReuGenerator.DUMMY_MEETINGS.get(13));
        // use method getMeetings() to test
        List<Meeting> meetingsFilter1 = service.getFilteredMeetings(filters1);
        assertThat(meetingsFilter1, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetingsFilter1.toArray()));

        // test 2 : filter : filter on Place only
        MeetingFilter filters2 = new MeetingFilter(true,DummyMaReuGenerator.DUMMY_PLACES.get(3),null);
        // declare the Expected Meetings List
        List<Meeting> expectedMyMeetingsFilter2 = Arrays.asList(DummyMaReuGenerator.DUMMY_MEETINGS.get(12),DummyMaReuGenerator.DUMMY_MEETINGS.get(13));
        List<Meeting> meetingsFilter2 = service.getFilteredMeetings(filters2);
        assertThat(meetingsFilter2, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMyMeetingsFilter2.toArray()));

        // test 3 : filter : filter on date & time only
        MeetingFilter filters3 = new MeetingFilter(true,null, new GregorianCalendar(2023,5,12,1,0));
        // declare the Expected Meetings List
        List<Meeting> expectedMyMeetingsFilter3 = Arrays.asList(DummyMaReuGenerator.DUMMY_MEETINGS.get(12));
        List<Meeting> meetingsFilter3 = service.getFilteredMeetings(filters3);
        assertThat(meetingsFilter3, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMyMeetingsFilter3.toArray()));

        // test 4 : filter : filter on date/time and Place
        MeetingFilter filters4 = new MeetingFilter(true,DummyMaReuGenerator.DUMMY_PLACES.get(3), new GregorianCalendar(2022,5,12,3,0));
        // declare the Expected Meetings List
        List<Meeting> expectedMyMeetingsFilter4 = Arrays.asList(DummyMaReuGenerator.DUMMY_MEETINGS.get(13));
        List<Meeting> meetingsFilter4 = service.getFilteredMeetings(filters4);
        assertThat(meetingsFilter4, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMyMeetingsFilter4.toArray()));

    }

    /**
     * Test getMeetingById, test on id 6
     */
    @Test
    public void getMeetingByIdWithSuccess() {
        Meeting expectedMeeting = DummyMaReuGenerator.DUMMY_MEETINGS.get(5);
        Meeting meeting = service.getMeetingById(6);
        assertEquals(meeting, expectedMeeting);
    }

    /**
     * Test getLastMeetingId, must return 14
     */
    @Test
    public void getLastMeetingIdWithSuccess() {
        int expectedMeetingID = 14;
        int LastMeetingId = service.getLastMeetingId();
        assertEquals(expectedMeetingID, LastMeetingId);
    }

    /**
     * Test addMeeting(), after adding a meeting the list of Meeting must contain 15 items
     */
    @Test
    public void addMeetingWithSuccess() {
        int expectedNumberOfMeeting = 15;
        Meeting addedMeeting = DummyMaReuGenerator.DUMMY_MEETINGS.get(0);
        service.addMeeting(addedMeeting);
        assertEquals(expectedNumberOfMeeting, service.getMeetings().size());
    }

    /**
     * Test deleteMeeting(), after deleting a meeting the list of Meeting must contain 13 items
     */
    @Test
    public void deleteMeetingWithSuccess() {
        int expectedNumberOfMeeting = 13;
        Meeting deletedMeeting = DummyMaReuGenerator.DUMMY_MEETINGS.get(0);
        // connect participant in position 2 in DUMMY LIST in order to accept Meeting deletion
        service.setConnectedParticipant(DummyMaReuGenerator.DUMMY_PARTICIPANTS.get(2));
        //delete Meeting
        service.deleteMeeting(deletedMeeting);
        assertEquals(expectedNumberOfMeeting, service.getMeetings().size());
        // try to delete meeting which don't belong to participant in position 2
        Meeting cantBeDeletedMeeting = DummyMaReuGenerator.DUMMY_MEETINGS.get(1);
        service.deleteMeeting(cantBeDeletedMeeting);
        assertEquals(expectedNumberOfMeeting, service.getMeetings().size());
    }


    //TODO Unit test for closeMeeting

    /**
     * Test addParticipantToMeeting()
     */
    @Test
    public void addParticipantToMeetingWithSuccess() {
        int expectedParticpantNumber = 3;
        Meeting meetingToAddAPaticipant = DummyMaReuGenerator.DUMMY_MEETINGS.get(0);
        service.addParticipantToMeeting(meetingToAddAPaticipant, DummyMaReuGenerator.DUMMY_PARTICIPANTS.get(8));
        int meetingParticipantNewNumber = service.getMeetings().get(service.getMeetings().indexOf(meetingToAddAPaticipant)).getParticipants().size();
        assertEquals(expectedParticpantNumber, meetingParticipantNewNumber);
    }

    /**
     * Test getParticipants()
     */
    @Test
    public void getParticipantsWithSuccess() {
        List<Participant> expectedParticpants = DummyMaReuGenerator.DUMMY_PARTICIPANTS;
        List<Participant> participants = service.getParticipants();
        assertEquals(expectedParticpants, participants);
    }

    /**
     * Test getParticipantById()
     */
    @Test
    public void getParticipantByIdWithSuccess() {
        Participant expectedParticpant = DummyMaReuGenerator.DUMMY_PARTICIPANTS.get(5);
        Participant participant = service.getParticipantById(6);
        assertEquals(expectedParticpant, participant);
    }

    /**
     * Test getLastParticipantId()
     */
    @Test
    public void getLastParticipantIdWithSuccess() {
        int expectedId = 12;
        int lastId = service.getLastParticipantId();
        assertEquals(expectedId, lastId);
    }

    /**
     * Test createParticipant()
     */
    @Test
    public void createParticipantWithSuccess() {
        int expectedParticipantNumber = 13;
        service.createParticipant(DummyMaReuGenerator.DUMMY_PARTICIPANTS.get(0));
        assertEquals(expectedParticipantNumber, service.getParticipants().size());
    }


    //TODO Test for getInternalParticipants


    /**
     * Test getPlaces()
     */
    @Test
    public void getPlacesWithSuccess() {
        List<Place> expectedPlaces = DummyMaReuGenerator.DUMMY_PLACES;
        List<Place> places = service.getPlaces();
        assertEquals(expectedPlaces, places);
    }

    /**
     * Test getPlaceById()
     */
    @Test
    public void getPlaceByIdWithSuccess() {
        Place expectedPlace = DummyMaReuGenerator.DUMMY_PLACES.get(5);
        Place place = service.getPlaceById(6);
        assertEquals(expectedPlace, place);
    }




}