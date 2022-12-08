package app.d3v3l.mareu.views.participants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.d3v3l.mareu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParticipantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParticipantFragment extends Fragment {

    public ParticipantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     */

    public static ParticipantFragment newInstance() {
        ParticipantFragment fragment = new ParticipantFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_participant, container, false);
    }
}