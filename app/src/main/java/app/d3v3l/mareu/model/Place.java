package app.d3v3l.mareu.model;

/**
 * Model object representing a Place where take place a meeting
 */
public class Place {

    /** Identifier */
    private int id;
    /** Place common name */
    private final String name;
    /** Place Ressource ID photo */
    private final int photo;
    /** Participant Capacity of this place */
    private final int capacity;

    public Place(int id, String name, int photo, int capacity) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPhoto() {
        return photo;
    }

    public int getCapacity() {
        return capacity;
    }

}
