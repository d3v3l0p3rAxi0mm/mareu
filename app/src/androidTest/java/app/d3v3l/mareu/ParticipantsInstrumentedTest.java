package app.d3v3l.mareu;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static java.lang.Thread.sleep;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.ViewPagerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.d3v3l.mareu.utils.RecyclerViewMatcher;
import app.d3v3l.mareu.controllers.viewpager.ViewPagerActivity;

@RunWith(AndroidJUnit4.class)
public class ParticipantsInstrumentedTest {

    @Rule
    public ActivityTestRule<ViewPagerActivity> mActivityRule =
            new ActivityTestRule(ViewPagerActivity.class);

    @Before
    public void setUp() {
        ViewPagerActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }


    @Test
    public void ParticipantListShouldDisplayAddParticipantButton() throws InterruptedException {
        // scroll viewPager to go to Participant List
        onView(ViewMatchers.withId(R.id.viewpager_container))
                .perform(ViewPagerActions.scrollRight(true));
        sleep(500);
        onView(withId(R.id.viewpager_addUser))
                .check(matches(isDisplayed()));
    }

    @Test
    public void ParticipantListShouldNotDisplayAddMeetingButton() throws InterruptedException {
        // scroll viewPager to go to Participant List
        onView(ViewMatchers.withId(R.id.viewpager_container))
                .perform(ViewPagerActions.scrollRight(true));
        sleep(500);
        onView(withId(R.id.viewpager_addMeeting))
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void ParticipantListShouldNotDisplayFilterMeetingButton() throws InterruptedException {
        // scroll viewPager to go to Participant List
        onView(ViewMatchers.withId(R.id.viewpager_container))
                .perform(ViewPagerActions.scrollRight(true));
        sleep(500);
        onView(withId(R.id.viewpager_meetingsFilter))
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void CreateParticipantShouldAddThisParticipantInList() {
        // scroll viewPager to go to Participant List
        onView(ViewMatchers.withId(R.id.viewpager_container))
                .perform(ViewPagerActions.scrollRight(true));
        // Click on Add Participant
        onView(withId(R.id.viewpager_addUser)).perform(click());
        // click on Add Meeting
        // Fill Firstname field
                onView(ViewMatchers.withId(R.id.addParticipantActivity_firstName)).perform(clearText());
        onView(ViewMatchers.withId(R.id.addParticipantActivity_firstName))
                .perform(typeText("MyFirstName"), closeSoftKeyboard());
        // Fill Lastname field
        onView(withId(R.id.addParticipantActivity_lastName))
                .perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.addParticipantActivity_lastName)).perform(clearText());
        onView(ViewMatchers.withId(R.id.addParticipantActivity_lastName))
                .perform(typeText("MyLastName"), closeSoftKeyboard());
        // Fill email field
        onView(withId(R.id.addParticipantActivity_email))
                .perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.addParticipantActivity_email)).perform(clearText());
        onView(ViewMatchers.withId(R.id.addParticipantActivity_email))
                .perform(typeText("myfirstname.mylastname@lamzone.com"), closeSoftKeyboard());
        // Fill Login field
        onView(withId(R.id.addParticipantActivity_login))
                .perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.addParticipantActivity_login)).perform(clearText());

        onView(ViewMatchers.withId(R.id.addParticipantActivity_login))
                .perform(typeText("myfirstname.mylastname"), closeSoftKeyboard());
        // Fill Password field
        onView(withId(R.id.addParticipantActivity_password))
                .perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.addParticipantActivity_password)).perform(clearText());
        onView(ViewMatchers.withId(R.id.addParticipantActivity_password))
                .perform(typeText("1234"), closeSoftKeyboard());

        //Click on Create Button
        onView(withId(R.id.addParticipantActivity_participantCreate))
                .perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.addParticipantActivity_participantCreate)).perform(click());

        /* Check now if this user is at the end of List */
        onView(withId(R.id.list_participants)).perform(scrollToPosition(12));
        onView(withRecyclerView(R.id.list_participants).atPosition(12))
                .check(matches(hasDescendant(withText("myfirstname.mylastname@lamzone.com"))));
    }

    private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }



}
