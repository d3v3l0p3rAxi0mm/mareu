package app.d3v3l.mareu.views.viewpager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.views.meetings.AddMeetingActivity;
import app.d3v3l.mareu.views.meetings.MeetingFilterActivity;
import app.d3v3l.mareu.views.participants.AddParticipantActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

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
    FloatingActionButton mFilter;
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

        // Display contextuel Button based on displayed page position
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i == 0) {
                    mAddMeeting.show();
                    mFilter.show();
                    mAddUser.hide();
                }
                else if (i == 1) {
                    mAddMeeting.hide();
                    mFilter.hide();
                    mAddUser.show();
                }
                else {
                    mAddMeeting.hide();
                    mFilter.hide();
                    mAddUser.hide();
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mAddMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mAddMeeting.getContext(), AddMeetingActivity.class);
                mAddMeeting.getContext().startActivity(intent);
            }
        });

        mAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mAddUser.getContext(), AddParticipantActivity.class);
                mAddUser.getContext().startActivity(intent);
            }
        });

        mFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mFilter.getContext(), MeetingFilterActivity.class);
                mFilter.getContext().startActivity(intent);
            }
        });

    }
}