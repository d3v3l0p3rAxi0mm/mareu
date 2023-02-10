package app.d3v3l.mareu.controllers.meetings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static app.d3v3l.mareu.controllers.utils.DateAppUtils.displayMeetingStartDate;
import static app.d3v3l.mareu.controllers.utils.DateAppUtils.displayMeetingStartTime;

import org.greenrobot.eventbus.EventBus;

import app.d3v3l.mareu.databinding.FragmentMeetingDetailsBinding;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.CloseMeetingEvent;
import app.d3v3l.mareu.events.DeleteMeetingEvent;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.service.MaReuApiService;
import app.d3v3l.mareu.controllers.utils.OnButtonClickedListener;

public class MeetingDetailsFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;
    private Meeting mMeeting;
    private FragmentMeetingDetailsBinding binding;

    // Call apiService
    MaReuApiService mApiService = DI.getMaReuApiService();

    public MeetingDetailsFragment() {
        // Required empty public constructor
    }

    public static MeetingDetailsFragment newInstance() {
        MeetingDetailsFragment fragment = new MeetingDetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMeeting = mApiService.getSelectedMeeting();
        binding = FragmentMeetingDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.meetingDetailsPlacePhoto.setImageResource(mMeeting.getPlace().getPhoto());
        binding.meetingDetailsPlacePhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
        binding.meetingDetailsPlaceName.setText(mMeeting.getPlace().getName());
        String numberOfParticipants = mMeeting.getNumberOfParticipants() + " Participants";
        binding.meetingDetailsNumberOfParticipants.setText(numberOfParticipants);
        String availableSeats = mMeeting.getAvailableSeats() + " seat(s) available";
        binding.meetingDetailsAvailableSeats.setText(availableSeats);
        String dateOfMeeting = displayMeetingStartDate(mMeeting.getStartOfMeeting()) + " at " + displayMeetingStartTime(mMeeting.getStartOfMeeting());
        binding.meetingDetailsStartMeeting.setText(dateOfMeeting);
        String meetingDuration = mMeeting.getMeetingDuration() + "'";
        binding.meetingDetailsMeetingDuration.setText(meetingDuration);
        binding.meetingDetailsSubjectTitle.setText(mMeeting.getTitle());
        binding.meetingDetailsSubject.setText(mMeeting.getSubject());
        String status = mMeeting.getMeetingStatus();
        binding.meetingDetailsStatus.setText(status);
        binding.meetingDetailsParticipantsList.setText(mMeeting.getListOfParticipants());
        binding.back.setOnClickListener(this);

        // Display Close Button or Not && define action on button Click
        if (status.equals("In Progress")) {
            binding.meetingDetailsCloseMeeting.setVisibility(View.VISIBLE);
            binding.meetingDetailsCloseMeeting.setOnClickListener(v -> {
                // Post Event via EventBus when deleting a meeting
                EventBus.getDefault().postSticky(new CloseMeetingEvent(mMeeting));
                mCallback.onButtonClicked(view);
            });
        }

        // Delete Meeting : only Meeting Creator can do this
        if (mApiService.getConnectedParticipant() == mMeeting.getMeetingCreatorParticipant()) {
            binding.meetingDetailsMeetingDelete.setOnClickListener(v -> {
                // Post Event via EventBus when deleting a meeting
                EventBus.getDefault().postSticky(new DeleteMeetingEvent(mMeeting));
                mCallback.onButtonClicked(view);
            });
        } else {
            // hide Delete button for non Meeting creator
            binding.meetingDetailsMeetingDelete.setVisibility(View.GONE);
        }

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
            throw new ClassCastException(e + " must implement OnButtonClickedListener");
        }
    }
}