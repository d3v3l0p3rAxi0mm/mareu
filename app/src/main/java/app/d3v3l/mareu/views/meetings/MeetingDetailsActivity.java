package app.d3v3l.mareu.views.meetings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.d3v3l.mareu.R;

public class MeetingDetailsActivity extends AppCompatActivity implements MeetingDetailsFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        // Gathering informations of Meeting By its Id and put them into widgets
        Bundle extras = getIntent().getExtras();
        int meetingId = extras.getInt("meetingId");
        this.configureAndShowDetailFragment(meetingId);
    }

    private void configureAndShowDetailFragment(int meetingId) {
            MeetingDetailsFragment detailsMeetingFragment = MeetingDetailsFragment.newInstance(meetingId);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detailsMeeting, detailsMeetingFragment)
                    .commit();
    }

    @Override
    public void onButtonClicked(View view) {
        MeetingDetailsActivity.this.finish();
    }
}