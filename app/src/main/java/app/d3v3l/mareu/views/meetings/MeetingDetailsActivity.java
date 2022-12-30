package app.d3v3l.mareu.views.meetings;



import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.DeleteMeetingEvent;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.service.MaReuApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

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