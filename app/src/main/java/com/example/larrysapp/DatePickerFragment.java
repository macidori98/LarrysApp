package com.example.larrysapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

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

        DatePickerDialog dpd = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        /*LoginActivity la = new LoginActivity();
        la.tv_birthDate.setText(getYear() + " " + getMonth() + " " + getDay());*/
        //Toast.makeText(getContext(), getYear(), Toast.LENGTH_SHORT).show();
        return dpd;
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    LoginActivity la = (LoginActivity) getActivity();
                    la.tv_birthDate.setText(view.getYear() + " " + (view.getMonth()+1) + " " + view.getDayOfMonth());
                }
            };

    public String getYear() {
        return String.valueOf(year);
    }

}
