package com.example.innovativebanking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.R;
import com.example.innovativebanking.adapters.VaultAdapter;
import com.example.innovativebanking.database.AppDatabase;
import com.example.innovativebanking.models.VaultModel;
import com.example.innovativebanking.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class VaultsActivity extends AppCompatActivity {

    private ListView vaultsListView;
    private ArrayList<VaultModel> vaults;
    private Button createVaultBtn;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_vaults);
        AppDatabase appDatabase = AppDatabase.getInstance(this);
        Utils utils = new Utils(this);
        List<VaultModel> vaultModelList = appDatabase.vaultDAO().getAllVaultsByUserId(utils.getCurrentUserId());
        vaultsListView = findViewById(R.id.vaultsLV);
        vaults = new ArrayList<>();
        vaults.addAll(vaultModelList);
        final VaultAdapter vaultAdapter = new VaultAdapter(this, vaults);
        vaultsListView.setAdapter(vaultAdapter);

        createVaultBtn = findViewById(R.id.createVaultBtn);
        createVaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaultsActivity.this, CreateVaultActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}