package app.d3v3l.mareu.controllers.meetings;

import static app.d3v3l.mareu.controllers.utils.DateAppUtils.displayMeetingStartDate;
import static app.d3v3l.mareu.controllers.utils.DateAppUtils.displayMeetingStartTime;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.databinding.FragmentMeetingBinding;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.DeleteMeetingEvent;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.service.MaReuApiService;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

    public MeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @NonNull
    @Override
    public MeetingRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FragmentMeetingBinding fragmentMeetingBinding = FragmentMeetingBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(fragmentMeetingBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.bindView(meeting);
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentMeetingBinding fragmentMeetingBinding;
        private final MaReuApiService mApiService = DI.getMaReuApiService();

        public ViewHolder(FragmentMeetingBinding fragmentMeetingBinding) {
            super(fragmentMeetingBinding.getRoot());
            this.fragmentMeetingBinding = fragmentMeetingBinding;
        }

        public void bindView(Meeting meeting) {

            fragmentMeetingBinding.meetingFragmentPlace.setText(meeting.getPlace().getName());
            fragmentMeetingBinding.meetingFragmentSubjectTitle.setText(meeting.getTitle());
            fragmentMeetingBinding.meetingFragmentPlacePhoto.setImageResource(meeting.getPlace().getPhoto());
            String dateOfMeeting = displayMeetingStartDate(meeting.getStartOfMeeting());
            fragmentMeetingBinding.meetingFragmentStartMeeting.setText(dateOfMeeting);
            String HourOfMeeting = displayMeetingStartTime(meeting.getStartOfMeeting());
            fragmentMeetingBinding.meetingFragmentTime.setText(HourOfMeeting);
            fragmentMeetingBinding.meetingFragmentEmails.setText(meeting.getListOfParticipants());

            // How Display the Delete Button ? active or not
            if (meeting.getMeetingCreatorParticipant().equals(mApiService.getConnectedParticipant())) {
                fragmentMeetingBinding.meetingFragmentDelete.setImageResource(R.drawable.ic_baseline_delete_24);
                fragmentMeetingBinding.meetingFragmentDelete.setOnClickListener(view ->
                        EventBus.getDefault().postSticky(new DeleteMeetingEvent(meeting)));
            } else {
                fragmentMeetingBinding.meetingFragmentDelete.setOnClickListener(view ->
                        Toast.makeText(view.getContext(), R.string.cant_delete, Toast.LENGTH_SHORT).show());
            }

            // Detect the orientation of device
            if (fragmentMeetingBinding.meetingFragmentPlace.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // When click on a meeting, display fragment of Meeting
                fragmentMeetingBinding.meetingFragmentMainLayout.setOnClickListener(view -> {
                    mApiService.setSelectedMeeting(meeting);
                    FragmentManager manager = ((AppCompatActivity) fragmentMeetingBinding.meetingFragmentMainLayout.getContext()).getSupportFragmentManager();
                    manager
                            .beginTransaction()
                            .replace(R.id.container_details, MeetingDetailsFragment.newInstance())
                            .commit();
                });
            } else {
                fragmentMeetingBinding.meetingFragmentMainLayout.setOnClickListener(view -> {
                    Intent intent = new Intent(fragmentMeetingBinding.meetingFragmentMainLayout.getContext(), MeetingDetailsActivity.class);
                    mApiService.setSelectedMeeting(meeting);
                    fragmentMeetingBinding.meetingFragmentMainLayout.getContext().startActivity(intent);
                });
            }
        }
    }


}
