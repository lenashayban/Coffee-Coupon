package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class falseAnswer extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_false_answer);
        //get rating bar object**
        //RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1); //creativty**
        //bar.setNumStars(5);**
        //bar.setStepSize(0.5f);**
        //get text view
        ImageView sad =(ImageView)findViewById(R.id.imageView6);
        TextView t=(TextView)findViewById(R.id.textView);
        //display msg




        t.setText("Sorry the answer is not correct ! , Have a nice day ! ");

    }
}