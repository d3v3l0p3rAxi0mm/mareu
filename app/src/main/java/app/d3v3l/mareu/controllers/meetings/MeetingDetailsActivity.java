package app.d3v3l.mareu.controllers.meetings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.utils.OnButtonClickedListener;

public class MeetingDetailsActivity extends AppCompatActivity implements OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        this.configureAndShowDetailFragment();
    }

    private void configureAndShowDetailFragment() {
            MeetingDetailsFragment detailsMeetingFragment = MeetingDetailsFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detailsMeeting, detailsMeetingFragment)
                    .commit();
    }

    @Override
    public void onButtonClicked(View view) {
        MeetingDetailsActivity.this.finish();
    }
}