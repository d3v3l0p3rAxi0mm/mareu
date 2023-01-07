package app.d3v3l.mareu.views.meetings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.d3v3l.mareu.R;

public class MeetingDetailsActivity extends AppCompatActivity implements MeetingDetailsFragment.OnButtonClickedListener {

    private MeetingDetailsFragment detailsMeetingFragment;
    int meetingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        // Gathering informations of Meeting By its Id and put them into widgets
        Bundle extras = getIntent().getExtras();
        meetingId = extras.getInt("meetingId");
        this.configureAndShowDetailFragment(meetingId);
    }

    // --------------
    // FRAGMENTS
    // --------------

    private void configureAndShowDetailFragment(int meetingIdByExtra) {
        detailsMeetingFragment = (MeetingDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detailsMeeting);
        if (detailsMeetingFragment == null) {
            detailsMeetingFragment = MeetingDetailsFragment.newInstance(meetingIdByExtra);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detailsMeeting, detailsMeetingFragment)
                    .commit();
        }
    }

    @Override
    public void onButtonClicked(View view) {
        MeetingDetailsActivity.this.finish();
    }
}