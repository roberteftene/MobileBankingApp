package com.example.innovativebanking.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.Adapters.CreditCardAdapter;
import com.example.innovativebanking.R;
import com.example.innovativebanking.models.CreditCardModel;

import java.util.ArrayList;

public class AddMoneyActivity extends AppCompatActivity {

    private Button addCard;
    ArrayList<CreditCardModel> creditCardModelList;
    ListView creditCardsListView;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        setContentView(R.layout.activity_add_money);

        creditCardsListView = findViewById(R.id.savedCreditCardsLv);
        creditCardModelList = new ArrayList<>();
        creditCardModelList.add(new CreditCardModel(1234546787922231L, "Eftene Robert", 333, "20-10-2028"));
        creditCardModelList.add(new CreditCardModel(1234546787922231L, "Amalia Cornea", 234, "12-12-2025"));
        CreditCardAdapter creditCardAdapter = new CreditCardAdapter(this, creditCardModelList);
        creditCardsListView.setAdapter(creditCardAdapter);
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