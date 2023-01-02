package app.d3v3l.mareu.views.meetings;

import static app.d3v3l.mareu.utils.DateAppUtils.displayMeetingStartDate;
import static app.d3v3l.mareu.utils.DateAppUtils.displayMeetingStartTime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.util.ErrorDialogManager;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.views.viewpager.ViewPagerActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    private MeetingDetailsFragment detailsFragment;

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
        holder.mMeetingID.setText(String.valueOf(meeting.getID()));
        holder.mMeetingPlace.setText(meeting.getPlace().getName());
        holder.mMeetingSubjectTitle.setText(meeting.getTitle());
        holder.mMeetingPlacePhoto.setImageResource(meeting.getPlace().getPhoto());
        String dateOfMeeting = displayMeetingStartDate(meeting.getStartOfMeeting());
        String HourOfMeeting = displayMeetingStartTime(meeting.getStartOfMeeting());
        holder.mStartMeeting.setText(dateOfMeeting);
        holder.mMeetingHour.setText(HourOfMeeting);
        String meetingParticipation = meeting.getParticipants().size() + "/" + meeting.getPlace().getCapacity();
        holder.mNumberOfParticipants.setText(meetingParticipation);
        String duration = String.valueOf(meeting.getMeetingDuration()) + "'";
        holder.mMeetingDuration.setText(duration);


        if (ViewPagerActivity.mDetailsContainer != null) {
            Log.d("LOG_DETAILS", "container_details is not NULL");
            // display first Meeting
            if (position == 0) {
                FragmentManager manager = ((AppCompatActivity) holder.mMeetingLayout.getContext()).getSupportFragmentManager();
                detailsFragment = MeetingDetailsFragment.newInstance(meeting.getID());
                manager.beginTransaction()
                        .add(R.id.container_details, detailsFragment)
                        .commit();
            }
            // When click on a meeting, display fragment of Meeting
            holder.mMeetingLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager = ((AppCompatActivity) holder.mMeetingLayout.getContext()).getSupportFragmentManager();
                    detailsFragment = MeetingDetailsFragment.newInstance(meeting.getID());
                    manager.beginTransaction()
                            .replace(R.id.container_details, detailsFragment)
                            .commit();
                }
            });
        } else {
            Log.d("LOGGGGG", "container_details is NULL");
            holder.mMeetingLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.mMeetingLayout.getContext(), MeetingDetailsActivity.class);
                    intent.putExtra("meetingId", meeting.getID());
                    holder.mMeetingLayout.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.meetingFragment_meetingId)
        public TextView mMeetingID;
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
        @BindView(R.id.meetingFragment_numberOfParticipants)
        public TextView mNumberOfParticipants;
        @BindView(R.id.meetingFragment_duration)
        public TextView mMeetingDuration;
        @BindView(R.id.meetingFragment_time)
        public TextView mMeetingHour;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
