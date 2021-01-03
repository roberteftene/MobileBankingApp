package com.example.innovativebanking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.innovativebanking.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SendMoneyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendMoneyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText nameInput;
    private EditText valueInput;
    private EditText accountInput;
    private CheckBox isPartenerInput;
    private Button submitSend;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SendMoneyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SendMoneyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SendMoneyFragment newInstance(String param1, String param2) {
        SendMoneyFragment fragment = new SendMoneyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_send_money, container, false);
        getActivity().findViewById(R.id.addManually).setVisibility(View.INVISIBLE);
        getInputFields(v);
        submitSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;
                if (nameInput.getText().toString().length() < 4) {
                    valid = false;
                    Toast.makeText(getActivity(), "Invalid name", Toast.LENGTH_SHORT).show();
                }
                if (!nameInput.getText().toString().matches("[a-zA-Z]+")) {
                    valid = false;
                    Toast.makeText(getActivity(), "The name should contain only letters", Toast.LENGTH_SHORT).show();
                }
                if (valueInput.getText().toString().equals("")) {
                    valid = false;
                    Toast.makeText(getActivity(), "Enter a value", Toast.LENGTH_SHORT).show();
                }
                if (accountInput.getText().toString().equals("")) {
                    valid = false;
                    Toast.makeText(getActivity(), "Enter an account", Toast.LENGTH_SHORT).show();
                }
                if (valid) {
                    Toast.makeText(getActivity(), "Succes", Toast.LENGTH_SHORT).show();
                    Bundle result = new Bundle();
                    result.putString("bundleKey", nameInput.getText().toString());
                    result.putString("accountKey", accountInput.getText().toString());
                    if (isPartenerInput.isChecked()) {
                        getParentFragmentManager().setFragmentResult("key", result);
                    }
                    nameInput.setText("");
                    accountInput.setText("");
                    valueInput.setText("");
                }
            }
        });
        return v;

    }

    public void getInputFields(View v) {
        submitSend = v.findViewById(R.id.sendMoneyBtn);
        nameInput = v.findViewById(R.id.nameInput);
        valueInput = v.findViewById(R.id.valueInput);
        accountInput = v.findViewById(R.id.accountInput);
        isPartenerInput = v.findViewById(R.id.isPartener);
    }

}