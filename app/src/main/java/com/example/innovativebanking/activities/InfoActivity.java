package com.example.innovativebanking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.R;
import com.example.innovativebanking.database.AppDatabase;
import com.example.innovativebanking.models.UserModel;
import com.example.innovativebanking.utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button updateAcount, closeAccount, logOut, setTargetBtn;
    GoogleMap map;
    private String accountBalanceTarget;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_info);
        final AppDatabase appDatabase = AppDatabase.getInstance(this);
        final Utils utils = new Utils(this);
        final UserModel currentUser = appDatabase.userDAO().getUserById(utils.getCurrentUserId());
        updateAcount = findViewById(R.id.updateUser);
        closeAccount = findViewById(R.id.deleteUser);
        logOut = findViewById(R.id.logOut);

        updateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this,UpdateUserDetails.class);
                startActivity(intent);
                finish();
            }
        });

        closeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setMessage("Are you sure you want to close your account?");
                builder.setTitle("Think twice!");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                appDatabase.userDAO().deleteUser(currentUser);
                                Intent intent = new Intent(InfoActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        setTargetBtn = findViewById(R.id.setBalanceTargetBtn);
        setTargetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder targetDialog = new AlertDialog.Builder(InfoActivity.this);
                targetDialog.setTitle("Set a new target balance!");
                final EditText targetValue = new EditText(InfoActivity.this);
                targetValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                targetDialog.setView(targetValue);

                targetDialog.setPositiveButton("SET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        accountBalanceTarget = targetValue.getText().toString();
                        currentUser.setBalanceTarget(Integer.parseInt(accountBalanceTarget));
                        appDatabase.userDAO().updateUser(currentUser);
                        Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

                targetDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ;
                    }
                });
                targetDialog.show();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng lat = new LatLng(44.41890470076146, 26.116029954557366);
        map.addMarker(new MarkerOptions().position(lat).title("Bank Office"));
        map.setMinZoomPreference(15);
        map.moveCamera(CameraUpdateFactory.newLatLng(lat));
    }
}