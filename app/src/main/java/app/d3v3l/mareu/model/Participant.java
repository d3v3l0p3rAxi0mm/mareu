package app.d3v3l.mareu.model;

/**
 * Model object representing a Participant of meetings
 */

public class Participant {

    /** Identifier */
    private int id;
    /** Firstname */
    private String firstName;
    /** Lastname */
    private String lastName;
    /** Login */
    private String login;
    /** Password */
    private String password;
    /** Email */
    private String email;
    /** Avatar */
    private String avatar;
    /** is Internal to the Company */
    private boolean internal;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }
}
