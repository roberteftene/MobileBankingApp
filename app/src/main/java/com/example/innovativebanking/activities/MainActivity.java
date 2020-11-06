package com.example.innovativebanking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.R;
import com.example.innovativebanking.models.SendModel;
import com.example.innovativebanking.models.TransactionModel;
import com.example.innovativebanking.models.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TransactionModel> transactions = new ArrayList<>();
    private ListView transactionsList;
    private UserModel mockUser;
    private TextView userGreet;
    private TextView userBalance;
    private SendModel mockSend;
    private Button sendMoneyBtn;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_main);
        initMocks();
        findViews();
        userGreet.setText("Hello " + mockUser.getUserName());
        userBalance.setText("Your balance is " + mockUser.getBalance());

        //TODO make a separat package for adapters
        ArrayAdapter<TransactionModel> arrayAdapter = new ArrayAdapter<TransactionModel>(this, android.R.layout.simple_list_item_1, transactions) {
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                return view;
            }
        };
        transactionsList.setAdapter(arrayAdapter);

        sendMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendMoneyActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String name = intent.getStringExtra("param1");
        float value = intent.getFloatExtra("param2", 20);
        String account = intent.getStringExtra("param3");
        mockSend = new SendModel(name, value, account);
        TransactionModel transactionModel = new TransactionModel(value, new Date(), name);
        transactions.add(transactionModel);

    }

    public void initMocks() {
        Date date = new Date();
        TransactionModel transactionModel1 = new TransactionModel(37.5f, date, "MegaImage");
        TransactionModel transactionModel2 = new TransactionModel(100, date, "Carrefour");
        TransactionModel transactionModel3 = new TransactionModel(250, date, "Zara");
        TransactionModel transactionModel4 = new TransactionModel(30, date, "MegaImage");
        TransactionModel transactionModel5 = new TransactionModel(22, date, "Immedio");
        transactions.add(transactionModel1);
        transactions.add(transactionModel2);
        transactions.add(transactionModel3);
        transactions.add(transactionModel4);
        transactions.add(transactionModel5);

        mockUser = new UserModel(530, "Robert Eftene");
    }

    public void findViews() {

        transactionsList = findViewById(R.id.transactionList);
        userGreet = findViewById(R.id.userGreet);
        userBalance = findViewById(R.id.userBalance);
        sendMoneyBtn = findViewById(R.id.sendMoneyBtn);

    }
}