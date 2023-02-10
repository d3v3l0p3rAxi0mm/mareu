package app.d3v3l.mareu.controllers.meetings;

import static app.d3v3l.mareu.controllers.utils.DateAppUtils.createGregorianCalendarFromUIButtons;
import static app.d3v3l.mareu.controllers.utils.DateAppUtils.implementDatePickeronUIButton;
import static app.d3v3l.mareu.controllers.utils.DateAppUtils.implementTimePickeronUIButton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import app.d3v3l.mareu.databinding.ActivityAddMeetingBinding;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.AddMeetingEvent;
import app.d3v3l.mareu.model.Meeting;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.MaReuApiService;

public class AddMeetingActivity extends AppCompatActivity {

    private long mDuration = 0;
    private final List<Participant> participantsOfMeeting = new ArrayList<>();
    private MaReuApiService mApiService;
    private ActivityAddMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Call ApiService
        mApiService = DI.getMaReuApiService();
        List<Participant> participants = mApiService.getParticipants();
        List<Place> places = mApiService.getPlaces();

        // Implementation of Meeting Duration Picker
        final String[] values = {"0", "30", "60", "90", "120", "150", "180"};
        binding.addMeetingActivityDurationPicker.setMinValue(0);
        binding.addMeetingActivityDurationPicker.setMaxValue(values.length - 1);
        binding.addMeetingActivityDurationPicker.setDisplayedValues(values);
        binding.addMeetingActivityDurationPicker.setWrapSelectorWheel(false);
        binding.addMeetingActivityDurationPicker.setOnValueChangedListener((picker, oldVal, newVal) -> mDuration = Long.parseLong(values[newVal]));

        // Implementation of MeetingRooms radio List
        for(Place place: places) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(place.getId());
            radioButton.setTag(place.getName());
            radioButton.setText(place.getName());
            binding.addMeetingActivityPlacesRadioGroup.addView(radioButton);
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
            binding.addMeetingActivityParticipantsCheckBoxGroup.addView(checkbox);
        }

        // Go back to the previous Activity
        binding.addMeetingActivityBackPreviousActivity.setOnClickListener(view -> AddMeetingActivity.this.finish());

        // Implementation of Date Picker
        binding.addMeetingActivityDatePicker.setOnClickListener(view -> implementDatePickeronUIButton(binding.addMeetingActivityDatePicker));

        // Implementation of Time Picker
        binding.addMeetingActivityTimePicker.setOnClickListener(view -> implementTimePickeronUIButton(binding.addMeetingActivityTimePicker));

        // click on creation of Meeting
        binding.addMeetingActivityMeetingCreate.setOnClickListener(view -> {

            // Start date of Meeting
            GregorianCalendar startDate = createGregorianCalendarFromUIButtons(binding.addMeetingActivityDatePicker, binding.addMeetingActivityTimePicker);

            // verification of fields
            boolean correctFieldsForcreatingMeeting;
            String toastMessage;
            if (binding.addMeetingActivityMeetingSubjectTitle.getText().toString().equals("")) {
                toastMessage = "Title is empty !";
                correctFieldsForcreatingMeeting = false;
            } else {
                if (binding.addMeetingActivityMeetingSubject.getText().toString().equals("")) {
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
                            if (binding.addMeetingActivityPlacesRadioGroup.getCheckedRadioButtonId() == -1) {
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
                        mApiService.getPlaceById(binding.addMeetingActivityPlacesRadioGroup.getCheckedRadioButtonId()),
                        mApiService.getConnectedParticipant(),
                        participantsOfMeeting,
                        startDate,
                        endDate,
                        binding.addMeetingActivityMeetingSubjectTitle.getText().toString(),
                        binding.addMeetingActivityMeetingSubject.getText().toString()
                );
                EventBus.getDefault().postSticky(new AddMeetingEvent(meeting));
                AddMeetingActivity.this.finish();
            } else {
                Toast.makeText(binding.addMeetingActivityMeetingCreate.getContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

}