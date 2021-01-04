package com.example.innovativebanking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.R;
import com.example.innovativebanking.database.AppDatabase;
import com.example.innovativebanking.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Button register;
    List<UserModel> userModelList;
    private EditText mailAccount, passAccount;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_login);
        final AppDatabase appDatabase = AppDatabase.getInstance(this);
        mailAccount = findViewById(R.id.emailInputTxt);
        passAccount = findViewById(R.id.passwordInputLogin);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userModelList = new ArrayList<>();
                userModelList.addAll(appDatabase.userDAO().getAll());

                for (int i = 0; i < userModelList.size(); i++) {
                    if ((mailAccount.getText().toString()).equals(userModelList.get(i).getEmail()) && (passAccount.getText().toString()).equals(userModelList.get(i).getPassword())) {
                        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("currentUserID", userModelList.get(i).getUserId());
                        editor.apply();
                        Log.v("Session", "ID:" + userModelList.get(i).getUserId());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        mailAccount.setText("");
                        passAccount.setText("");
                        break;
                    } else {
                        Toast.makeText(LoginActivity.this, "Email/Password incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}