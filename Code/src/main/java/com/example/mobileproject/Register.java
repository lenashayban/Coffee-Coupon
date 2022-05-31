package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.security.AccessController.getContext;

public class Register extends AppCompatActivity {




    private EditText txtEmail, txtPassword,txtCPassword, FName;
    private Button btnRegister;
    TextView signInButton, userType;
    ProgressBar progressBar;
    boolean emailExist = false;
    RadioGroup radioGroup;
    RadioButton radioButton;

   // DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FName = findViewById(R.id.FName);
        txtEmail = findViewById(R.id.Temail);
        txtPassword = findViewById(R.id.Tpassword);
        txtCPassword = findViewById(R.id.TCpassword);
        btnRegister = findViewById(R.id.Bregister);
        signInButton = findViewById(R.id.signinButton);
        progressBar = findViewById(R.id.progressBar);
        userType = findViewById(R.id.txtUserType);
        radioGroup = findViewById(R.id.userType);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailExist = false;

                String txt_name = FName.getText().toString().trim();
                String txt_email = txtEmail.getText().toString().trim();
                String txt_pass = txtPassword.getText().toString().trim();
                String txt_cPass = txtCPassword.getText().toString().trim();

                if (TextUtils.isEmpty(txt_name)) {
                    FName.setError("Please Enter Your Name");
                    return;
                }

                if (TextUtils.isEmpty(txt_email)) {
                    txtEmail.setError("Please Enter Your Email");
                    return;
                }
                if (TextUtils.isEmpty(txt_pass)) {
                    txtPassword.setError("Please Enter Your Password");
                    return;
                }
                if (TextUtils.isEmpty(txt_cPass)) {
                    txtCPassword.setError("Please Confirm Your Password");
                    return;
                }

                if (radioGroup.getCheckedRadioButtonId() == -1)
                {
                    userType.setError("You have to choose a role!");
                    return;
                }

                if (txt_pass.length() < 8) {
                    txtPassword.setError("Please Enter at Least 8 Characters!");
                    return;
                }

                if (!isValidPassword(txt_pass)) {
                    txtPassword.setError("Password must contain at least 1 Character, 1 Number and 1 Special Character!");
                    return;
                }
                if (!txt_cPass.equals(txt_pass)) {
                    txtCPassword.setError("Password does not match confirm password!");
                    return;
                }

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String type = radioButton.getText().toString().trim();


                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

                // Gets the data repository in write mode
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Check if email is already signed up
                //String emailQuery = "Select userEmail from Users";
                Cursor cursor = db.query("Users", new String[]{"userEmail"}, null, null, null, null, null);
                for (int i=0; i<cursor.getCount(); i++) {
                    cursor.moveToNext();
                    if (txt_email.equals(cursor.getString(0))){
                        txtEmail.setError("Email is already registered! Login please");
                        emailExist = true;
                        return;
                    }
                } // end for loop

                Cursor cursor2 = db.query("Owners", new String[]{"ownerEmail"}, null, null, null, null, null);
                for (int i=0; i<cursor2.getCount(); i++) {
                    cursor2.moveToNext();
                    if (txt_email.equals(cursor2.getString(0))){
                        txtEmail.setError("Email is already registered! Login please");
                        emailExist = true;
                        return;
                    }
                } // end for loop

                if (!emailExist) {
                    progressBar.setVisibility(View.VISIBLE);

                    //check if user or owner
                    if (type.equals("User")) {
                        //Create a new map of values, where column names are the keys
                        ContentValues values = new ContentValues();
                        values.put("userName", txt_name);
                        values.put("userEmail", txt_email);
                        values.put("userPass", txt_pass);

                        //Insert the new row, returning the primary key value of the new row
                        long newRowId = db.insert("Users", null, values);
                        startActivity(new Intent(getApplicationContext(), Customer.class));
                    }

                    if (type.equals("Owner")) {
                        //Create a new map of values, where column names are the keys
                        ContentValues values = new ContentValues();
                        values.put("ownerName", txt_name);
                        values.put("ownerEmail", txt_email);
                        values.put("ownerPass", txt_pass);
                        values.put("longitude", " ");
                        values.put("latitude", " ");

                        //Insert the new row, returning the primary key value of the new row
                        long newRowId = db.insert("Owners", null, values);
                        Intent intent = new Intent(getApplicationContext(), Owner.class);
                        intent.putExtra("ownerEmail", txt_email);
                        startActivity(intent);
                    }
                }


            }

            });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}