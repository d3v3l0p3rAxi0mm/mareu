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

import app.d3v3l.mareu.views.meetings.MeetingDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewPagerActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.viewpager_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager_container)
    ViewPager mViewPager;
    @BindView(R.id.addMeetingFloatingButton)
    FloatingActionButton mAddMeetingButton;

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

        mAddMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddMeetingClick = new Intent(mAddMeetingButton.getContext(), AddMeetingActivity.class);
                mAddMeetingButton.getContext().startActivity(intentAddMeetingClick);
            }
        });

    }


}