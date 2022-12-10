package app.d3v3l.mareu.views.participants;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.model.Participant;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ParticipantRecyclerViewAdapter extends RecyclerView.Adapter<ParticipantRecyclerViewAdapter.ViewHolder> {

    private final List<Participant> mParticipants;

    public ParticipantRecyclerViewAdapter(List<Participant> items) {
        mParticipants = items;
    }

    @NonNull
    @Override
    public ParticipantRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_participant, parent, false);
        return new ParticipantRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ParticipantRecyclerViewAdapter.ViewHolder holder, int position) {
        Participant participant = mParticipants.get(position);

        switch (position%2) {
            case 0 :
                holder.mParticipantColorModuloFlagHorizontal.setBackgroundColor(0xFFE1BA2E);
                holder.mParticipantColorModuloFlagVertical.setBackgroundColor(0xFFE1BA2E);
                break;
            case 1 :
                holder.mParticipantColorModuloFlagHorizontal.setBackgroundColor(0xFFCC7B82);
                holder.mParticipantColorModuloFlagVertical.setBackgroundColor(0xFFCC7B82);
                break;
        }

        Glide.with(holder.mParticipantAvatar.getContext())
                .load(participant.getAvatar())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.mParticipantAvatar);

        String fullname = participant.getFirstName() + " " + participant.getLastName();
        holder.mParticipantFullName.setText(fullname);
        holder.mParticipantEmail.setText(participant.getEmail());
    }

    @Override
    public int getItemCount() {
        return mParticipants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.participantFragment_colorModuloFlagHorizontal)
        public ImageView mParticipantColorModuloFlagHorizontal;
        @BindView(R.id.participantFragment_colorModuloFlagVertical)
        public ImageView mParticipantColorModuloFlagVertical;
        @BindView(R.id.participantFragment_avatar)
        public ImageView mParticipantAvatar;
        @BindView(R.id.participantFragment_fullname)
        public TextView mParticipantFullName;
        @BindView(R.id.participantFragment_email)
        public TextView mParticipantEmail;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
