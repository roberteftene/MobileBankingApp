package com.example.innovativebanking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.innovativebanking.UploadAsync;
import com.example.innovativebanking.models.SendModel;
import com.example.innovativebanking.models.TransactionModel;
import com.example.innovativebanking.models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TransactionModel> transactions = new ArrayList<>();
    private ListView transactionsList;
    private UserModel mockUser;
    private SendModel mockSend;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button sendMoneyBtn, infoBtn, paymentsBtn, sendTransactions;
    private TextView userGreet, userBalance;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_main);
        initMocks();
        findViews();


        paymentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaymentsActivity.class);
                startActivity(intent);
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

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
        mockSend = new SendModel(name, value, account, false);
        TransactionModel transactionModel = new TransactionModel(value, new Date(), name, 1L, "imageEx");
        transactions.add(transactionModel);

        sendTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject postData1 = null;
                JSONObject jsonLibrary = new JSONObject();
                JSONArray jsonArray = new JSONArray();

                try {
                    for (int i = 0; i < transactions.size(); i++) {
                        postData1 = new JSONObject();
                        postData1.put("name", transactions.get(i).getName());
                        postData1.put("value", transactions.get(i).getMoney());
                        postData1.put("date", transactions.get(i).getDate());
                    }
//                    postData.put("name",transactions.get(0).getName());
//                    postData.put("value",transactions.get(0).getMoney());
//                    postData.put("date",transactions.get(0).getDate());
//
//                    postData.put("name",transactions.get(1).getName());
//                    postData.put("value",transactions.get(1).getMoney());
//                    postData.put("date",transactions.get(1).getDate());
//
//                    postData.put("name",transactions.get(2).getName());
//                    postData.put("value",transactions.get(2).getMoney());
//                    postData.put("date",transactions.get(2).getDate());
//                    postData.put("name","megaimage");
//                    postData.put("value","200");
//                    postData.put("date","10.10.2020");
                    jsonArray.put(postData1);
                    jsonLibrary.put("EfteneRobert", jsonArray);
                    Log.d(TAG, "\n Transaction: " + postData1.toString());
                    new UploadAsync().execute("http://167.99.143.42/upload", jsonLibrary.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void initMocks() {
        Date date = new Date();
        TransactionModel transactionModel1 = new TransactionModel(37.5f, date, "MegaImage", 1L, "imageEx");
        TransactionModel transactionModel2 = new TransactionModel(100, date, "Carrefour", 2L, "imageEx");
        TransactionModel transactionModel3 = new TransactionModel(250, date, "Zara", 3L, "imageEx");
        TransactionModel transactionModel4 = new TransactionModel(30, date, "MegaImage", 4L, "imageEx");
        TransactionModel transactionModel5 = new TransactionModel(22, date, "Immedio", 5L, "imageEx");
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
        paymentsBtn = findViewById(R.id.paymentsBtn);
        infoBtn = findViewById(R.id.infoBtn);
        sendTransactions = findViewById(R.id.sendTransactions);

    }
}