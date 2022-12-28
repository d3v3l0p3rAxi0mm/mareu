package app.d3v3l.mareu;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

import android.support.test.espresso.contrib.ViewPagerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.d3v3l.mareu.views.viewpager.ViewPagerActivity;

@RunWith(AndroidJUnit4.class)
public class ParticipantsInstrumentedTest {

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
    public void ParticipantListShouldDisplayAddParticipantButton() {
        // scroll viewPager to go to Participant List
        onView(ViewMatchers.withId(R.id.viewpager_container))
                .perform(ViewPagerActions.scrollRight(true));
        onView(withId(R.id.viewpager_addUser))
                .check(matches(isDisplayed()));
    }

    @Test
    public void ParticipantListShouldNotDisplayAddMeetingButton() {
        onView(withId(R.id.viewpager_addMeeting))
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void ParticipantListShouldNotDisplayFilterMeetingButton() {
        onView(withId(R.id.viewpager_meetingsFilter))
                .check(matches(not(isDisplayed())));
    }

}
