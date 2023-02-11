package app.d3v3l.mareu.controllers.viewpager;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.databinding.ActivityViewPagerBinding;
import app.d3v3l.mareu.controllers.utils.OnButtonClickedListener;
import app.d3v3l.mareu.controllers.meetings.AddMeetingActivity;
import app.d3v3l.mareu.controllers.meetings.MeetingDetailsFragment;
import app.d3v3l.mareu.controllers.meetings.MeetingFilterActivity;
import app.d3v3l.mareu.controllers.participants.AddParticipantActivity;

import app.d3v3l.mareu.controllers.participants.ParticipantDetailsFragment;
import app.d3v3l.mareu.controllers.places.PlaceDetailsFragment;

public class ViewPagerActivity extends AppCompatActivity implements OnButtonClickedListener {

    private ActivityViewPagerBinding binding;
    ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        binding.viewpagerContainer.setAdapter(mPagerAdapter);
        binding.viewpagerContainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.viewpagerTabs));
        binding.viewpagerTabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.viewpagerContainer));

        // Display action Buttons (add meeting, add user, filters) based on displayed page position
        binding.viewpagerContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i == 0) {
                    binding.viewpagerAddMeeting.show();
                    binding.viewpagerMeetingsFilter.setVisibility(View.VISIBLE);
                    binding.viewpagerAddUser.hide();
                    if (binding.viewpagerContainer.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE &&
                            binding.viewpagerContainer.getResources().getConfiguration().screenWidthDp >= 600) {
                        FragmentManager manager = ((AppCompatActivity) binding.viewpagerContainer.getContext()).getSupportFragmentManager();
                        MeetingDetailsFragment detailsFragment = MeetingDetailsFragment.newInstance();
                        manager.beginTransaction().replace(R.id.container_details, detailsFragment).commit();
                    }
                }
                else if (i == 1) {
                    binding.viewpagerAddMeeting.hide();
                    binding.viewpagerMeetingsFilter.setVisibility(View.GONE);
                    binding.viewpagerAddUser.show();
                    if (binding.viewpagerContainer.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE &&
                            binding.viewpagerContainer.getResources().getConfiguration().screenWidthDp >= 600) {
                        FragmentManager manager = ((AppCompatActivity) binding.viewpagerContainer.getContext()).getSupportFragmentManager();
                        ParticipantDetailsFragment detailsFragment = ParticipantDetailsFragment.newInstance();
                        manager.beginTransaction().replace(R.id.container_details, detailsFragment).commit();
                    }
                }
                else {
                    binding.viewpagerAddMeeting.hide();
                    binding.viewpagerMeetingsFilter.setVisibility(View.GONE);
                    binding.viewpagerAddUser.hide();
                    if (binding.viewpagerContainer.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE &&
                            binding.viewpagerContainer.getResources().getConfiguration().screenWidthDp >= 600) {
                        FragmentManager manager = ((AppCompatActivity) binding.viewpagerContainer.getContext()).getSupportFragmentManager();
                        PlaceDetailsFragment detailsFragment = PlaceDetailsFragment.newInstance();
                        manager.beginTransaction().replace(R.id.container_details, detailsFragment).commit();
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

        binding.viewpagerAddMeeting.setOnClickListener(view -> {
            Intent intent = new Intent(binding.viewpagerAddMeeting.getContext(), AddMeetingActivity.class);
            binding.viewpagerAddMeeting.getContext().startActivity(intent);
        });

        binding.viewpagerAddUser.setOnClickListener(view -> {
            Intent intent = new Intent(binding.viewpagerAddUser.getContext(), AddParticipantActivity.class);
            binding.viewpagerAddUser.getContext().startActivity(intent);
        });

        binding.viewpagerMeetingsFilter.setOnClickListener(view -> {
            Intent intent = new Intent(binding.viewpagerMeetingsFilter.getContext(), MeetingFilterActivity.class);
            binding.viewpagerMeetingsFilter.getContext().startActivity(intent);
        });

    }

    @Override
    public void onButtonClicked(View view) {

    }
}