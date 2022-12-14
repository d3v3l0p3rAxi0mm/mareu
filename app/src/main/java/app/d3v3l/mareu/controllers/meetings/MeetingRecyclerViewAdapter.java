package app.d3v3l.mareu.controllers.meetings;

import static app.d3v3l.mareu.utils.DateAppUtils.displayMeetingStartDate;
import static app.d3v3l.mareu.utils.DateAppUtils.displayMeetingStartTime;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.DeleteMeetingEvent;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.service.MaReuApiService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    private MeetingDetailsFragment detailsFragment;
    private final MaReuApiService mApiService = DI.getMaReuApiService();

    public MeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @NonNull
    @Override
    public MeetingRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Meeting meeting = mMeetings.get(position);
        holder.mMeetingPlace.setText(meeting.getPlace().getName());
        holder.mMeetingSubjectTitle.setText(meeting.getTitle());
        holder.mMeetingPlacePhoto.setImageResource(meeting.getPlace().getPhoto());
        String dateOfMeeting = displayMeetingStartDate(meeting.getStartOfMeeting());
        String HourOfMeeting = displayMeetingStartTime(meeting.getStartOfMeeting());
        holder.mStartMeeting.setText(dateOfMeeting);
        holder.mMeetingHour.setText(HourOfMeeting);
        holder.mMeetingEmails.setText(meeting.getListOfParticipants());

        if (meeting.getMeetingCreatorParticipant().equals(mApiService.getConnectedParticipant())) {
            holder.mDelete.setImageResource(R.drawable.ic_baseline_delete_24);
            holder.mDelete.setOnClickListener(view ->
                    EventBus.getDefault().postSticky(new DeleteMeetingEvent(meeting)));
        } else {
            holder.mDelete.setOnClickListener(view ->
                    Toast.makeText(view.getContext(), R.string.cant_delete, Toast.LENGTH_SHORT).show());
        }

        // Detect the orientation of device
        if (holder.mMeetingPlace.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // When click on a meeting, display fragment of Meeting
            holder.mMeetingLayout.setOnClickListener(view -> {
                FragmentManager manager = ((AppCompatActivity) holder.mMeetingLayout.getContext()).getSupportFragmentManager();
                detailsFragment = MeetingDetailsFragment.newInstance(meeting.getID());
                manager.beginTransaction()
                        .replace(R.id.container_details, detailsFragment)
                        .commit();
            });
        } else {
            holder.mMeetingLayout.setOnClickListener(view -> {
                Intent intent = new Intent(holder.mMeetingLayout.getContext(), MeetingDetailsActivity.class);
                intent.putExtra("meetingId", meeting.getID());
                holder.mMeetingLayout.getContext().startActivity(intent);
            });
        }

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.meetingFragment_mainLayout)
        public LinearLayout mMeetingLayout;
        @BindView(R.id.meetingFragment_place)
        public TextView mMeetingPlace;
        @BindView(R.id.meetingFragment_subjectTitle)
        public TextView mMeetingSubjectTitle;
        @BindView(R.id.meetingFragment_placePhoto)
        public ImageView mMeetingPlacePhoto;
        @BindView(R.id.meetingFragment_startMeeting)
        public TextView mStartMeeting;
        @BindView(R.id.meetingFragment_time)
        public TextView mMeetingHour;
        @BindView(R.id.meetingFragment_emails)
        public TextView mMeetingEmails;
        @BindView(R.id.meetingFragment_delete)
        public ImageView mDelete;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
