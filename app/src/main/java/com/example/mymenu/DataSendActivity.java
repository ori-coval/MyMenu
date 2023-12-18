package com.example.mymenu;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class DataSendActivity extends AppCompatActivity {


    private Button selectFileButton;
    private Button viaBluetoothButton;
    private Button viaNfcButton;
    private Button sendFileButton;
    private Button cancelButton;
    private ProgressBar progressBar;
    Context context;

    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_send);

        context = getApplicationContext();

        // Initialize UI elements
        selectFileButton = findViewById(R.id.select_file_button);
        viaBluetoothButton = findViewById(R.id.via_bluetooth_button);
        viaNfcButton = findViewById(R.id.via_nfc_button);
        sendFileButton = findViewById(R.id.send_file_button);
        cancelButton = findViewById(R.id.cancel_button);
        progressBar = findViewById(R.id.progress_bar);

        // Set initial button states
        sendFileButton.setEnabled(false);
        bluetoothManager = getSystemService(BluetoothManager.class);
        bluetoothAdapter = bluetoothManager.getAdapter();

        int REQUEST_ENABLE_BT = 1;
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }


        viaBluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Implement Bluetooth recipient selection
            }
        });

        viaNfcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Implement NFC recipient selection
            }
        });

        sendFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Implement file sharing logic
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataSendActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

//        if(id == R.id.action_home){
//            Intent intent = new Intent(DataSendActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
//        else if(id == R.id.action_recipient){
//            Intent intent = new Intent(DataSendActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
//        else if(id == R.id.action_history){
//            Intent intent = new Intent(DataSendActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
        return super.onOptionsItemSelected(item);
    }

    // Add additional methods and logic as needed for file selection, recipient handling, and file sharing.

    int requestCode = 1;

    public void  onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == requestCode && resultCode == Activity.RESULT_OK){
            if(data == null)
                return;
        }
        Uri uri = data.getData();

        Toast.makeText(context, uri.getPath(), Toast.LENGTH_SHORT).show();
    }

    public  void chooseFile(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, requestCode);
    }

}
