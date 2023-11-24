package com.example.mymenu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    Intent in;
    DatabaseHelper myDb;
    EditText name, surname, email, etId, etPhone;
    Button btnInsert,btnView,btnUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        in = getIntent();

        myDb = new DatabaseHelper(this);

        name = findViewById(R.id.etName);
        surname = findViewById(R.id.etSure);
        etId = findViewById(R.id.etId);
        email = findViewById(R.id.etMail);
        etPhone = findViewById(R.id.etPhone);

        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(
                        etId.getText().toString(),
                        name.getText().toString(),
                        surname.getText().toString(),
                        email.getText().toString(),
                        etPhone.getText().toString());

                if (isInserted == true)
                    Toast.makeText(SignupActivity.this, "Data Inserted",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SignupActivity.this, "Data not Inserted",
                            Toast.LENGTH_LONG).show();
                etId.setText("");
                name.setText("");
                surname.setText("");
                email.setText("");
                etPhone.setText("");

            }
        });

        btnView = findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    //show message
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

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                boolean isUpdated = myDb.updateData(
                        etId.getText().toString(),
                        name.getText().toString(),
                        surname.getText().toString(),
                        email.getText().toString(),
                        etPhone.getText().toString());
                if (isUpdated == true) {
                    Toast.makeText(SignupActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                }
            }

        });

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myDb.deleteData(etId.getText().toString()) > 0) {
                    Toast.makeText(SignupActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void showData(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
