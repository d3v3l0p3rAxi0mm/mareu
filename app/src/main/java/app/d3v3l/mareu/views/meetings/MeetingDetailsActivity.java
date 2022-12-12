package app.d3v3l.mareu.views.meetings;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.service.MaReuApiService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingDetailsActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.meetingDetails_placePhoto)
    ImageView mPlacePhoto;
    @BindView(R.id.meetingDetails_placeName)
    TextView mPlaceName;
    @BindView(R.id.meetingDetails_shadowPlaceName)
    TextView mShadowPlaceName;
    @BindView(R.id.meetingDetails_numberOfParticipants)
    TextView mNumberOfParticipants;
    @BindView(R.id.meetingDetails_availableSeats)
    TextView mAvailableSeats;
    @BindView(R.id.meetingDetails_startMeeting)
    TextView mStartMeeting;
    @BindView(R.id.meetingDetails_MeetingDuration)
    TextView mMeetingDuration;
    @BindView(R.id.meetingDetails_subjectTitle)
    TextView mSubjectTitle;
    @BindView(R.id.meetingDetails_subject)
    TextView mMeetingSubject;
    @BindView(R.id.meetingDetails_status)
    TextView mMeetingStatus;
    @BindView(R.id.back)
    FloatingActionButton mBackButton;
    @BindView(R.id.meetingDetailsActivity_meetingAddParticipant)
    FloatingActionButton mAddMeetingParticipant;
    @BindView(R.id.meetingDetails_closeMeeting)
    Button mCloseMeeting;

    private Meeting mMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

        // Call apiService
        MaReuApiService mApiService = DI.getMaReuApiService();

        // Bind all widgets
        ButterKnife.bind(this);

        // Gathering informations of Meeting By its Id and put them into widgets
        Bundle extras = getIntent().getExtras();
        int meetingId = extras.getInt("meetingId");
        mMeeting = mApiService.getMeetingById(meetingId);

        mPlacePhoto.setImageResource(mMeeting.getPlace().getPhoto());
        mPlacePhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);

        mPlaceName.setText(mMeeting.getPlace().getName());
        mShadowPlaceName.setText(mMeeting.getPlace().getName());

        String numberOfParticipants = mMeeting.getNumberOfParticipants() + " Participants";
        mNumberOfParticipants.setText(numberOfParticipants);

        String availableSeats = mMeeting.getAvailableSeats() + " seat(s) available";
        mAvailableSeats.setText(availableSeats);


        String year = String.valueOf(mMeeting.getStartOfMeeting().get(Calendar.YEAR));
        SimpleDateFormat month_date = new SimpleDateFormat("MM");
        String month = month_date.format(mMeeting.getStartOfMeeting().getTime());
        String day = String.format("%02d",mMeeting.getStartOfMeeting().get(Calendar.DAY_OF_MONTH));
        String hour = String.format("%02d",mMeeting.getStartOfMeeting().get(Calendar.HOUR_OF_DAY));
        String mn = String.format("%02d",mMeeting.getStartOfMeeting().get(Calendar.MINUTE));
        String dateOfMeeting = day + "/" + month + "/" + year + " at " + hour + ":" + mn;


        mStartMeeting.setText(dateOfMeeting);

        String meetingDuration = String.valueOf(mMeeting.getMeetingDuration()) + "'";
        mMeetingDuration.setText(meetingDuration);

        mSubjectTitle.setText(mMeeting.getTitle());

        mMeetingSubject.setText(mMeeting.getSubject());

        String status = mMeeting.getMeetingStatus();
        mMeetingStatus.setText(status);

        if (status.equals("In Progress")) {
            mCloseMeeting.setVisibility(View.VISIBLE);
            mCloseMeeting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO EventBus to Update end of meeting Date > call closeMeeting method in ApiService
                    mApiService.closeMeeting(mMeeting);
                    MeetingDetailsActivity.this.finish();
                }
            });
        }

        if (status.equals("Finished")) {
            mAddMeetingParticipant.setImageResource(R.drawable.ic_baseline_lock_24);
        } else {
            mAddMeetingParticipant.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Toast.makeText(v.getContext(), "Ajout d'un participant", Toast.LENGTH_SHORT).show();
              }
            });
        }

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MeetingDetailsActivity.this.finish();
            }
        });



    }
}