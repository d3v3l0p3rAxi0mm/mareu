package app.d3v3l.mareu.controllers.meetings;

import static app.d3v3l.mareu.utils.DateAppUtils.createGregorianCalendarFromUIButtons;
import static app.d3v3l.mareu.utils.DateAppUtils.implementDatePickeronUIButton;
import static app.d3v3l.mareu.utils.DateAppUtils.implementTimePickeronUIButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.AddMeetingEvent;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.MaReuApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMeetingActivity extends AppCompatActivity {

    private long mDuration = 0;
    private final List<Participant> participantsOfMeeting = new ArrayList<>();
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
            final String[] values = {"0", "30", "60", "90", "120", "150", "180"};
            mDurationPicker.setMinValue(0);
            mDurationPicker.setMaxValue(values.length - 1);
            mDurationPicker.setDisplayedValues(values);
            mDurationPicker.setWrapSelectorWheel(false);
            mDurationPicker.setOnValueChangedListener((picker, oldVal, newVal) -> mDuration = Long.parseLong(values[newVal]));
        }

        // Implementation of MeetingRooms radio List
        for(Place place: places) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(place.getId());
            radioButton.setTag(place.getName());
            radioButton.setText(place.getName());
            radioGroup.addView(radioButton);
        }

        // Implementation of Meeting Participants List
        for(Participant participant: participants) {
            CheckBox checkbox = new CheckBox(this);
            checkbox.setText(participant.getEmail());
            checkbox.setId(participant.getId());
            if (participant.equals(mApiService.getConnectedParticipant())) {
                checkbox.setChecked(true);
                participantsOfMeeting.add(mApiService.getConnectedParticipant());
            }
            checkbox.setOnClickListener(v -> {
                if(checkbox.isChecked()){
                    participantsOfMeeting.add(participant);
                } else {
                    participantsOfMeeting.remove(participant);
                }
            });
            participantsLinearLayout.addView(checkbox);
        }

        // Go back to the previous Activity
        back.setOnClickListener(view -> AddMeetingActivity.this.finish());

        // Implementation of Date Picker
        mDate.setOnClickListener(view -> implementDatePickeronUIButton(mDate));

        // Implementation of Time Picker
        mTime.setOnClickListener(view -> implementTimePickeronUIButton(mTime));

        // click on creation of Meeting
        mCreate.setOnClickListener(view -> {

            // Start date of Meeting
            GregorianCalendar startDate = createGregorianCalendarFromUIButtons(mDate, mTime);

            // verification of fields
            boolean correctFieldsForcreatingMeeting;
            String toastMessage;
            if (title.getText().toString().equals("")) {
                toastMessage = "Title is empty !";
                correctFieldsForcreatingMeeting = false;
            } else {
                if (subject.getText().toString().equals("")) {
                    toastMessage = "Subject is empty !";
                    correctFieldsForcreatingMeeting = false;
                } else {
                    if (participantsOfMeeting.size() == 0) {
                        toastMessage = "No participant !";
                        correctFieldsForcreatingMeeting = false;
                    } else {
                        if (startDate == null) {
                            toastMessage = "Date or Time not set !";
                            correctFieldsForcreatingMeeting = false;
                        } else {
                            if (radioGroup.getCheckedRadioButtonId() == -1) {
                                toastMessage = "No room selected !";
                                correctFieldsForcreatingMeeting = false;
                            } else {
                                if (mDuration == 0) {
                                    toastMessage = "No duration set !";
                                    correctFieldsForcreatingMeeting = false;
                                } else {
                                    toastMessage = "";
                                    correctFieldsForcreatingMeeting = true;
                                }
                            }
                        }
                    }
                }
            }
            // End of verification

            if (correctFieldsForcreatingMeeting) {

                // End Date of Meeting
                long start = startDate.getTime().getTime();
                long end = start + (mDuration * 60000);
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
                EventBus.getDefault().postSticky(new AddMeetingEvent(meeting));
                AddMeetingActivity.this.finish();
            } else {
                Toast.makeText(mCreate.getContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

}