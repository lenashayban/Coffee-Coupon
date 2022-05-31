package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText txtEmail, loginPassword;
    Button loginButton;
    ProgressBar LoginProgressBar;
    TextView  invalid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginProgressBar = findViewById(R.id.loginProgressBar);
        txtEmail = findViewById(R.id.loginEmailAddress);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        invalid = findViewById(R.id.invalid);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    txtEmail.setError("Please Enter Your Email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    loginPassword.setError("Please Enter Your Password");
                    return;
                }

                LoginProgressBar.setVisibility(View.VISIBLE);

                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

                // Gets the data repository in write mode
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                Cursor cursor = db.query("Users", new String[]{"userEmail","userPass"}, null, null, null, null, null);
                for (int i=0; i<cursor.getCount(); i++) {
                    cursor.moveToNext();
                    if (email.equals(cursor.getString(0))){
                        if(password.equals(cursor.getString(1)))
                            //change activity name!!!
                            startActivity(new Intent(getApplicationContext(), Customer.class));

                        return;
                    }
                } // end for loop

                Cursor cursor2 = db.query("Owners", new String[]{"ownerEmail", "ownerPass"}, null, null, null, null, null);
                for (int i=0; i<cursor2.getCount(); i++) {
                    cursor2.moveToNext();
                    if (email.equals(cursor2.getString(0))){
                        if(password.equals(cursor2.getString(1)))
                            //change activity name!!!
                            startActivity(new Intent(getApplicationContext(), Owner.class));
                        return;
                    }
                } // end for loop
                txtEmail.setError("Invalid Email or Password");
                LoginProgressBar.setVisibility(View.INVISIBLE);
                return;
            }



        });



    }
}