package app.d3v3l.mareu.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import app.d3v3l.mareu.R;

public class DateAppUtils {

    public static void implementDatePickeronUIButton(Button btn) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int Year = c.get(Calendar.YEAR);
        int Month = c.get(Calendar.MONTH);
        int Day = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(btn.getContext(), (view, year, monthOfYear, dayOfMonth) -> {
            String textToDisplay = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
            btn.setText(textToDisplay);
        }, Year, Month, Day);
        datePickerDialog.show();
    }

    public static void implementTimePickeronUIButton(Button btn) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int Hour = c.get(Calendar.HOUR_OF_DAY);
        int Minutes = c.get(Calendar.MINUTE);
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(btn.getContext(), (view, hourOfDay, minute) -> {
            String textToDisplay = hourOfDay + ":" + minute;
            btn.setText(textToDisplay);
        }, Hour, Minutes, true);
        timePickerDialog.show();
    }

    public static GregorianCalendar createGregorianCalendarFromUIButtons(Button btnDate, Button btnTime) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String DateDisplayedOnUIButton = btnDate.getText().toString();
        String TimeDisplayedOnUIButton = btnTime.getText().toString();
        if (
                !DateDisplayedOnUIButton.equals(btnDate.getContext().getString(R.string.select_a_date)) &&
                !TimeDisplayedOnUIButton.equals(btnTime.getContext().getString(R.string.select_time))
        ) {
            String[] splitDate = DateDisplayedOnUIButton.split("/");
            String[] splitTime = TimeDisplayedOnUIButton.split(":");

            gregorianCalendar.set(
                    Integer.parseInt(splitDate[2]),
                    Integer.parseInt(splitDate[1]) - 1,
                    Integer.parseInt(splitDate[0]),
                    Integer.parseInt(splitTime[0]),
                    Integer.parseInt(splitTime[1])
            );
            return gregorianCalendar;
        } else {
            return null;
        }
    }

    public static String displayMeetingStartDate(GregorianCalendar gregorianCalendar) {
        String year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
        SimpleDateFormat month_date = new SimpleDateFormat("MM");
        String month = month_date.format(gregorianCalendar.getTime());
        String day = String.format(new Locale("Fr", "Fr"), "%02d",gregorianCalendar.get(Calendar.DAY_OF_MONTH));
        return day + "/" + month + "/" + year;
    }

    public static String displayMeetingStartTime(GregorianCalendar gregorianCalendar) {
        String hour = String.format(new Locale("Fr", "Fr"),"%02d",gregorianCalendar.get(Calendar.HOUR_OF_DAY));
        String mn = String.format(new Locale("Fr", "Fr"),"%02d",gregorianCalendar.get(Calendar.MINUTE));
        return hour + ":" + mn;
    }

}