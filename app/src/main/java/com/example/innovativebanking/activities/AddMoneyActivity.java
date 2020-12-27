package com.example.innovativebanking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.R;

public class AddMoneyActivity extends AppCompatActivity {

    private Button addCard;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_add_money);
        addCard = findViewById(R.id.addNewCard);
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState == null) {
                    Intent intent = new Intent(AddMoneyActivity.this, AddCardActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}