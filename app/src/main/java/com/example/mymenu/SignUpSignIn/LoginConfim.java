package com.example.mymenu.SignUpSignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mymenu.DataSendActivity;
import com.example.mymenu.R;

public class LoginConfim extends AppCompatActivity {

    private Button dataSend;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_confim);

        dataSend = findViewById(R.id.btnDataSend);
        dataSend.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginConfim.this, DataSendActivity.class);
                startActivity(intent);
            }
        });
    }
}