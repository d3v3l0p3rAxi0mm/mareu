package app.d3v3l.mareu.views.meetings;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.service.MaReuApiService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeetingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingFragment extends Fragment {

    private MaReuApiService mApiService;
    private List<Meeting> mMeetings;
    private RecyclerView mRecyclerView;
    private boolean mDisplayMyMeetings = false;
    // Create keys for Bundle
    private static final String KEY_DISPLAY_MY_MEETINGS="KEY_DISPLAY_MY_MEETINGS";

    public MeetingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     */

    public static MeetingFragment newInstance(boolean displayMyMeetings) {
        MeetingFragment fragment = new MeetingFragment();
        // Create bundle and add it the information if the list displays favorite or not
        Bundle args = new Bundle();
        args.putBoolean(KEY_DISPLAY_MY_MEETINGS, displayMyMeetings);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMaReuApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        Context context = view.getContext();
        // Get data from Bundle (created in method newInstance)
        mDisplayMyMeetings = getArguments().getBoolean(KEY_DISPLAY_MY_MEETINGS, false);

        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;

    }

    /**
     * Init the List of Meetings
     */
    private void initList() {
        if (mDisplayMyMeetings) {
            // if the position of ViewAdapter is 1, app displays favorite List
            int idConnectedParticipant = mApiService.getConnectedParticipant().getId();
            Log.d("IdParticipant", String.valueOf(idConnectedParticipant));
            mMeetings = mApiService.getMyMeetings(idConnectedParticipant);
            mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetings, mDisplayMyMeetings));
        } else {
            // else, app displays favorite List
            mMeetings = mApiService.getMeetings();
            mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetings, mDisplayMyMeetings));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }
}