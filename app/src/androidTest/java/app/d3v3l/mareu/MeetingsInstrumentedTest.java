package app.d3v3l.mareu;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

import static java.lang.Thread.sleep;

import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.d3v3l.mareu.utils.ClickOnOneMeetingInTheList;
import app.d3v3l.mareu.views.MainActivity;

@RunWith(AndroidJUnit4.class)
public class MeetingsInstrumentedTest {

    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }


    @Test
    public void ClickOnMeetingShouldLaunchDetailsMeeting() throws InterruptedException {
        /* CONNEXION */
        // clear login textField
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(clearText());
        //write login
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(typeText("laeticia"));
        // clear password textField
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(clearText());
        //write the correct password
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(typeText("1234"));
        // Then : click on connexion
        onView(withId(R.id.mainactivity_connexion)).perform(click());
        /* END OF CONNEXION */

        // CHECK
        // Click on second meeting in the list
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new ClickOnOneMeetingInTheList()));

        // Then : display the meeting Detail
        onView(withId(R.id.frameLayoutMeetingProfile))
                .check(matches(isDisplayed()));
        sleep(2000);
    }

    @Test
    public void CreateMeetingShouldAddThisMeetingInList() {
        /* CONNEXION */
        // clear login textField
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(clearText());
        //write login
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(typeText("laeticia"));
        // clear password textField
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(clearText());
        //write the correct password
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(typeText("1234"));
        // Then : click on connexion
        onView(withId(R.id.mainactivity_connexion)).perform(click());
        /* END OF CONNEXION */

        /* CHECK */
        // click on Add Meeting
        onView(withId(R.id.viewpager_addMeeting)).perform(click());
        // clear Title field
        onView(ViewMatchers.withId(R.id.addMeetingActivity_meetingSubjectTitle)).perform(clearText());
        //write a Title field
        String titleOfMeeting = "New Meeting";
        onView(ViewMatchers.withId(R.id.addMeetingActivity_meetingSubjectTitle))
                .perform(typeText(titleOfMeeting));
        // clear Subject field
        onView(ViewMatchers.withId(R.id.addMeetingActivity_meetingSubject)).perform(clearText());
        //write a Subject field
        onView(ViewMatchers.withId(R.id.addMeetingActivity_meetingSubject))
                .perform(typeText("this is a short fake description for New Meeting used in Instrumented Tests"),
                        closeSoftKeyboard());
        // Check Alderaan Place
        onView(withText("Alderaan")).perform(click());
        onView(withId(R.id.addMeetingActivity_datePicker))
                .perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.addMeetingActivity_datePicker)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000,1,1));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.addMeetingActivity_timePicker)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14,0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.addMeetingActivity_durationPicker))
                .perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.addMeetingActivity_durationPicker))
                .perform(new GeneralSwipeAction(Swipe.FAST, GeneralLocation.BOTTOM_CENTER, GeneralLocation.TOP_CENTER, Press.FINGER));
        onView(withId(R.id.addMeetingActivity_meetingCreate))
                .perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withText("kylie@lamzone.com")).perform(click());
        onView(withText("corine@itbrain.eu")).perform(click());
        onView(withId(R.id.addMeetingActivity_meetingCreate)).perform(click());
        // after creating Meeting, click on the first element which is the new meeting freshly created
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickOnOneMeetingInTheList()));
        // display the meeting Detail
        onView(withId(R.id.meetingDetails_subjectTitle))
                .check(matches(withText(titleOfMeeting)));
        /* END OF CHECK */
    }

    @Test
    public void DeleteMeetingShouldRemoveMeetingInList() throws InterruptedException {
        /* CONNEXION */
        // clear login textField
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(clearText());
        //write login
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(typeText("maxime"));
        // clear password textField
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(clearText());
        //write the correct password
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(typeText("1234"));
        // Then : click on connexion
        onView(withId(R.id.mainactivity_connexion)).perform(click());
        /* END OF CONNEXION */

        /* CHECK */
        String titleOfMeeting = "Financial review";
        // Click on second meeting in the list
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, new ClickOnOneMeetingInTheList()));
        // check if Meeting Title is equal to titleOfMeeting value
        onView(withId(R.id.meetingDetails_subjectTitle))
                .check(matches(withText(titleOfMeeting)));
        // Check if Delete Button is displayed :: Meeting must be created by Maxime
        onView(withId(R.id.meetingDetails_meetingDelete))
                .check(matches(isDisplayed()));
        onView(withId(R.id.meetingDetails_meetingDelete)).perform(click());
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, new ClickOnOneMeetingInTheList()));
        // check if Meeting is not the same than titleOfMeeting value
        onView(withId(R.id.meetingDetails_subjectTitle))
                .check(matches(withText(not(titleOfMeeting))));
    }

    @Test
    public void MeetingListShouldDisplayAddMeetingButton() {
        /* CONNEXION */
        // clear login textField
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(clearText());
        //write login
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(typeText("laeticia"));
        // clear password textField
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(clearText());
        //write the correct password
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(typeText("1234"));
        // Then : click on connexion
        onView(withId(R.id.mainactivity_connexion)).perform(click());
        /* END OF CONNEXION */

        /* CHECK */
        onView(withId(R.id.viewpager_addMeeting))
                .check(matches(isDisplayed()));
        /* END OF CHECK */
    }

    @Test
    public void MeetingListShouldDisplayFilterMeetingButton() {
        onView(withId(R.id.viewpager_meetingsFilter))
                .check(matches(isDisplayed()));
    }

    @Test
    public void MeetingListShouldNotDisplayAddParticipantButton() {
        /* CONNEXION */
        // clear login textField
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(clearText());
        //write login
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(typeText("laeticia"));
        // clear password textField
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(clearText());
        //write the correct password
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(typeText("1234"));
        // Then : click on connexion
        onView(withId(R.id.mainactivity_connexion)).perform(click());
        /* END OF CONNEXION */

        /* CHECK */
        onView(withId(R.id.viewpager_addUser))
                .check(matches(not(isDisplayed())));
        /* END OF CHECK */
    }


    // Check if list is ordered by date ASC


}

