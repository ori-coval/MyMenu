package com.example.mymenu.SignUpSignIn;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.mymenu.DatabaseHelper;
import com.example.mymenu.R;

public class SignUpFragment extends Fragment {
    DatabaseHelper myDb;
    EditText name, surname, email, etId, etPhone;
    Button btnInsert, btnView, btnUpdate, btnDelete;

    public SignUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sign_up, container, false);

        myDb = new DatabaseHelper(getActivity());

        name = view.findViewById(R.id.etName);
        surname = view.findViewById(R.id.etSure);
        etId = view.findViewById(R.id.etId);
        email = view.findViewById(R.id.etMail);
        etPhone = view.findViewById(R.id.etPhone);

        btnInsert = view.findViewById(R.id.btnInsert);
        btnView = view.findViewById(R.id.btnView);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnDelete = view.findViewById(R.id.btnDelete);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(
                        etId.getText().toString(),
                        name.getText().toString(),
                        surname.getText().toString(),
                        email.getText().toString(),
                        etPhone.getText().toString());

                if (isInserted) {
                    Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Data not Inserted", Toast.LENGTH_LONG).show();
                }

                etId.setText("");
                name.setText("");
                surname.setText("");
                email.setText("");
                etPhone.setText("");
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showData("Error", "No Data Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("NAME: " + res.getString(1) + "\n");
                    buffer.append("SURNAME: " + res.getString(2) + "\n");
                    buffer.append("EMAIL: " + res.getString(3) + "\n");
                    buffer.append("PHONE_num: " + res.getString(4) + "\n\n");
                }
                showData("Data", buffer.toString());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDb.updateData(
                        etId.getText().toString(),
                        name.getText().toString(),
                        surname.getText().toString(),
                        email.getText().toString(),
                        etPhone.getText().toString());
                if (isUpdated) {
                    Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Data Not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myDb.deleteData(etId.getText().toString()) > 0) {
                    Toast.makeText(getActivity(), "Data Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Data Not Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    public void showData(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
