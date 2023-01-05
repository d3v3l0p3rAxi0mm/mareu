package app.d3v3l.mareu.views.meetings;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import app.d3v3l.mareu.R;

import static app.d3v3l.mareu.utils.DateAppUtils.displayMeetingStartDate;
import static app.d3v3l.mareu.utils.DateAppUtils.displayMeetingStartTime;

import org.greenrobot.eventbus.EventBus;

import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.CloseMeetingEvent;
import app.d3v3l.mareu.events.DeleteMeetingEvent;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.service.MaReuApiService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingDetailsFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;

    public interface OnButtonClickedListener {
        public void onButtonClicked(View view);
    }

    // UI Components
    @BindView(R.id.meetingDetails_placePhoto)
    ImageView mPlacePhoto;
    @BindView(R.id.meetingDetails_placeName)
    TextView mPlaceName;
    @BindView(R.id.meetingDetails_numberOfParticipants)
    TextView mNumberOfParticipants;
    @BindView(R.id.meetingDetails_availableSeats)
    TextView mAvailableSeats;
    @BindView(R.id.meetingDetails_startMeeting)
    TextView mStartMeeting;
    @BindView(R.id.meetingDetails_meetingDuration)
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
    @BindView(R.id.meetingDetails_meetingDelete)
    Button mDeleteMeeting;

    private Meeting mMeeting;
    private int meetingId;

    // Call apiService
    MaReuApiService mApiService = DI.getMaReuApiService();
    private static final String MEETINGID = "meetingId";

    public MeetingDetailsFragment() {
        // Required empty public constructor
    }


    public static MeetingDetailsFragment newInstance(int meetingId) {
        MeetingDetailsFragment fragment = new MeetingDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(MEETINGID, meetingId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            meetingId = getArguments().getInt(MEETINGID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meeting_details, container, false);
        //Set onClickListener to button "BACk"
        view.findViewById(R.id.back).setOnClickListener(this);
        // Bind all widgets
        ButterKnife.bind(this, view);

        mMeeting = mApiService.getMeetingById(meetingId);
        mPlacePhoto.setImageResource(mMeeting.getPlace().getPhoto());
        mPlacePhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mPlaceName.setText(mMeeting.getPlace().getName());
        String numberOfParticipants = mMeeting.getNumberOfParticipants() + " Participants";
        mNumberOfParticipants.setText(numberOfParticipants);
        String availableSeats = mMeeting.getAvailableSeats() + " seat(s) available";
        mAvailableSeats.setText(availableSeats);
        String dateOfMeeting = displayMeetingStartDate(mMeeting.getStartOfMeeting()) + " at " + displayMeetingStartTime(mMeeting.getStartOfMeeting());
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
                    // Post Event via EventBus when deleting a meeting
                    EventBus.getDefault().postSticky(new CloseMeetingEvent(mMeeting));
                    mCallback.onButtonClicked(view);
                }
            });
        }

        /*
        if (status.equals("Finished")) {
            mAddMeetingParticipant.setImageResource(R.drawable.ic_baseline_lock_24);
        } else {
            mAddMeetingParticipant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Participant p = mApiService.getParticipantById(10);
                    mApiService.addParticipantToMeeting(mMeeting, p);
                }
            });
        }
        */

        // Delete Meeting : only Meeting Creator can do this
        if (mApiService.getConnectedParticipant() == mMeeting.getMeetingCreatorParticipant()) {
            mDeleteMeeting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Post Event via EventBus when deleting a meeting
                    EventBus.getDefault().postSticky(new DeleteMeetingEvent(mMeeting));
                    mCallback.onButtonClicked(view);
                }
            });
        } else {
            // hide Delete button for non Meeting creator
            mDeleteMeeting.setVisibility(View.GONE);
        }

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Call the method that creating callback after being attached to parent activity
        this.createCallbackToParentActivity();
    }

    @Override
    public void onClick(View view) {
        mCallback.onButtonClicked(view);
    }

    // Create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            //Parent activity will automatically subscribe to callback
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }
}