package com.example.innovativebanking.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.innovativebanking.R;
import com.example.innovativebanking.activities.MainActivity;
import com.example.innovativebanking.activities.UpdateVaultActivity;
import com.example.innovativebanking.database.AppDatabase;
import com.example.innovativebanking.models.UserModel;
import com.example.innovativebanking.models.VaultModel;
import com.example.innovativebanking.utils.Utils;

import java.util.ArrayList;


public class VaultAdapter extends ArrayAdapter<VaultModel> {

    Context context;
    private ArrayList<VaultModel> dataSet;

    public VaultAdapter(@NonNull Context context, ArrayList<VaultModel> dataSet) {
        super(context, R.layout.vault_lv_item, dataSet);
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Nullable
    @Override
    public VaultModel getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getPosition(@Nullable VaultModel item) {
        int pos = 0;
        for (int i = 0; i < dataSet.size(); i++) {
            if (dataSet.get(i).equals(item)) {
                pos = i;
            }
        }
        return pos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final VaultModel vaultModel = getItem(position);
        final AppDatabase appDatabase = AppDatabase.getInstance(getContext());
        Utils utils = new Utils(getContext());
        final UserModel userModel = appDatabase.userDAO().getUserById(utils.getCurrentUserId());

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.vault_lv_item, parent, false);
            viewHolder.vaultNameTxt = convertView.findViewById(R.id.vaultName);
            viewHolder.vaultDescriptionTxt = convertView.findViewById(R.id.vaultDescriptionTxt);
            viewHolder.vaultStatus = convertView.findViewById(R.id.vaultStatus);
            viewHolder.addMoneyBtn = convertView.findViewById(R.id.addMoneyVaultBtn);
            viewHolder.closeVaultBtn = convertView.findViewById(R.id.closeVaultBtn);
            viewHolder.editVaultBtn = convertView.findViewById(R.id.editVaultBtn);
            viewHolder.withdrawMoneyBtn = convertView.findViewById(R.id.withdrawVaultBtn);
            viewHolder.vaultProgress = convertView.findViewById(R.id.progressBar);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.vaultNameTxt.setText(vaultModel.getVaultName());
        viewHolder.vaultDescriptionTxt.setText(vaultModel.getVaultDescription());
        viewHolder.vaultProgress.setMax((int) vaultModel.getVaultTarget());
        viewHolder.vaultProgress.setProgress((int) vaultModel.getVaultBalance());
        viewHolder.vaultStatus.setText("You have " + vaultModel.getVaultBalance() + " RON out of " + vaultModel.getVaultTarget() + " RON");

        final String[] dialogInputText = new String[1];
        viewHolder.addMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("GREAT!");
                final EditText moneyToAdd = new EditText(getContext());
                moneyToAdd.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(moneyToAdd);

                builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean valid = true;
                        if (Float.parseFloat(moneyToAdd.getText().toString()) > userModel.getBalance()) {
                            valid = false;
                            Toast.makeText(getContext(), "You dont have enough money", Toast.LENGTH_SHORT).show();
                        }
                        if (valid) {
                            dialogInputText[0] = moneyToAdd.getText().toString();
                            float vaultPreviousBalance = vaultModel.getVaultBalance();
                            float previousUserBalance = userModel.getBalance();
                            userModel.setBalance(previousUserBalance - Float.parseFloat(dialogInputText[0]));
                            vaultModel.setVaultBalance(vaultPreviousBalance + Integer.parseInt(dialogInputText[0]));
                            appDatabase.vaultDAO().updateVault(vaultModel);
                            appDatabase.userDAO().updateUser(userModel);
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            getContext().startActivity(intent);
                            ((Activity) getContext()).finish();
                        }
                    }
                });

                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        viewHolder.withdrawMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Write the value below");
                final EditText moneyToDraw = new EditText(getContext());
                moneyToDraw.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(moneyToDraw);

                builder.setPositiveButton("WITHDRAW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean valid = true;
                        if (Float.parseFloat(moneyToDraw.getText().toString()) > vaultModel.getVaultBalance()) {
                            valid = false;
                            Toast.makeText(getContext(), "You dont have enough money in vault", Toast.LENGTH_SHORT).show();
                        }
                        if (valid) {
                            dialogInputText[0] = moneyToDraw.getText().toString();
                            float vaultPreviousBalance = vaultModel.getVaultBalance();
                            vaultModel.setVaultBalance(vaultPreviousBalance - Integer.parseInt(dialogInputText[0]));
                            float userPreviousBalance = userModel.getBalance();
                            userModel.setBalance(userPreviousBalance + Float.parseFloat(dialogInputText[0]));
                            appDatabase.vaultDAO().updateVault(vaultModel);
                            appDatabase.userDAO().updateUser(userModel);
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            getContext().startActivity(intent);
                            ((Activity) getContext()).finish();
                        }
                    }
                });

                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        viewHolder.closeVaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure you want to close this vault");
                builder.setTitle("Think twice!");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                float userPreviousBalance = userModel.getBalance();
                                if (vaultModel.getVaultBalance() > 0) {
                                    userModel.setBalance(vaultModel.getVaultBalance() + userPreviousBalance);
                                }
                                appDatabase.userDAO().updateUser(userModel);
                                appDatabase.vaultDAO().deleteVault(vaultModel);
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                getContext().startActivity(intent);
                                ((Activity) getContext()).finish();
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

        viewHolder.editVaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateVaultActivity.class);
                intent.putExtra("vaultId", Integer.toString(vaultModel.getVaultId()));
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });
        return convertView;

    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<VaultModel> getDataSet() {
        return dataSet;
    }

    public void setDataSet(ArrayList<VaultModel> dataSet) {
        this.dataSet = dataSet;
    }

    private static class ViewHolder {
        TextView vaultNameTxt, vaultDescriptionTxt, vaultStatus;
        Button addMoneyBtn, closeVaultBtn, withdrawMoneyBtn, editVaultBtn;
        ProgressBar vaultProgress;

    }
}
