package app.d3v3l.mareu.model;

/**
 * Model object representing a Participant of meetings
 */
public class Participant {

    /** Identifier */
    private int id;
    /** Firstname */
    private final String firstName;
    /** Lastname */
    private final String lastName;
    /** Login */
    private final String login;
    /** Password */
    private final String password;
    /** Email */
    private final String email;
    /** Avatar */
    private final String avatar;
    /** is Internal to the Company */
    private final boolean internal;

    public Participant(int id, String firstName, String lastName, String login, String password, String email, String avatar, boolean internal) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.internal = internal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isInternal() {
        return internal;
    }

}
