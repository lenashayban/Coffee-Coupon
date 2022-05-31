package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ownerGeofence extends AppCompatActivity {
    EditText longitude, latitude;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_geofence);

        Intent intent = getIntent();
        String ownerEmail = intent.getStringExtra("ownerEmail");

        longitude = findViewById(R.id.Longitude);
        latitude = findViewById(R.id.Latitude);
        submit = findViewById(R.id.submitBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_lat = latitude.getText().toString().trim();
                String txt_lag = longitude.getText().toString().trim();


                if (TextUtils.isEmpty(txt_lag)) {
                    longitude.setError("Please Enter Longitude");
                    return;
                }

                if (TextUtils.isEmpty(txt_lat)) {
                    latitude.setError("Please Enter Latitude");
                    return;
                }


                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

                // Gets the data repository in write mode
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                //Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put("longitude", txt_lag);
                values.put("latitude", txt_lat);


                //Insert the new row, returning the primary key value of the new row
                //long newRowId = db.insert("Users", null, values);
                db.update("Owners", values, String.format("%s = ?", "ownerEmail"),
                        new String[]{ownerEmail});
                startActivity(new Intent(getApplicationContext(), Owner.class));
            }
});
    }
}