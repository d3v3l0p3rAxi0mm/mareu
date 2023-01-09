package app.d3v3l.mareu.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import app.d3v3l.mareu.R;

public class ClickOnOneMeetingInTheList implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on a Meeting in the RecyclerView";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View clickZone = view.findViewById(R.id.meetingFragment_mainLayout);
        // Maybe check for null
        clickZone.performClick();
    }

}
