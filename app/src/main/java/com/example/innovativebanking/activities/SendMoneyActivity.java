package com.example.innovativebanking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.R;
import com.example.innovativebanking.models.SendModel;

public class SendMoneyActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText valueInput;
    private EditText accountInput;
    private SendModel sendModel;
    private Button submitSend;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_send_money);
        findViews();
        submitSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                float value = Float.parseFloat(valueInput.getText().toString());
                String account = accountInput.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("param1", name);
                intent.putExtra("param2", value);
                intent.putExtra("param3", account);
                startActivity(intent);
            }
        });

    }

    public void findViews() {
        nameInput = findViewById(R.id.nameInput);
        valueInput = findViewById(R.id.valueInput);
        accountInput = findViewById(R.id.accountInput);
        submitSend = findViewById(R.id.sendMoneyBtn);
    }
}