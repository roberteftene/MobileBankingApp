package com.example.innovativebanking.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.innovativebanking.DownloadContent;
import com.example.innovativebanking.R;
import com.example.innovativebanking.models.TransactionModel;

import java.util.HashMap;
import java.util.Map;

public class TransactionsAdapter extends BaseAdapter {

    private static final String TAG = TransactionsAdapter.class.getSimpleName();

    Context context;
    Map<Long, TransactionModel> transactionList;
    TransactionAdapterInteraction adapterListener;
    private HashMap<Long, Boolean> itemState = new HashMap<>();

    public TransactionsAdapter(Context context, Map<Long, TransactionModel> transactionList) {
        this.transactionList = transactionList;
        this.context = context;
        this.adapterListener = (TransactionAdapterInteraction) context;

        for (Long key : transactionList.keySet()) {
            TransactionModel transaction = transactionList.get(key);
            itemState.put(transaction.getId(), transaction.isChecked());
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TransactionViewHolder holder = new TransactionViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.transaction_item, parent, false);
            holder.transactionImage = convertView.findViewById(R.id.transactionIamge);
            holder.name = convertView.findViewById(R.id.transactionName);
            holder.value = convertView.findViewById(R.id.value);
            holder.isChecked = convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);

        } else {
            holder = (TransactionViewHolder) convertView.getTag();
        }
        final TransactionModel transactionModel = transactionList.get(getItemId(position));

        final TransactionViewHolder finalHolder1 = holder;
        holder.isChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                itemState.put(transactionModel.getId(), isChecked);
                adapterListener.onAdapterItemClick(transactionModel.getName() + "-" + transactionModel.getMoney());
                if (finalHolder1.transactionImage != null) {
                    Log.d(TAG, "----------downloadImage method------------");
                    DownloadContent imageTask = new DownloadContent("http://pdm.ase.ro/images/tehnologii.png");
                    Thread downloadThread = new Thread(imageTask);
                    downloadThread.start();
                    Log.d(TAG, "----------download thread started------------");
                    DownloadContent.handler = new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            Log.d(TAG, "----------image received from thread------------");
                            Bundle data = msg.getData();
                            Bitmap image = data.getParcelable("image");
                            finalHolder1.transactionImage.setImageBitmap(image);

                        }
                    };
                }
            }
        });

        holder.name.setText(transactionModel.getName());
        holder.value.setText(transactionModel.getMoney() + " money");
        holder.isChecked.setChecked(itemState.get(transactionModel.getId()));
        return convertView;
    }

    @Override
    public int getCount() {
        return transactionList.size();
    }

    @Override
    public Object getItem(int position) {
        Object[] objects = transactionList.keySet().toArray();
        return transactionList.get(objects[position]);
    }

    @Override
    public long getItemId(int position) {
        Object[] objects = transactionList.keySet().toArray();
        return transactionList.get(objects[position]).getId();
    }

    public interface TransactionAdapterInteraction {
        void onAdapterItemClick(String value);
    }

    private static class TransactionViewHolder {
        public ImageView transactionImage;
        public TextView name, value;
        public CheckBox isChecked;
    }

}
