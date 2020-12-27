package com.example.innovativebanking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.R;

public class AddCardActivity extends AppCompatActivity {

    Spinner yearsArray;
    Spinner monthsArray;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_add_card);
        yearsArray = findViewById(R.id.expiry_years_array);
        monthsArray = findViewById(R.id.expiry_days_array);
        ArrayAdapter<CharSequence> yearsAdapter = ArrayAdapter.createFromResource(this, R.array.expiry_years_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> monthsAdapter = ArrayAdapter.createFromResource(this, R.array.expiry_months_array, android.R.layout.simple_spinner_dropdown_item);
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearsArray.setAdapter(yearsAdapter);
        monthsArray.setAdapter(monthsAdapter);
    }
}