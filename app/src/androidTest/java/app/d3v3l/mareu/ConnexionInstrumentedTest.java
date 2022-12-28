package app.d3v3l.mareu;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.d3v3l.mareu.utils.ClickOnOneMeetingInTheList;
import app.d3v3l.mareu.views.MainActivity;
import app.d3v3l.mareu.views.viewpager.ViewPagerActivity;

@RunWith(AndroidJUnit4.class)
public class ConnexionInstrumentedTest {

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
    public void CorrectAuthentificationShouldShowMeetingListPage() {
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
        // check if list of Meeting is displayed
        onView(withId(R.id.list_meetings))
                .check(matches(isDisplayed()));
    }

    @Test
    public void BadAuthentificationShouldStayOnConnexionPage() {
        //write a wrong login
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(typeText("wrong_login"));
        //write a wrong password
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(typeText("wrong_password"));
        // Then : click on connexion
        onView(withId(R.id.mainactivity_connexion)).perform(click());
        // check if connexion page is still displayed
        onView(withId(R.id.mainactivity_appLogo))
                .check(matches(isDisplayed()));
    }

    @Test
    public void BlankLoginAndPasswordShouldStayOnConnexionPage() {
        // clear login textField
        onView(ViewMatchers.withId(R.id.mainactivity_loginField))
                .perform(clearText());
        // clear password textField
        onView(ViewMatchers.withId(R.id.mainactivity_passwordField))
                .perform(clearText());
        // Then : click on connexion
        onView(withId(R.id.mainactivity_connexion)).perform(click());
        // check if connexion page is still displayed
        onView(withId(R.id.mainactivity_appLogo))
                .check(matches(isDisplayed()));
    }



}
