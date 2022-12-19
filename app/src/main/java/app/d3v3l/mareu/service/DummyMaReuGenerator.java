package app.d3v3l.mareu.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;

public abstract class DummyMaReuGenerator {

    public static List<String> GENERATOR_FIRSTNAME = Arrays.asList(
            "Georges", "Marjolaine", "Xavier", "Jean", "Benoît", "Frédéric", "Anne", "Céline", "Marcel", "Marius", "Luc", "Lucas", "Etienne", "Philippe", "Marie", "Julien", "Clément", "Aude", "Blanche", "Hélène", "Pierre", "Leslie", "Myriam", "Sophie", "Kévin", "Karl", "Denis", "Tiphaine", "Larry", "John", "Mike", "Stéphane", "Stéphanie", "Audrey", "Gustave", "Héloïse", "Catherine", "Fabrice", "Philibert", "Victor", "Kléa", "Ulysse", "Eric", "Wolfgang", "Loup", "Aldred", "Astrid", "Clémence");

    public static List<String> GENERATOR_LASTNAME = Arrays.asList(
            "DUMONT", "CLAVERY", "ALDEBERT", "MICHON", "BROCHARD", "MANCE", "DUCLERC", "MARIN", "KLIMT", "CLIBARD");

    public static String generateFirstName() {
        Random rand = new Random();
        return GENERATOR_FIRSTNAME.get(rand.nextInt(GENERATOR_FIRSTNAME.size()));
    }
    public static String generateLastName() {
        Random rand = new Random();
        return GENERATOR_LASTNAME.get(rand.nextInt(GENERATOR_LASTNAME.size()));
    }

    public static List<Place> DUMMY_PLACES = Arrays.asList(
            new Place(1, "Alderaan room", R.drawable.alderaan, 9),
            new Place(2, "Ahch-To room", R.drawable.ahchto, 12),
            new Place(3, "Exegol room", R.drawable.exegol, 13),
            new Place(4, "Anoat room", R.drawable.anoat, 4),
            new Place(5, "Tatooine room", R.drawable.tatooine, 7),
            new Place(6, "Coruscant room", R.drawable.coruscant, 32),
            new Place(7, "Naboo room", R.drawable.naboo, 9),
            new Place(8, "Kamino room", R.drawable.kamino, 8),
            new Place(9, "Hoth room", R.drawable.hoth, 8),
            new Place(10, "Endor room", R.drawable.endor, 7)
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
                    0,
                    DUMMY_PLACES.get(3),
                    DUMMY_PARTICIPANTS.get(0),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(0),DUMMY_PARTICIPANTS.get(1)),
                    new GregorianCalendar(2022,11,17,9,20),
                    new GregorianCalendar(2022,11,17,15,30),
                    "Tech review",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices. Nullam sollicitudin enim ante, id pharetra metus gravida nec. Nunc at libero nec nulla pellentesque aliquet. Suspendisse potenti. Ut hendrerit sagittis porttitor. Nullam sollicitudin enim ante, id pharetra metus gravida nec. Nullam sollicitudin enim ante, id pharetra metus gravida nec."
            ),
            new Meeting(
                    1,
                    DUMMY_PLACES.get(7),
                    DUMMY_PARTICIPANTS.get(2),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(2),DUMMY_PARTICIPANTS.get(3)),
                    new GregorianCalendar(2022,11,17,15,30),
                    new GregorianCalendar(2022,11,17,19,30),
                    "Maréu RoadMap",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices. Nullam sollicitudin enim ante, id pharetra metus gravida nec. Nunc at libero nec nulla pellentesque aliquet. Suspendisse potenti. Ut hendrerit sagittis porttitor."
            ),
            new Meeting(
                    2,
                    DUMMY_PLACES.get(2),
                    DUMMY_PARTICIPANTS.get(4),
                    Arrays.asList(DUMMY_PARTICIPANTS.get(4),DUMMY_PARTICIPANTS.get(5)),
                    new GregorianCalendar(2022,11,19,10,50),
                    new GregorianCalendar(2022,11,19,18,30),
                    "Financial review",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque leo vitae suscipit volutpat. Quisque pretium eget dui quis volutpat. Cras consectetur iaculis sapien vitae ultrices."
            )
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static List<Participant> generateParticipants() {
        return new ArrayList<>(DUMMY_PARTICIPANTS);
    }

}
