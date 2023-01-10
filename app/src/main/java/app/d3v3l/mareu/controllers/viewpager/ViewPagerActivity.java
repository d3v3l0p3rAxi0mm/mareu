package app.d3v3l.mareu.controllers.viewpager;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.MaReuApiService;
import app.d3v3l.mareu.utils.OnButtonClickedListener;
import app.d3v3l.mareu.controllers.meetings.AddMeetingActivity;
import app.d3v3l.mareu.controllers.meetings.MeetingDetailsFragment;
import app.d3v3l.mareu.controllers.meetings.MeetingFilterActivity;
import app.d3v3l.mareu.controllers.participants.AddParticipantActivity;

import app.d3v3l.mareu.controllers.participants.ParticipantDetailsFragment;
import app.d3v3l.mareu.controllers.places.PlaceDetailsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity implements OnButtonClickedListener {

    // UI Components
    @BindView(R.id.viewpager_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager_container)
    ViewPager mViewPager;
    @BindView(R.id.viewpager_addMeeting)
    FloatingActionButton mAddMeeting;
    @BindView(R.id.viewpager_addUser)
    FloatingActionButton mAddUser;
    @BindView(R.id.viewpager_meetingsFilter)
    Button mFilter;

    private final MaReuApiService mApiService = DI.getMaReuApiService();
    private final List<Meeting> mMeetings = mApiService.getMeetings();
    private final List<Participant> mParticipants = mApiService.getParticipants();
    private final List<Place> mPlaces = mApiService.getPlaces();
    ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);

        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        // Display action Buttons (add meeting, add user, filters) based on displayed page position
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i == 0) {
                    mAddMeeting.show();
                    mFilter.setVisibility(View.VISIBLE);
                    mAddUser.hide();
                    if (mViewPager.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        FragmentManager manager = ((AppCompatActivity) mViewPager.getContext()).getSupportFragmentManager();
                        MeetingDetailsFragment detailsFragment = MeetingDetailsFragment.newInstance(mMeetings.get(0).getID());
                        manager.beginTransaction()
                                .replace(R.id.container_details, detailsFragment)
                                .commit();
                    }
                }
                else if (i == 1) {
                    mAddMeeting.hide();
                    mFilter.setVisibility(View.GONE);
                    mAddUser.show();
                    if (mViewPager.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        FragmentManager manager = ((AppCompatActivity) mViewPager.getContext()).getSupportFragmentManager();
                        ParticipantDetailsFragment detailsFragment = ParticipantDetailsFragment.newInstance(mParticipants.get(0).getId());
                        manager.beginTransaction()
                                .replace(R.id.container_details, detailsFragment)
                                .commit();
                    }
                }
                else {
                    mAddMeeting.hide();
                    mFilter.setVisibility(View.GONE);
                    mAddUser.hide();
                    if (mViewPager.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        FragmentManager manager = ((AppCompatActivity) mViewPager.getContext()).getSupportFragmentManager();
                        PlaceDetailsFragment detailsFragment = PlaceDetailsFragment.newInstance(mPlaces.get(0).getId());
                        manager.beginTransaction()
                                .replace(R.id.container_details, detailsFragment)
                                .commit();
                    }
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mAddMeeting.setOnClickListener(view -> {
            Intent intent = new Intent(mAddMeeting.getContext(), AddMeetingActivity.class);
            mAddMeeting.getContext().startActivity(intent);
        });

        mAddUser.setOnClickListener(view -> {
            Intent intent = new Intent(mAddUser.getContext(), AddParticipantActivity.class);
            mAddUser.getContext().startActivity(intent);
        });

        mFilter.setOnClickListener(view -> {
            Intent intent = new Intent(mFilter.getContext(), MeetingFilterActivity.class);
            mFilter.getContext().startActivity(intent);
        });

    }

    @Override
    public void onButtonClicked(View view) {

    }
}