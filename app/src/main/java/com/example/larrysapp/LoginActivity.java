package com.example.larrysapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class LoginActivity extends AppCompatActivity {
    public static final String MY_SHARED_PREFERENCES = "LoginData";
    EditText et_name, et_email, et_password;
    CheckBox chkBox;
    String chkbox_value;
    TextView tv_birthDate;
    String year, month, day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        chkBox = findViewById(R.id.chk_rememberMe);
        tv_birthDate = findViewById(R.id.tv_birthDate);

        SharedPreferences sharedPreferences = getSharedPreferences(MY_SHARED_PREFERENCES, MODE_PRIVATE);
        chkbox_value = sharedPreferences.getString("rememberMe","nem");

        if (chkbox_value.contentEquals("igen")){
            et_name.setText(sharedPreferences.getString("name","name"));
            et_email.setText(sharedPreferences.getString("email", "email"));
            et_password.setText(sharedPreferences.getString("password", "password"));
            tv_birthDate.setText(sharedPreferences.getString("date", "birth"));
            chkBox.setChecked(true);
        }
    }

    public void showDatePicker(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
        year = newFragment.getYear();
        month = newFragment.getMonth();
        day = newFragment.getDay();
        tv_birthDate = findViewById(R.id.tv_birthDate);
        tv_birthDate.setText("dsfg");
    }

    public void login(View v) {
        if (chkBox.isChecked()){
            SharedPreferences.Editor sharedPreferences = getSharedPreferences(MY_SHARED_PREFERENCES, MODE_PRIVATE).edit();
            sharedPreferences.putString("name", et_name.getText().toString());
            sharedPreferences.putString("email", et_email.getText().toString());
            sharedPreferences.putString("password", et_password.getText().toString());
            sharedPreferences.putString("date", year + " " + month + " " + day);
            sharedPreferences.putString("rememberMe", "igen");
            sharedPreferences.apply();
        } else {
            SharedPreferences.Editor sharedPreferences = getSharedPreferences(MY_SHARED_PREFERENCES, MODE_PRIVATE).edit();
            sharedPreferences.putString("name", "");
            sharedPreferences.putString("email", "");
            sharedPreferences.putString("password", "");
            sharedPreferences.putString("date", "");
            sharedPreferences.putString("rememberMe", "nem");
            sharedPreferences.apply();
        }
    }
}
