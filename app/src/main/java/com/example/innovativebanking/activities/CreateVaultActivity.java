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
import com.example.innovativebanking.database.AppDatabase;
import com.example.innovativebanking.models.VaultModel;
import com.example.innovativebanking.utils.Utils;

public class CreateVaultActivity extends AppCompatActivity {

    private EditText vaultNameTxt, vaultDescriptionTxt, vaultTargetTxt;
    private Button createVault;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_create_vault);
        final AppDatabase appDatabase = AppDatabase.getInstance(this);
        final Utils utils = new Utils(this);
        vaultNameTxt = findViewById(R.id.vaultInputNameTxt);
        vaultDescriptionTxt = findViewById(R.id.vaultInputDescriptionTxt);
        vaultTargetTxt = findViewById(R.id.vaultInputTargetTxt);
        createVault = findViewById(R.id.createVault);

        createVault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VaultModel vaultModel = new VaultModel(vaultNameTxt.getText().toString(), vaultDescriptionTxt.getText().toString(), Float.parseFloat(vaultTargetTxt.getText().toString()), 0, utils.getCurrentUserId());
                appDatabase.vaultDAO().addVault(vaultModel);
                Intent intent = new Intent(CreateVaultActivity.this, VaultsActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}