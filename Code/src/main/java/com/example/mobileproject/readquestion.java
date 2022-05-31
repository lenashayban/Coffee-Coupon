package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

public class readquestion extends AppCompatActivity {

    Toolbar toolbar;
    RadioButton rdA, rdB, rdC, rdD;
    TextView txtQuestion;
    Button SUBMIT;
    Question currentQ;
    NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readquestion);

            /*toolbar = (Toolbar)findViewById(R.id.toolbar2);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });*/
        notificationHelper = new NotificationHelper(this);

        DatabaseHelper myDb = new DatabaseHelper(this);
        txtQuestion=(TextView)findViewById(R.id.QUESTION);
        rdA=(RadioButton)findViewById(R.id.A);
        rdB=(RadioButton)findViewById(R.id.B);
        rdC=(RadioButton)findViewById(R.id.CORRECT);
        rdD=(RadioButton)findViewById(R.id.D);
        SUBMIT=(Button)findViewById(R.id.SUBMIT);

        currentQ=myDb.getQuestion();
        setQuestionView();

        SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.rdg);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                grp.clearCheck();
                Log.d("youra ns", currentQ.getANSWER()+" "+answer.getText()); // **idk**
                if(currentQ.getANSWER().equals(answer.getText()))
                {

                    // result act


                    Intent intent = new Intent(readquestion.this, trueAnswer.class); //a msg w the coupon
                    notificationHelper.sendHighPriorityNotification("Congratulations!", "You Win The Game. Enjoy Your Cupon", readquestion.class);
                    startActivity(intent);
                    finish(); //make sure it's not an activity with result or yes
                }
                else {
                    Intent intent = new Intent(readquestion.this, falseAnswer.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

    private void setQuestionView(){
        txtQuestion.setText(currentQ.getQUESTION());
        rdA.setText(currentQ.getANSWER());
        rdB.setText(currentQ.getOPTB());
        rdC.setText(currentQ.getOPTC());
        rdD.setText(currentQ.getOPTD());
    }
}




