package app.d3v3l.mareu.views.participants;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.service.MaReuApiService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParticipantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParticipantFragment extends Fragment {

    private MaReuApiService mApiService;
    private List<Participant> mParticipants;
    private RecyclerView mRecyclerView;

    public ParticipantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     */

    public static ParticipantFragment newInstance() {
        return new ParticipantFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMaReuApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_participant_list, container, false);
        Context context = view.getContext();

        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;






    }

    /**
     * Init the List of Participants
     */
    private void initList() {
        mParticipants = mApiService.getParticipants();
        mRecyclerView.setAdapter(new ParticipantRecyclerViewAdapter(mParticipants));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }
}