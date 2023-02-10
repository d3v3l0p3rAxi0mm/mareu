package app.d3v3l.mareu.controllers.participants;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import app.d3v3l.mareu.databinding.FragmentParticipantDetailsBinding;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.service.MaReuApiService;
import app.d3v3l.mareu.controllers.utils.OnButtonClickedListener;

public class ParticipantDetailsFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;
    private FragmentParticipantDetailsBinding binding;

    // Call apiService
    MaReuApiService mApiService = DI.getMaReuApiService();

    public ParticipantDetailsFragment() {
        // Required empty public constructor
    }

    public static ParticipantDetailsFragment newInstance() {
        return new ParticipantDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentParticipantDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.back.setOnClickListener(this);
        Participant mParticipant = mApiService.getSelectedParticipant();
        Glide.with(binding.participantDetailsAvatar.getContext())
                .load(mParticipant.getAvatar())
                .apply(RequestOptions.centerCropTransform())
                .into(binding.participantDetailsAvatar);
        String name = mParticipant.getFirstName() + " " + mParticipant.getLastName();
        binding.participantDetailsName.setText(name);
        binding.participantDetailsEmail.setText(mParticipant.getEmail());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

    @Override
    public void onClick(View view) {
        mCallback.onButtonClicked(view);
    }

    private void createCallbackToParentActivity(){
        try {
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e + " must implement OnButtonClickedListener");
        }
    }
}