package app.d3v3l.mareu.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;

public abstract class DummyMaReuGenerator {

    public static List<String> GENERATOR_FIRSTNAME = Arrays.asList(
            "Georges", "Marjolaine", "Xavier", "Jean", "Benoît", "Frédéric", "Anne", "Céline", "Marcel", "Marius", "Luc", "Lucas", "Etienne", "Philippe", "Marie", "Julien", "Clément", "Aude", "Blanche", "Hélène", "Pierre", "Leslie", "Myriam", "Sophie", "Kévin", "Karl", "Denis", "Tiphaine", "Larry", "John", "Mike", "Stéphane", "Stéphanie", "Audrey", "Gustave", "Héloïse", "Catherine", "Fabrice", "Philibert", "Victor", "Kléa", "Ulysse", "Eric", "Wolfgang", "Loup", "Aldred", "Astrid", "Clémence");

    public static List<String> GENERATOR_LASTNAME = Arrays.asList(
            "DUMONT", "CLAVERY", "ALDEBERT", "MICHON", "BROCHARD", "MANCE", "DUCLERC", "MARIN", "KLIMT", "CLIBARD","MINET","DUPONT","DURAND","DAUDET","GRAMMS","HERBERT","CHALAMOND","GRANVER","JAMMIN","DORRE");

    public static String generateFirstName() {
        Random rand = new Random();
        return GENERATOR_FIRSTNAME.get(rand.nextInt(GENERATOR_FIRSTNAME.size()));
    }
    public static String generateLastName() {
        Random rand = new Random();
        return GENERATOR_LASTNAME.get(rand.nextInt(GENERATOR_LASTNAME.size()));
    }

    public static List<Place> DUMMY_PLACES = Arrays.asList(
            new Place(1, "Alderaan", R.drawable.alderaan, 9),
            new Place(2, "Ahch-To", R.drawable.ahchto, 12),
            new Place(3, "Exegol", R.drawable.exegol, 13),
            new Place(4, "Anoat", R.drawable.anoat, 4),
            new Place(5, "Tatooine", R.drawable.tatooine, 7),
            new Place(6, "Coruscant", R.drawable.coruscant, 32),
            new Place(7, "Naboo", R.drawable.naboo, 9),
            new Place(8, "Kamino", R.drawable.kamino, 8),
            new Place(9, "Hoth", R.drawable.hoth, 8),
            new Place(10, "Endor", R.drawable.endor, 7)
    );

    public static List<Participant> DUMMY_PARTICIPANTS = Arrays.asList(
            new Participant(1, "Alexandra","Breult", "alexandra","1234", "alexandra@lamzone.com","https://i.pravatar.cc/300?u=a042x81f4es9026704d", true),
            new Participant(2, "Francis","Jammeyrens", "francis","1234","francis.jammeyrens@ext.lamzone.com","https://i.pravatar.cc/300?u=a7w2x81fsWs9ss67013", true),
            new Participant(3, "Maxime","De Clotare", "maxime","1234","maxime@lamzone.com","https://i.pravatar.cc/300?u=a042581f4e29026704a", true),
            new Participant(4, "Laeticia","Histario", "laeticia","1234","laeticia@lamzone.com","https://i.pravatar.cc/300?u=a042581f4e29026703d", true),
            new Participant(5, "Sophie","Lukkam", "sophie","1234","sophie@lamzone.com","https://i.pravatar.cc/300?u=a042x81f4Ss99w670xd", true),
            new Participant(6, "Mathieu","Jenkrain", "mathieu","1234","mathieu@lamzone.com","https://i.pravatar.cc/300?u=a042x81f4Ss99s67018", true),
            new Participant(7, "Marlène","Plissamont", "marlene","1234","marlene@lamzone.com","https://i.pravatar.cc/300?u=a042x81fsSs99s67018", true),
            new Participant(8, "Charles","Weber", "charles","1234","charles@lamzone.com","https://i.pravatar.cc/300?u=a7w2x81fsas9ss67013", true),
            new Participant(9, "Franck","Noës", "franck","1234","franck@lamzone.com","https://i.pravatar.cc/300?u=a7w2x81fsas9qP9901%2c", true),
            new Participant(10, "Chloé","Vuilt", "chloe","1234","chloe@lamzone.com","https://i.pravatar.cc/300?u=a7w2x81fsas9qP99019", true),
            new Participant(11, "Kylie","Minoh", "kylie","1234","kylie@lamzone.com","https://i.pravatar.cc/300?u=b7w2q81fsao9qP99019", true),
            new Participant(12, "Corine","DONGUE", "","","corine@itbrain.eu","https://i.pravatar.cc/300?u=b7w2q81fDRo9qP99019", false)
    );

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(
                    1,
                    DUMMY_PLACES.get(0),
                    DUMMY_PARTICIPANTS.get(2),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(0),DUMMY_PARTICIPANTS.get(1)),
                    new GregorianCalendar(2022,11,17,9,20),
                    new GregorianCalendar(2022,11,17,15,30),
                    "Tech review",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices. Nullam sollicitudin enim ante, id pharetra metus gravida nec. Nunc at libero nec nulla pellentesque aliquet. Suspendisse potenti. Ut hendrerit sagittis porttitor. Nullam sollicitudin enim ante, id pharetra metus gravida nec. Nullam sollicitudin enim ante, id pharetra metus gravida nec."
            ),
            new Meeting(
                    2,
                    DUMMY_PLACES.get(1),
                    DUMMY_PARTICIPANTS.get(1),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(1),DUMMY_PARTICIPANTS.get(2),DUMMY_PARTICIPANTS.get(4),DUMMY_PARTICIPANTS.get(11),DUMMY_PARTICIPANTS.get(0)),
                    new GregorianCalendar(2022,11,17,15,30),
                    new GregorianCalendar(2022,11,17,19,30),
                    "Maréu RoadMap",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices. Nullam sollicitudin enim ante, id pharetra metus gravida nec. Nunc at libero nec nulla pellentesque aliquet. Suspendisse potenti. Ut hendrerit sagittis porttitor."
            ),
            new Meeting(
                    3,
                    DUMMY_PLACES.get(2),
                    DUMMY_PARTICIPANTS.get(2),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(2),DUMMY_PARTICIPANTS.get(5),DUMMY_PARTICIPANTS.get(6),DUMMY_PARTICIPANTS.get(10)),
                    new GregorianCalendar(2022,11,19,10,50),
                    new GregorianCalendar(2022,11,19,18,30),
                    "Financial review",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices."
            ),
            new Meeting(
                    4,
                    DUMMY_PLACES.get(5),
                    DUMMY_PARTICIPANTS.get(9),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(9),DUMMY_PARTICIPANTS.get(4),DUMMY_PARTICIPANTS.get(5)),
                    new GregorianCalendar(2022,11,22,13,30),
                    new GregorianCalendar(2022,11,22,20,30),
                    "Christmas meeting",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices."
            ),
            new Meeting(
                    5,
                    DUMMY_PLACES.get(4),
                    DUMMY_PARTICIPANTS.get(4),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(6),DUMMY_PARTICIPANTS.get(4),DUMMY_PARTICIPANTS.get(5)),
                    new GregorianCalendar(2022,10,29,15,0),
                    new GregorianCalendar(2022,10,29,17,0),
                    "Mareu First Meeting",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices."
            ),
            new Meeting(
                    6,
                    DUMMY_PLACES.get(5),
                    DUMMY_PARTICIPANTS.get(5),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(8),DUMMY_PARTICIPANTS.get(4),DUMMY_PARTICIPANTS.get(5)),
                    new GregorianCalendar(2023,0,28,11,0),
                    new GregorianCalendar(2023,0,28,12,0),
                    "Tech Meeting",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices."
            ),
            new Meeting(
                    7,
                    DUMMY_PLACES.get(6),
                    DUMMY_PARTICIPANTS.get(6),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(6),DUMMY_PARTICIPANTS.get(4),DUMMY_PARTICIPANTS.get(5)),
                    new GregorianCalendar(2023,0,13,15,0),
                    new GregorianCalendar(2023,0,13,17,0),
                    "Company's Goals 2023 Meeting",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices."
            ),
            new Meeting(
                    8,
                    DUMMY_PLACES.get(8),
                    DUMMY_PARTICIPANTS.get(6),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(9),DUMMY_PARTICIPANTS.get(10),DUMMY_PARTICIPANTS.get(8)),
                    new GregorianCalendar(2023,0,11,8,0),
                    new GregorianCalendar(2023,0,11,9,0),
                    "financial 2022 review",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices."
            ),
            new Meeting(
                    9,
                    DUMMY_PLACES.get(8),
                    DUMMY_PARTICIPANTS.get(8),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(0),DUMMY_PARTICIPANTS.get(4),DUMMY_PARTICIPANTS.get(8)),
                    new GregorianCalendar(2023,1,11,16,0),
                    new GregorianCalendar(2023,1,11,17,0),
                    "Communication Project",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices."
            ),
            new Meeting(
                    10,
                    DUMMY_PLACES.get(9),
                    DUMMY_PARTICIPANTS.get(9),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(9),DUMMY_PARTICIPANTS.get(8),DUMMY_PARTICIPANTS.get(10)),
                    new GregorianCalendar(2023,0,5,10,0),
                    new GregorianCalendar(2023,0,5,11,0),
                    "Drone delivery project Meeting",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices."
            ),
            new Meeting(
                    11,
                    DUMMY_PLACES.get(7),
                    DUMMY_PARTICIPANTS.get(7),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(7),DUMMY_PARTICIPANTS.get(6)),
                    new GregorianCalendar(2023,4,12,0,0),
                    new GregorianCalendar(2023,4,12,2,0),
                    "Meeting for Unit Tests",
                    "Do not remove. used for test on getMyMeetingsWithSuccess() , getFilteredMeetingsWithSuccess() "
            ),
            new Meeting(
                    12,
                    DUMMY_PLACES.get(7),
                    DUMMY_PARTICIPANTS.get(0),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(0),DUMMY_PARTICIPANTS.get(1),DUMMY_PARTICIPANTS.get(2),DUMMY_PARTICIPANTS.get(4),DUMMY_PARTICIPANTS.get(5),DUMMY_PARTICIPANTS.get(6),DUMMY_PARTICIPANTS.get(7),DUMMY_PARTICIPANTS.get(8),DUMMY_PARTICIPANTS.get(9),DUMMY_PARTICIPANTS.get(10),DUMMY_PARTICIPANTS.get(11)),
                    new GregorianCalendar(2023,4,12,2,30),
                    new GregorianCalendar(2023,4,12,4,0),
                    "Meeting for Unit Tests",
                    "Do not remove. used for test on getMyMeetingsWithSuccess() , getFilteredMeetingsWithSuccess() "
            ),
            new Meeting(
                    13,
                    DUMMY_PLACES.get(3),
                    DUMMY_PARTICIPANTS.get(3),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(3),DUMMY_PARTICIPANTS.get(6)),
                    new GregorianCalendar(2023,5,12,0,0),
                    new GregorianCalendar(2023,5,12,2,0),
                    "Meeting for Unit Tests",
                    "Do not remove. used for test on getMyMeetingsWithSuccess() , getFilteredMyMeetingsWithSuccess() "
            ),
            new Meeting(
                    14,
                    DUMMY_PLACES.get(3),
                    DUMMY_PARTICIPANTS.get(3),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(3),DUMMY_PARTICIPANTS.get(6)),
                    new GregorianCalendar(2022,5,12,2,30),
                    new GregorianCalendar(2022,5,12,4,0),
                    "Meeting for Unit Tests",
                    "Do not remove. used for test on getMyMeetingsWithSuccess() , getFilteredMyMeetingsWithSuccess() "
            )
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static List<Participant> generateParticipants() {
        return new ArrayList<>(DUMMY_PARTICIPANTS);
    }

}
