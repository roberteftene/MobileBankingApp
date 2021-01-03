package com.example.innovativebanking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.R;
import com.example.innovativebanking.adapters.TransactionAdapter;
import com.example.innovativebanking.database.AppDatabase;
import com.example.innovativebanking.database.UserTransactions;
import com.example.innovativebanking.models.SendModel;
import com.example.innovativebanking.models.TransactionModel;
import com.example.innovativebanking.models.UserModel;
import com.example.innovativebanking.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TransactionModel> transactions = new ArrayList<>();
    private ListView transactionsList;
    private UserModel mockUser;
    private SendModel mockSend;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button sendMoneyBtn, infoBtn, paymentsBtn, sendTransactions, addMoney, investBtn;
    private TextView userGreet, userBalance;
    TransactionModel transactionModel1;
    TransactionModel transactionModel2;
    TransactionModel transactionModel3;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_main);

//        initMocks();
        findViews();

        Utils utils = new Utils(this);
        final AppDatabase appDatabase = AppDatabase.getInstance(this);
        UserModel currUser = appDatabase.userDAO().getUserById(utils.getCurrentUserId());
        userGreet.setText("Hello " + currUser.getFirstName());
        userBalance.setText("Your balance is " + currUser.getBalance());

        List<UserTransactions> loggedUserTransactions = appDatabase.userDAO().getAllTransactionsByUserId();
        for (UserTransactions loggedUserTransaction : loggedUserTransactions) {
            if (loggedUserTransaction.userModel.getUserId() == utils.getCurrentUserId()) {
                transactions.addAll(loggedUserTransaction.transactionModelList);
            }
        }

        TransactionAdapter transactionAdapter = new TransactionAdapter(this, transactions);
//        ArrayAdapter<TransactionModel> arrayAdapter = new ArrayAdapter<TransactionModel>(this, android.R.layout.simple_list_item_1, transactions) {
//            @Override
//            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//                TextView tv = view.findViewById(android.R.id.text1);
//                tv.setTextColor(getResources().getColor(R.color.colorPrimary));
//                return view;
//            }
//        };
        transactionsList.setAdapter(transactionAdapter);

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

        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMoneyActivity.class);
                startActivity(intent);
            }
        });

        investBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InvestActivity.class);
                startActivity(intent);
            }
        });



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
//        TransactionModel transactionModel = new TransactionModel(value, new Date(), name, 1L, "imageEx");
//        transactions.add(transactionModel);

    }

    public void initMocks(int currUserId) {
        Date date = new Date();
        transactionModel1 = new TransactionModel(35, date.toString(), "MegaImage", currUserId);
        transactionModel2 = new TransactionModel(200, date.toString(), "Carrefour", currUserId);
        transactionModel3 = new TransactionModel(150, date.toString(), "Zara", currUserId);
        transactions.add(transactionModel1);
        transactions.add(transactionModel2);
        transactions.add(transactionModel3);

    }

    public void findViews() {
        transactionsList = findViewById(R.id.transactionList);
        userGreet = findViewById(R.id.userGreet);
        userBalance = findViewById(R.id.userBalance);
        sendMoneyBtn = findViewById(R.id.sendMoneyBtn);
        addMoney = findViewById(R.id.addMoney);
        paymentsBtn = findViewById(R.id.paymentsBtn);
        infoBtn = findViewById(R.id.infoBtn);
        sendTransactions = findViewById(R.id.sendTransactions);
        investBtn = findViewById(R.id.investBtn);
    }
}