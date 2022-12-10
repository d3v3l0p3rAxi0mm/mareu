package app.d3v3l.mareu.model;

import android.graphics.drawable.Drawable;

/**
 * Model object representing a Place where take place a meeting
 */

public class Place {

    /** Identifier */
    private int id;
    /** Place common name */
    private String name;
    /** Place photo */
    private int photo;
    /** Participant Capacity of this place */
    private int capacity;

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

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
