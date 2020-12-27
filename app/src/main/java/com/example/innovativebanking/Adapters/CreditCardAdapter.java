package com.example.innovativebanking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.innovativebanking.R;
import com.example.innovativebanking.models.CreditCardModel;

import java.util.ArrayList;
import java.util.List;

public class CreditCardAdapter extends ArrayAdapter<CreditCardModel> {

    Context context;
    private ArrayList<CreditCardModel> dataSet;

    public CreditCardAdapter(ArrayList<CreditCardModel> dataSet, Context context) {
        super(context, R.layout.credit_card_lv_item, dataSet);
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CreditCardModel getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CreditCardModel creditCardModel = getItem(position);
        ViewHolder viewHolder;
        View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.credit_card_lv_item, parent, false);
            viewHolder.cardPersonNameTxt = convertView.findViewById(R.id.cardPersonName);
            viewHolder.cardNumberTxt = convertView.findViewById(R.id.cardNumber);
            viewHolder.cardIcon = convertView.findViewById(R.id.cardIcon);
            viewHolder.changeCardDetailsBtn = convertView.findViewById(R.id.changeCardDetailsBtn);
            viewHolder.selectCardCheckBox = convertView.findViewById(R.id.cardCheckbox);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.cardPersonNameTxt.setText(creditCardModel.getCardPersonName());
        viewHolder.cardNumberTxt.setText((int) creditCardModel.getCardNumber());
        return convertView;
    }

    public List<CreditCardModel> getDataSet() {
        return dataSet;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private static class ViewHolder {
        TextView cardPersonNameTxt;
        TextView cardNumberTxt;
        Button changeCardDetailsBtn;
        CheckBox selectCardCheckBox;
        ImageView cardIcon;
    }
}
