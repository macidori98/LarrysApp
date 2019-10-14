package com.example.larrysapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment {
    public int year, month, day;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH);
        this.day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    /*DatePickerFragment.this.setYear(String.valueOf(view.getYear()));
                    DatePickerFragment.this.setMonth(String.valueOf(view.getMonth()+1)) ;
                    DatePickerFragment.this.setDay(String.valueOf(view.getDayOfMonth()));*/
                }
            };

    public String getYear() {
        return String.valueOf(year);
    }

    public String getMonth() {
        return String.valueOf(month);
    }

    public String getDay() {
        return String.valueOf(day);
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
