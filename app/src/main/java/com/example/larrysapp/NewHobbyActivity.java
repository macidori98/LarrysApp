package com.example.larrysapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewHobbyActivity extends AppCompatActivity {

    private Button btnAddHobby, btnViewHobbies;
    private EditText etNewHobby;

    private HobbiesAdapter mAdapter;
    private List<Hobby> hobbiesList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noHobbiesView;

    private String list = "info.androidhive.sqlite";

    private DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hobbies);

        final Intent intent = getPackageManager().getLaunchIntentForPackage(list);//new Intent(this, MainActivity.class);

        db = new DatabaseHelper(this);

        btnAddHobby = findViewById(R.id.btn_addHobby);
        btnViewHobbies = findViewById(R.id.btn_viewHobbies);
        etNewHobby = findViewById(R.id.et_hobbyName);

        btnAddHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createHobbyNewHobby(etNewHobby.getText().toString());
            }
        });

        btnViewHobbies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }


    private void createHobbyNewHobby(String note) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertHobby(note);

    }

}
