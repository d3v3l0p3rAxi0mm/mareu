package app.d3v3l.mareu.model;

/**
 * Model object representing a Place where take place a meeting
 */

public class Place {

    /** Identifier */
    private int id;
    /** Place common name */
    private String placeName;
    /** Participant Capacity of this place */
    private int capacity;

    public Place(int id, String placeName, int capacity) {
        this.id = id;
        this.placeName = placeName;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
