package app.d3v3l.mareu.controllers.participants;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.service.MaReuApiService;
import app.d3v3l.mareu.utils.OnButtonClickedListener;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ParticipantDetailsFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;


    // UI Components
    @BindView(R.id.participantDetails_avatar)
    ImageView mAvatar;
    @BindView(R.id.participantDetails_Name)
    TextView mName;
    @BindView(R.id.participantDetails_email)
    TextView mEmail;

    private Participant mParticipant;
    private int participantId;

    // Call apiService
    MaReuApiService mApiService = DI.getMaReuApiService();
    private static final String PARTICIPANTID = "participantId";

    public ParticipantDetailsFragment() {
        // Required empty public constructor
    }

    public static ParticipantDetailsFragment newInstance(int participantId) {
        ParticipantDetailsFragment fragment = new ParticipantDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(PARTICIPANTID, participantId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            participantId = getArguments().getInt(PARTICIPANTID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_participant_details, container, false);
        //Set onClickListener to button "BACk"
        view.findViewById(R.id.back).setOnClickListener(this);
        ButterKnife.bind(this, view);

        mParticipant = mApiService.getParticipantById(participantId);
        Glide.with(mAvatar.getContext())
                .load(mParticipant.getAvatar())
                .apply(RequestOptions.centerCropTransform())
                .into(mAvatar);
        String name = mParticipant.getFirstName() + " " + mParticipant.getLastName();
        mName.setText(name);
        mEmail.setText(mParticipant.getEmail());

        return view;

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