package app.d3v3l.mareu.controllers.participants;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.databinding.FragmentParticipantBinding;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.service.MaReuApiService;

public class ParticipantRecyclerViewAdapter extends RecyclerView.Adapter<ParticipantRecyclerViewAdapter.ViewHolder> {

    private final List<Participant> mParticipants;
    private ParticipantDetailsFragment detailsFragment;
    public ParticipantRecyclerViewAdapter(List<Participant> items) {
        mParticipants = items;
    }

    @NonNull
    @Override
    public ParticipantRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FragmentParticipantBinding fragmentParticipantBinding = FragmentParticipantBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(fragmentParticipantBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ParticipantRecyclerViewAdapter.ViewHolder holder, int position) {
        Participant participant = mParticipants.get(position);
        holder.bindView(participant, position);
    }

    @Override
    public int getItemCount() {
        return mParticipants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentParticipantBinding fragmentParticipantBinding;
        private final MaReuApiService mApiService = DI.getMaReuApiService();

        public ViewHolder(FragmentParticipantBinding fragmentParticipantBinding) {
            super(fragmentParticipantBinding.getRoot());
            this.fragmentParticipantBinding = fragmentParticipantBinding;
        }

        public void bindView(Participant participant, int position) {
            switch (position%2) {
                case 0 :
                    fragmentParticipantBinding.participantFragmentColorModuloFlagHorizontal.setBackgroundColor(0xFFE1BA2E);
                    fragmentParticipantBinding.participantFragmentColorModuloFlagVertical.setBackgroundColor(0xFFE1BA2E);
                    break;
                case 1 :
                    fragmentParticipantBinding.participantFragmentColorModuloFlagHorizontal.setBackgroundColor(0xFFCC7B82);
                    fragmentParticipantBinding.participantFragmentColorModuloFlagVertical.setBackgroundColor(0xFFCC7B82);
                    break;
            }

            Glide.with(fragmentParticipantBinding.participantFragmentAvatar.getContext())
                    .load(participant.getAvatar())
                    .apply(RequestOptions.centerCropTransform())
                    .into(fragmentParticipantBinding.participantFragmentAvatar);

            String fullname = participant.getFirstName() + " " + participant.getLastName();
            fragmentParticipantBinding.participantFragmentFullname.setText(fullname);
            fragmentParticipantBinding.participantFragmentEmail.setText(participant.getEmail());

            // Detect the orientation of device
            if (fragmentParticipantBinding.participantFragmentLinearLayout.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // When click on a meeting, display fragment of Meeting
                fragmentParticipantBinding.participantFragmentLinearLayout.setOnClickListener(view -> {
                    FragmentManager manager = ((AppCompatActivity) fragmentParticipantBinding.participantFragmentLinearLayout.getContext()).getSupportFragmentManager();
                    detailsFragment = ParticipantDetailsFragment.newInstance();
                    manager.beginTransaction()
                            .replace(R.id.container_details, detailsFragment)
                            .commit();
                });
            } else {
                fragmentParticipantBinding.participantFragmentLinearLayout.setOnClickListener(view -> {
                    Intent intent = new Intent(fragmentParticipantBinding.participantFragmentLinearLayout.getContext(), ParticipantDetailsActivity.class);
                    mApiService.setSelectedParticipant(participant);
                    fragmentParticipantBinding.participantFragmentLinearLayout.getContext().startActivity(intent);
                });
            }
        }
    }

}
