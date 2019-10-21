package com.example.larrysapp;

import android.content.Context;
import android.content.Intent;
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

import java.util.StringTokenizer;

public class LoginActivity extends AppCompatActivity {
    public static final String MY_SHARED_PREFERENCES = "LoginData";
    private EditText et_name, et_email, et_password;
    private CheckBox chkBox;
    private String chkbox_value;
    public TextView tv_birthDate;
    private String year, month, day, temp;
    private String[] date;
    private DatePickerFragment newFragment;
    private String list = "info.androidhive.sqlite";


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
            temp = tv_birthDate.getText().toString();
            date = temp.split(" ");
            year = date[0];
            month = date[1];
            day = date[2];
            //Toast.makeText(this, year, Toast.LENGTH_SHORT).show();
            chkBox.setChecked(true);
        }
    }

    public void showDatePicker(View v) {
        newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
        //Toast.makeText(this, newFragment.toString(), Toast.LENGTH_SHORT).show();
    }

    public void login(View v) {
        if (chkBox.isChecked()){
            SharedPreferences.Editor sharedPreferences = getSharedPreferences(MY_SHARED_PREFERENCES, MODE_PRIVATE).edit();
            sharedPreferences.putString("name", et_name.getText().toString());
            sharedPreferences.putString("email", et_email.getText().toString());
            sharedPreferences.putString("password", et_password.getText().toString());
            sharedPreferences.putString("date", tv_birthDate.getText().toString());
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
        final Intent intent = getPackageManager().getLaunchIntentForPackage(list);//new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
