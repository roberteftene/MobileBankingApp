package com.example.innovativebanking.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.Adapters.TransactionsAdapter;
import com.example.innovativebanking.R;
import com.example.innovativebanking.models.TransactionModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentsActivity extends AppCompatActivity implements TransactionsAdapter.TransactionAdapterInteraction {

    ArrayAdapter<String> listAdapter;
    Map<Long, TransactionModel> transactionModelMap = new HashMap<>();
    private GridView gridView;
    private ListView listView;
    private List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        //CUSTOM ADAPTER EXAMPLE
        listView = findViewById(R.id.lvItems);
        gridView = findViewById(R.id.transactionGridView);
        initMocks();

        TransactionsAdapter transactionsAdapter = new TransactionsAdapter(this, transactionModelMap);
        gridView.setAdapter(transactionsAdapter);

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, items);
        listView.setAdapter(listAdapter);


    }

    @Override
    public void onAdapterItemClick(String value) {
        if (items.contains(value)) {
            items.remove(value);
        } else {
            items.add(value);
        }
        listAdapter.notifyDataSetChanged();
    }

    public void initMocks() {
        TransactionModel t1 = new TransactionModel(300, new Date(), "Shop", 1L, "http://pdm.ase.ro/images/tehnologii.png");
        TransactionModel t2 = new TransactionModel(600, new Date(), "MegaImage", 2L, "http://pdm.ase.ro/images/tehnologii.png");
        TransactionModel t3 = new TransactionModel(500, new Date(), "MegaImage", 3L, "http://pdm.ase.ro/images/tehnologii.png");
        TransactionModel t4 = new TransactionModel(200, new Date(), "MegaImage", 4L, "http://pdm.ase.ro/images/tehnologii.png");
        TransactionModel t5 = new TransactionModel(100, new Date(), "MegaImage", 5L, "http://pdm.ase.ro/images/tehnologii.png");
        TransactionModel t6 = new TransactionModel(600, new Date(), "MegaImage", 6L, "http://pdm.ase.ro/images/tehnologii.png");
        TransactionModel t7 = new TransactionModel(900, new Date(), "MegaImage", 7L, "http://pdm.ase.ro/images/tehnologii.png");

        transactionModelMap.put(t1.getId(), t1);
        transactionModelMap.put(t2.getId(), t2);
        transactionModelMap.put(t3.getId(), t3);
        transactionModelMap.put(t4.getId(), t4);
        transactionModelMap.put(t5.getId(), t5);
        transactionModelMap.put(t6.getId(), t6);
        transactionModelMap.put(t7.getId(), t7);
    }
}