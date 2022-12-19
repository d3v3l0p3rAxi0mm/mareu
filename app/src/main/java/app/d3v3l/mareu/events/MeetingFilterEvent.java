package app.d3v3l.mareu.events;

import app.d3v3l.mareu.model.MeetingFilter;

public class MeetingFilterEvent {

    /**
     * Filters to apply
     */
    public MeetingFilter filters;

    /**
     * Constructor.
     * @param filters
     */
    public MeetingFilterEvent(MeetingFilter filters) {
        this.filters = filters;
    }

}
