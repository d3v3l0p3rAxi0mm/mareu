package app.d3v3l.mareu.views.meetings;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
        holder.mMeetingPlace.setText(meeting.getPlace().toString());
        holder.mStartMeeting.setText(meeting.getStartOfMeeting().toString());
        holder.mEndMeeting.setText(meeting.getEndOfMeeting().toString());
        holder.mNumberOfParticipants.setText(String.valueOf(meeting.getParticipants().size()));
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.meetingFragment_place)
        public TextView mMeetingPlace;
        @BindView(R.id.meetingFragment_startMeeting)
        public TextView mStartMeeting;
        @BindView(R.id.meetingFragment_endMeeting)
        public TextView mEndMeeting;
        @BindView(R.id.meetingFragment_numberOfParticipants)
        public TextView mNumberOfParticipants;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
