package app.d3v3l.mareu.controllers.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.d3v3l.mareu.controllers.participants.ParticipantFragment;
import app.d3v3l.mareu.controllers.meetings.MeetingFragment;
import app.d3v3l.mareu.controllers.places.PlaceFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                return MeetingFragment.newInstance();
            case 1:
                return ParticipantFragment.newInstance();
            case 2:
                return PlaceFragment.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
