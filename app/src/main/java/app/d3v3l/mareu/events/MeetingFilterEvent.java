package app.d3v3l.mareu.events;

import app.d3v3l.mareu.model.MeetingFilter;

public class MeetingFilterEvent {

    /**
     * Filters to apply
     */
    public MeetingFilter mMeetingFilters;

    /**
     * Constructor.
     * @param filters MeetingFilter Object sent via EventBus
     */
    public MeetingFilterEvent(MeetingFilter filters) {
        this.mMeetingFilters = filters;
    }


}
