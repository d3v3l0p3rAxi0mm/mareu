package app.d3v3l.mareu.utils;

import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class DateAppUtils {

    public static void implementDatePickeronUIButton(Button btn) {
        final Calendar c = Calendar.getInstance();
        int Year = c.get(Calendar.YEAR);
        int Month = c.get(Calendar.MONTH);
        int Day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(btn.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String textToDisplay = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                btn.setText(textToDisplay);
            }
        }, Year, Month, Day);
        datePickerDialog.show();
    }

}
