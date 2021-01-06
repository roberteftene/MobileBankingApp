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

public class UpdateVaultActivity extends AppCompatActivity {

    private EditText vaultNameTxt, vaultDescriptionTxt, vaultTargetTxt;
    private Button updateVault;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_update_vault);

        final AppDatabase appDatabase = AppDatabase.getInstance(this);

        vaultNameTxt = findViewById(R.id.vaultInputNameTxt);
        vaultDescriptionTxt = findViewById(R.id.vaultInputDescriptionTxt);
        vaultTargetTxt = findViewById(R.id.vaultInputTargetTxt);
        updateVault = findViewById(R.id.updateVault);

        String vaultId = getIntent().getStringExtra("vaultId");
        int vaultIdInt = Integer.parseInt(vaultId);
        final VaultModel vaultModel = appDatabase.vaultDAO().getVaultById(vaultIdInt);

        vaultNameTxt.setText(vaultModel.getVaultName());
        vaultDescriptionTxt.setText(vaultModel.getVaultDescription());
        vaultTargetTxt.setText(Float.toString(vaultModel.getVaultTarget()));

        updateVault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaultModel.setVaultTarget(Float.parseFloat(vaultTargetTxt.getText().toString()));
                vaultModel.setVaultDescription(vaultDescriptionTxt.getText().toString());
                vaultModel.setVaultName(vaultNameTxt.getText().toString());
                appDatabase.vaultDAO().updateVault(vaultModel);
                Intent intent = new Intent(UpdateVaultActivity.this, VaultsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}