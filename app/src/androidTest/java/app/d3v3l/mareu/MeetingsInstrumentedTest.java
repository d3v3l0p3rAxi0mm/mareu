package app.d3v3l.mareu;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

import static java.lang.Thread.sleep;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.d3v3l.mareu.utils.ClickOnOneMeetingInTheList;
import app.d3v3l.mareu.views.viewpager.ViewPagerActivity;

@RunWith(AndroidJUnit4.class)
public class MeetingsInstrumentedTest {

    private ViewPagerActivity mActivity;

    @Rule
    public ActivityTestRule<ViewPagerActivity> mActivityRule =
            new ActivityTestRule(ViewPagerActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }


    @Test
    public void ClickOnMeetingShouldLaunchDetailsMeeting() {
        // Click on second meeting in the list
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new ClickOnOneMeetingInTheList()));
        // Then : display the meeting Detail
        onView(withId(R.id.frameLayoutNeighbourProfile))
                .check(matches(isDisplayed()));
    }

    @Test
    public void CreateMeetingShouldAddThisMeetingInList() throws InterruptedException {
        // click on Add Meeting
        onView(withId(R.id.viewpager_addMeeting)).perform(click());
        // clear Title field
        onView(ViewMatchers.withId(R.id.addMeetingActivity_meetingSubjectTitle)).perform(clearText());
        //write a Title field
        onView(ViewMatchers.withId(R.id.addMeetingActivity_meetingSubjectTitle))
                .perform(typeText("New Meeting"));
        // clear Subject field
        onView(ViewMatchers.withId(R.id.addMeetingActivity_meetingSubject)).perform(clearText());
        //write a Subject field
        onView(ViewMatchers.withId(R.id.addMeetingActivity_meetingSubject))
                .perform(typeText("this is a short fake description for New Meeting used in Instrumented Tests"),
                        closeSoftKeyboard());
        onView(withText("Alderaan")).perform(click());

        // click on Date
        //TODO scroll vers le bas
        //onView(withId(R.id.addMeetingActivity_datePicker)).perform(click());

        //TODO Finish the test
    }

    @Test
    public void MeetingListShouldDisplayAddMeetingButton() {
        onView(withId(R.id.viewpager_addMeeting))
                .check(matches(isDisplayed()));
    }

    @Test
    public void MeetingListShouldDisplayFilterMeetingButton() {
        onView(withId(R.id.viewpager_meetingsFilter))
                .check(matches(isDisplayed()));
    }

    @Test
    public void MeetingListShouldNotDisplayAddParticipantButton() {
        onView(withId(R.id.viewpager_addUser))
                .check(matches(not(isDisplayed())));
    }


    // Check if list is ordered by date ASC


}

