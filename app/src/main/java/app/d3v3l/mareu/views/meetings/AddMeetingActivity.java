package app.d3v3l.mareu.views.meetings;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.MaReuApiService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMeetingActivity extends AppCompatActivity {

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinutes;
    private long muration;
    private long mDuration;
    private List<Participant> participantsOfMeeting = new ArrayList<>();
    private MaReuApiService mApiService;

    @BindView(R.id.addMeetingActivity_meetingSubjectTitle)
    EditText title;
    @BindView(R.id.addMeetingActivity_meetingSubject)
    EditText subject;

    @BindView(R.id.addMeetingActivity_backPreviousActivity)
    Button back;
    @BindView(R.id.addMeetingActivity_datePicker)
    Button mDate;
    @BindView(R.id.addMeetingActivity_timePicker)
    Button mTime;
    @BindView(R.id.addMeetingActivity_placesRadioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.addMeetingActivity_participantsCheckBoxGroup)
    LinearLayout participantsLinearLayout;
    @BindView(R.id.addMeetingActivity_durationPicker)
    NumberPicker mDurationPicker;
    @BindView(R.id.addMeetingActivity_meetingCreate)
    Button mCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);

        mApiService = DI.getMaReuApiService();
        List<Participant> participants = mApiService.getParticipants();
        List<Place> places = mApiService.getPlaces();

        // Implementation of Meeting Duration Picker
        if (mDurationPicker != null) {
            final String[] values = {"30", "60", "90", "120", "150", "180"};
            mDurationPicker.setMinValue(0);
            mDurationPicker.setMaxValue(values.length - 1);
            mDurationPicker.setDisplayedValues(values);
            mDurationPicker.setWrapSelectorWheel(false);
            mDurationPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    mDuration = Long.parseLong(values[newVal]);
                    Log.d("mDuration", String.valueOf(mDuration));
                }

            });
        }

        // Implementation of MeetingRooms radio List
        for(Place place: places) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(place.getId());
            radioButton.setText(place.getName());
            radioGroup.addView(radioButton);
        }

        // Implementation of Meeting Participants List
        List<CheckBox> checkBoxParticipants = new ArrayList<>();
        for(Participant participant: participants) {
            CheckBox checkbox = new CheckBox(this);
            checkbox.setText(participant.getEmail());
            checkbox.setId(participant.getId());
            if (participant.equals(mApiService.getConnectedParticipant())) {
                checkbox.setChecked(true);
                participantsOfMeeting.add(mApiService.getConnectedParticipant());
            }
            checkbox.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    if(checkbox.isChecked()){
                        participantsOfMeeting.add(participant);
                    } else {
                        participantsOfMeeting.remove(participant);
                    }
                }
            });
            participantsLinearLayout.addView(checkbox);
        }

        // Go back to the previous Activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMeetingActivity.this.finish();
            }
        });

        // Implementation of Date Picker
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
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
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                mTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinutes, true);
                timePickerDialog.show();
            }
        });

        // click on creation of Meeting
        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // recovery and calculation of Start and End Date of Meeting
                // Start Date
                String DateDisplayedOnUIButton = mDate.getText().toString();
                String TimeDisplayedOnUIButton = mTime.getText().toString();
                String[] splitDate = DateDisplayedOnUIButton.split("/");
                String[] splitTime = TimeDisplayedOnUIButton.split(":");
                GregorianCalendar startDate = new GregorianCalendar(
                        Integer.parseInt(splitDate[2]),
                        Integer.parseInt(splitDate[1])-1,
                        Integer.parseInt(splitDate[0]),
                        Integer.parseInt(splitTime[0]),
                        Integer.parseInt(splitTime[1])
                );
                // End Date
                long start = startDate.getTime().getTime();
                Log.d("StartTimestamp", String.valueOf(start));
                long end = start + (mDuration*60000);
                GregorianCalendar endDate = new GregorianCalendar();
                endDate.setTimeInMillis(end);

                Meeting meeting = new Meeting(
                        mApiService.getLastMeetingId() + 1,
                        mApiService.getPlaceById(radioGroup.getCheckedRadioButtonId()),
                        mApiService.getConnectedParticipant(),
                        participantsOfMeeting,
                        startDate,
                        endDate,
                        title.getText().toString(),
                        subject.getText().toString()
                );
                mApiService.addMeeting(meeting);
                AddMeetingActivity.this.finish();
            }
        });
    }


}