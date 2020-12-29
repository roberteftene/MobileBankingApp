package com.example.innovativebanking.adapters;

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

    public CreditCardAdapter(Context context, ArrayList<CreditCardModel> dataSet) {
        super(context, R.layout.credit_card_lv_item, dataSet);
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public CreditCardModel getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CreditCardModel creditCardModel = getItem(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder = new ViewHolder();
//        View result;

        if (convertView == null) {
//            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.credit_card_lv_item, parent, false);
            viewHolder.cardPersonNameTxt = convertView.findViewById(R.id.cardPersonNameLvItem);
            viewHolder.cardNumberTxt = convertView.findViewById(R.id.cardNumberLvItem);
            viewHolder.cardIcon = convertView.findViewById(R.id.cardIcon);
            viewHolder.changeCardDetailsBtn = convertView.findViewById(R.id.changeCardDetailsBtn);
            viewHolder.selectCardCheckBox = convertView.findViewById(R.id.cardCheckbox);

//            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
//            result = convertView;
        }
        viewHolder.cardPersonNameTxt.setText(creditCardModel.getCardPersonName());

        String creditNumber = Long.toString(creditCardModel.getCardNumber());
        StringBuffer stringBuffer = new StringBuffer(creditNumber);
        int pos = 4;
        for (int i = 0; i < 3; i++) {
            stringBuffer.insert(pos, '-');
            pos += 5;
        }
        viewHolder.cardNumberTxt.setText(stringBuffer);
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class ViewHolder {
        TextView cardPersonNameTxt;
        TextView cardNumberTxt;
        Button changeCardDetailsBtn;
        CheckBox selectCardCheckBox;
        ImageView cardIcon;
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


}
