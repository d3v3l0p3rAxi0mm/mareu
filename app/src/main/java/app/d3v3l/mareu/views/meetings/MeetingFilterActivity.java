package app.d3v3l.mareu.views.meetings;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.MeetingFilterEvent;
import app.d3v3l.mareu.model.MeetingFilter;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.MaReuApiService;
import static app.d3v3l.mareu.utils.DateAppUtils.implementDatePickeronUIButton;
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
                // Date
                String DateDisplayedOnUIButton = mDate.getText().toString();
                String TimeDisplayedOnUIButton = mTime.getText().toString();
                if (DateDisplayedOnUIButton != getString(R.string.date) && TimeDisplayedOnUIButton != getString(R.string.time) ) {
                    String[] splitDate = DateDisplayedOnUIButton.split("/");
                    String[] splitTime = TimeDisplayedOnUIButton.split(":");
                    filterDate = new GregorianCalendar(
                            Integer.parseInt(splitDate[2]),
                            Integer.parseInt(splitDate[1]) - 1,
                            Integer.parseInt(splitDate[0]),
                            Integer.parseInt(splitTime[0]),
                            Integer.parseInt(splitTime[1])
                    );
                }
                MeetingFilter myMeetingFilter = new MeetingFilter(filterConnectedParticipant, filterPlace, filterDate);
                EventBus.getDefault().postSticky(new MeetingFilterEvent(myMeetingFilter));
                MeetingFilterActivity.this.finish();

            }
        });

        // Implementation of Date Picker
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                implementDatePickeronUIButton(mDate);
                /*
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(mDate.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                 */
            }
        });

        // Implementation of Time Picker
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinutes = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(mTime.getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                mTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinutes, true);
                timePickerDialog.show();
            }
        });



    }



}