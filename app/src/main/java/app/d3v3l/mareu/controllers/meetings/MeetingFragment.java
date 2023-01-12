package app.d3v3l.mareu.controllers.meetings;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Objects;

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

    public static MeetingFragment newInstance() {
        return new MeetingFragment();
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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        return view;

    }

    /**
     * Init the List of Meetings
     * @param refresh boolean which ask to get Meetings from List in memory in ApiService or get List by recall method GetMeetings
     */
    private void initList(boolean refresh) {
        // refresh : true when we need to rebuild MeetingList to reOrder the list
        if (refresh) {
            mMeetings = mApiService.getMeetings();
        }
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetings));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Add a meeting
     * @param event AddMeetingEvent
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onAddMeetingEvent(AddMeetingEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        mApiService.addMeeting(event.mAddMeeting);
        initList(true);
    }

    /**
     * Filter meetings if asked
     * @param event MeetingFilterEvent
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMeetingFilterEvent(MeetingFilterEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        mMeetings = mApiService.getFilteredMeetings(event.mMeetingFilters);
        initList(false);
    }

    /**
     * Delete a meeting
     * @param event DeleteMeetingEvent
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDeleteMeetingEvent(DeleteMeetingEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        mApiService.deleteMeeting(event.mDeleteMeeting);
        initList(false);
    }

    /**
     * Close a meeting
     * @param event CloseMeetingEvent
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onCloseMeetingEvent(CloseMeetingEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        mApiService.closeMeeting(event.mCloseMeeting);
        initList(false);
    }

}