package com.example.mymenu.SignUpSignIn;

import  android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymenu.DatabaseHelper;
import com.example.mymenu.R;

public class LoginFragment extends Fragment {

    private DatabaseHelper myDb;
    private EditText userIdEditText, emailEditText;
    private Button loginButton;

    private Intent loginConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        myDb = new DatabaseHelper(getActivity());
        // Initialize UI components
        userIdEditText = view.findViewById(R.id.editTextUserId);
        emailEditText = view.findViewById(R.id.editTextEmail);
        loginButton = view.findViewById(R.id.buttonLogin);

        // Set click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get entered ID and email
                String userId = userIdEditText.getText().toString();
                String email = emailEditText.getText().toString();

                // Check if ID and email are not empty
                if (!userId.isEmpty() && !email.isEmpty() && myDb.isAccountExist(userId.toString(), email.toString())) {
                    Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                    loginConfirm = new Intent(getContext(), LoginConfim.class);
                    startActivity(loginConfirm);
                } else {
                    // Show an error message if ID or email is empty
                    Toast.makeText(getActivity(), "Please enter ID and email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
