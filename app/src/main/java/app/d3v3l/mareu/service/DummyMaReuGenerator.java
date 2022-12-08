package app.d3v3l.mareu.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;

public abstract class DummyMaReuGenerator {

    public static List<Place> DUMMY_PLACES = Arrays.asList(
            new Place(1, "Salle Alderaan", 10),
            new Place(2, "Salle Ahch-To", 3),
            new Place(3, "Salle Exegol", 2),
            new Place(4, "Salle Anoat", 4),
            new Place(5, "Salle Tatooine", 8),
            new Place(6, "Salle Coruscant", 25),
            new Place(7, "Salle Naboo", 7),
            new Place(8, "Salle Kamino", 9),
            new Place(9, "Salle Hoth", 15),
            new Place(10, "Salle Endor", 6)
    );

    public static List<Participant> DUMMY_PARTICIPANTS = Arrays.asList(
            new Participant(1, "Alexandra","Breult", "alexandra","1234", "alexandra@lamzone.com","https://i.pravatar.cc/300?u=a042x81f4es9026704d"),
            new Participant(2, "Francis","Jammeyrens", "francis","1234","francis@lamzone.com","https://i.pravatar.cc/300?u=a7w2x81fsWs9ss67013"),
            new Participant(3, "Maxime","De Clotare", "maxime","1234","maxime@lamzone.com","https://i.pravatar.cc/300?u=a042581f4e29026704a"),
            new Participant(4, "Laeticia","Histario", "laeticia","1234","laeticia@lamzone.com","https://i.pravatar.cc/300?u=a042581f4e29026703d"),
            new Participant(5, "Sophie","Lukkam", "sophie","1234","sophie@lamzone.com","https://i.pravatar.cc/300?u=a042x81f4Ss99w670xd"),
            new Participant(6, "Mathieu","Jenkrain", "mathieu","1234","mathieu@lamzone.com","https://i.pravatar.cc/300?u=a042x81f4Ss99s67018"),
            new Participant(7, "Marlène","Plissamont", "marlene","1234","marlene@lamzone.com","https://i.pravatar.cc/300?u=a042x81fsSs99s67018"),
            new Participant(8, "Charles","Weber", "charles","1234","charles@lamzone.com","https://i.pravatar.cc/300?u=a7w2x81fsas9ss67013"),
            new Participant(9, "Franck","Noës", "franck","1234","franck@lamzone.com","https://i.pravatar.cc/300?u=a7w2x81fsas9qP9901%2c"),
            new Participant(10, "Chloé","Vuilt", "chloe","1234","chloe@lamzone.com","https://i.pravatar.cc/300?u=a7w2x81fsas9qP99019"),
            new Participant(11, "Kylie","Minoh", "kylie","1234","kylie@lamzone.com","https://i.pravatar.cc/300?u=b7w2q81fsao9qP99019"),
            new Participant(12, "Firstname","Lastname", "visitor","1234","visitor@lamzone.com","https://i.pravatar.cc/300?u=a042581f4e29026703d")
    );

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(
                    DUMMY_PLACES.get(4),
                    DUMMY_PARTICIPANTS.get(2),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(2),DUMMY_PARTICIPANTS.get(7),DUMMY_PARTICIPANTS.get(3)),
                    new GregorianCalendar(2023,4,12,12,30),
                    new GregorianCalendar(2023,4,12,14,30),
                    "RoadMap Maréu"
            ),
            new Meeting(
                    DUMMY_PLACES.get(1),
                    DUMMY_PARTICIPANTS.get(5),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(5),DUMMY_PARTICIPANTS.get(6),DUMMY_PARTICIPANTS.get(8)),
                    new GregorianCalendar(2023,6,3,9,0),
                    new GregorianCalendar(2023,6,3,10,0),
                    "Financial review"
            ),
            new Meeting(
                    DUMMY_PLACES.get(5),
                    DUMMY_PARTICIPANTS.get(0),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(0),DUMMY_PARTICIPANTS.get(1),DUMMY_PARTICIPANTS.get(2),DUMMY_PARTICIPANTS.get(3),DUMMY_PARTICIPANTS.get(4),DUMMY_PARTICIPANTS.get(5),DUMMY_PARTICIPANTS.get(6),DUMMY_PARTICIPANTS.get(7),DUMMY_PARTICIPANTS.get(8),DUMMY_PARTICIPANTS.get(9),DUMMY_PARTICIPANTS.get(10)),
                    new GregorianCalendar(2023,12,15,10,0),
                    new GregorianCalendar(2023,12,15,16,0),
                    "Lamzone Extra Meeting"
            )
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static List<Participant> generateParticipants() {
        return new ArrayList<>(DUMMY_PARTICIPANTS);
    }

}
