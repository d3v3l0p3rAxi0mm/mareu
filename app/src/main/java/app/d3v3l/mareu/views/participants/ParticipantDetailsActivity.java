package app.d3v3l.mareu.views.participants;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.utils.OnButtonClickedListener;

public class ParticipantDetailsActivity extends AppCompatActivity implements OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_details);
        // Gathering informations of Participant By its Id and put them into widgets
        Bundle extras = getIntent().getExtras();
        int participantId = extras.getInt("participantId");
        this.configureAndShowDetailFragment(participantId);
    }

    private void configureAndShowDetailFragment(int participantId) {
        ParticipantDetailsFragment detailsParticipantFragment = ParticipantDetailsFragment.newInstance(participantId);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_detailsParticipant, detailsParticipantFragment)
                .commit();
    }

    @Override
    public void onButtonClicked(View view) {
        ParticipantDetailsActivity.this.finish();
    }
}