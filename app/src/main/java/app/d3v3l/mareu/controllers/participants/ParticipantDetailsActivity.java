package app.d3v3l.mareu.controllers.participants;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.controllers.utils.OnButtonClickedListener;

public class ParticipantDetailsActivity extends AppCompatActivity implements OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_details);
        this.configureAndShowDetailFragment();
    }

    private void configureAndShowDetailFragment() {
        ParticipantDetailsFragment detailsParticipantFragment = ParticipantDetailsFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_detailsParticipant, detailsParticipantFragment)
                .commit();
    }

    @Override
    public void onButtonClicked(View view) {
        ParticipantDetailsActivity.this.finish();
    }
}