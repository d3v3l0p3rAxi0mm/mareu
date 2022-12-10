package app.d3v3l.mareu.views.meetings;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.model.Meeting;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

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
        holder.mStartMeeting.setText(meeting.getStartOfMeeting().getTime().toString());
        String meetingParticipation = meeting.getParticipants().size() + "/" + meeting.getPlace().getCapacity();
        holder.mNumberOfParticipants.setText(meetingParticipation);
        String duration = String.valueOf(meeting.getMeetingDuration()) + "'";
        holder.mMeetingDuration.setText(duration);

        holder.mMeetingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMeetingClick = new Intent(holder.mMeetingLayout.getContext(), MeetingDetailsActivity.class);
                intentMeetingClick.putExtra("meetingId",meeting.getID());
                holder.mMeetingLayout.getContext().startActivity(intentMeetingClick);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

        //@BindView(R.id.meetingFragment_status)
        //public TextView mMeetingStatus;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
