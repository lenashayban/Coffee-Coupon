package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class trueAnswer extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_answer);
        //get rating bar object**
        //RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1); //creativty**
        //bar.setNumStars(5);**
        //bar.setStepSize(0.5f);**
        //get text view
        ImageView happy =(ImageView)findViewById(R.id.imageView6);
        TextView t=(TextView)findViewById(R.id.textView);
        //display msg
        String random = getRandomNumberString();
        String COde = "Congratulations you WON , here is your code : "+random+" \n Please contact the Coffee shop’s cashier and have a wonderful COFFEE ";



        t.setText(COde);

    }
    public  String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}