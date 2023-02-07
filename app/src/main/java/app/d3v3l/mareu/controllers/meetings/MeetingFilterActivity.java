package app.d3v3l.mareu.controllers.meetings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

import org.greenrobot.eventbus.EventBus;

import java.util.GregorianCalendar;
import java.util.List;

import app.d3v3l.mareu.databinding.ActivityMeetingFilterBinding;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.events.MeetingFilterEvent;
import app.d3v3l.mareu.model.MeetingFilter;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.MaReuApiService;

import static app.d3v3l.mareu.controllers.utils.DateAppUtils.createGregorianCalendarFromUIButtons;
import static app.d3v3l.mareu.controllers.utils.DateAppUtils.implementDatePickeronUIButton;
import static app.d3v3l.mareu.controllers.utils.DateAppUtils.implementTimePickeronUIButton;

public class MeetingFilterActivity extends AppCompatActivity {

    private MaReuApiService mApiService;
    private Boolean filterConnectedParticipant = false;
    private Place filterPlace = null;
    private GregorianCalendar filterDate = null;
    private ActivityMeetingFilterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingFilterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mApiService = DI.getMaReuApiService();
        List<Place> places = mApiService.getPlaces();

        // Go back to the previous Activity
        binding.meetingFilterActivityBackPreviousActivity.setOnClickListener(view -> MeetingFilterActivity.this.finish());

        // Implementation of MeetingRooms radio List
        for(Place place: places) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(place.getId());
            radioButton.setText(place.getName());
            binding.meetingFilterActivityPlacesRadioGroup.addView(radioButton);
        }

        // Implementation of Date Picker
        binding.meetingFilterActivityDatePicker.setOnClickListener(view -> implementDatePickeronUIButton(binding.meetingFilterActivityDatePicker));

        // Implementation of Time Picker
        binding.meetingFilterActivityTimePicker.setOnClickListener(view -> implementTimePickeronUIButton(binding.meetingFilterActivityTimePicker));

        // Launch the search
        binding.meetingFilterActivitySearch.setOnClickListener(v -> {
            filterConnectedParticipant = binding.meetingFilterActivityOnlyConnectedParticipant.isChecked();
            filterPlace = mApiService.getPlaceById(binding.meetingFilterActivityPlacesRadioGroup.getCheckedRadioButtonId());
            filterDate = createGregorianCalendarFromUIButtons(binding.meetingFilterActivityDatePicker, binding.meetingFilterActivityTimePicker);
            MeetingFilter myMeetingFilter = new MeetingFilter(filterConnectedParticipant, filterPlace, filterDate);
            EventBus.getDefault().postSticky(new MeetingFilterEvent(myMeetingFilter));
            MeetingFilterActivity.this.finish();
        });


    }

}