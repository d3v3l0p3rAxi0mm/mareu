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
import android.widget.FrameLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.AddMeetingEvent;
import app.d3v3l.mareu.events.CloseMeetingEvent;
import app.d3v3l.mareu.events.DeleteMeetingEvent;
import app.d3v3l.mareu.events.MeetingFilterEvent;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.service.MaReuApiService;

public class MeetingFragment extends Fragment {

    private MaReuApiService mApiService;
    private List<Meeting> mMeetings;
    private RecyclerView mRecyclerView;

    public MeetingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     */

    public static MeetingFragment newInstance() {
        MeetingFragment fragment = new MeetingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMaReuApiService();
        mMeetings = mApiService.getMeetings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;

    }

    /**
     * Init the List of Meetings
     */
    private void initList() {
        mMeetings = mApiService.getMeetings();
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetings));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
        Log.d("MyLog", "MeetingFragment onResume()");
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d("MyLog", "MeetingFragment onStart()");
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Log.d("MyLog", "MeetingFragment onStop()");
    }

    /**
     * Add a meeting
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddMeetingEvent(AddMeetingEvent event) {
        mApiService.addMeeting(event.mAddMeeting);
        initList();
        Log.d("MyLog", "Subscribe onAddMeetingEvent OK !");
    }

    /**
     * Filter meetings if asked
     * @param event
     */
    @Subscribe
    public void onMeetingFilterEvent(MeetingFilterEvent event) {
        mMeetings = mApiService.getFilteredMeetings(event.mMeetingFilters);
        initList();
        Log.d("MyLog", "Subscribe onMeetingFilterEvent OK !");
    }

    /**
     * Delete a meeting
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteMeetingEvent(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.mDeleteMeeting);
        initList();
        Log.d("MyLog", "Subscribe onDeleteMeetingEvent OK !");
    }

    /**
     * Close a meeting
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    //@Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onCloseMeetingEvent(CloseMeetingEvent event) {
        Log.d("MyLog", "Subscribe onCloseMeetingEvent OK !");
        mApiService.closeMeeting(event.mCloseMeeting);
        initList();
    }

}