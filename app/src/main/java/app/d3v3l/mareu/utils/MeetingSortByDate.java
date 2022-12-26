package app.d3v3l.mareu.utils;

import android.util.Log;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import app.d3v3l.mareu.model.Meeting;

public class MeetingSortByDate implements Comparator<Meeting> {

        @Override
        public int compare(Meeting meeting1, Meeting meeting2) {
            long timestampMeeting1 = meeting1.getStartOfMeeting().getTime().getTime();
            long timestampMeeting2 = meeting2.getStartOfMeeting().getTime().getTime();
            long diff = (timestampMeeting1 - timestampMeeting2)/1000;
            return (int)diff;
        }

        @Override
        public Comparator<Meeting> reversed() {
            return Comparator.super.reversed();
        }

}
