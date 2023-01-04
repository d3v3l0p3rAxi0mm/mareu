package app.d3v3l.mareu.views.meetings;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.GregorianCalendar;
import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.MeetingFilterEvent;
import app.d3v3l.mareu.model.MeetingFilter;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.MaReuApiService;

import static app.d3v3l.mareu.utils.DateAppUtils.createGregorianCalendarFromUIButtons;
import static app.d3v3l.mareu.utils.DateAppUtils.implementDatePickeronUIButton;
import static app.d3v3l.mareu.utils.DateAppUtils.implementTimePickeronUIButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingFilterActivity extends AppCompatActivity {

    @BindView(R.id.meetingFilterActivity_backPreviousActivity)
    Button back;
    @BindView(R.id.meetingFilterActivity_search)
    Button search;
    @BindView(R.id.meetingFilterActivity_placesRadioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.meetingFilterActivity_onlyConnectedParticipant)
    CheckBox onlyConnectedParticipant;
    @BindView(R.id.meetingFilterActivity_datePicker)
    Button mDate;
    @BindView(R.id.meetingFilterActivity_timePicker)
    Button mTime;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinutes;
    private MaReuApiService mApiService;
    private Boolean filterConnectedParticipant = false;
    private Place filterPlace = null;
    private GregorianCalendar filterDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_filter);
        ButterKnife.bind(this);

        mApiService = DI.getMaReuApiService();
        List<Place> places = mApiService.getPlaces();

        // Implementation of MeetingRooms radio List
        for(Place place: places) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(place.getId());
            radioButton.setText(place.getName());
            radioGroup.addView(radioButton);
        }

        // Go back to the previous Activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeetingFilterActivity.this.finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterConnectedParticipant = onlyConnectedParticipant.isChecked();
                filterPlace = mApiService.getPlaceById(radioGroup.getCheckedRadioButtonId());
                filterDate = createGregorianCalendarFromUIButtons(mDate, mTime);
                MeetingFilter myMeetingFilter = new MeetingFilter(filterConnectedParticipant, filterPlace, filterDate);
                EventBus.getDefault().post(new MeetingFilterEvent(myMeetingFilter));
                MeetingFilterActivity.this.finish();

            }
        });

        // Implementation of Date Picker
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                implementDatePickeronUIButton(mDate);
            }
        });

        // Implementation of Time Picker
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                implementTimePickeronUIButton(mTime);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyLog", "MeetingFilterActivity onDestroy()");
    }
}