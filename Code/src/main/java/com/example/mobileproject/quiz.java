package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class quiz extends AppCompatActivity {

    private EditText Question, answer,OptionB, OptionC, OptionD;
    Button save;
    Button cancel;
    Toolbar toolbar;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        /*toolbar = (Toolbar)findViewById(R.id.toolbar2);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        Question = findViewById(R.id.quizQ);
        answer = findViewById(R.id.editTextTextPersonName);
        OptionB = findViewById(R.id.editTextTextPersonName2);
        OptionC = findViewById(R.id.editTextTextPersonName3);
        OptionD = findViewById(R.id.editTextTextPersonName4);
        save = findViewById(R.id.savebtn);
        cancel = findViewById(R.id.cancelbtn);

        dbHelper = new DatabaseHelper(quiz.this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_Question = Question.getText().toString().trim();
                String txt_answer = answer.getText().toString().trim();
                String txt_OptionB = OptionB.getText().toString().trim();
                String txt_OptionC = OptionC.getText().toString().trim();
                String txt_OptionD = OptionD.getText().toString().trim();


                if (TextUtils.isEmpty(txt_Question)) {
                    Question.setError("This is a mandatory field");
                    return;
                }

                if (TextUtils.isEmpty(txt_answer)) {
                    answer.setError("This is a mandatory field");
                    return;
                }
                if (TextUtils.isEmpty(txt_OptionB)) {
                    OptionB.setError("This is a mandatory field");
                    return;
                }
                if (TextUtils.isEmpty(txt_OptionC)) {
                    OptionC.setError("This is a mandatory field");
                    return;
                }
                if (TextUtils.isEmpty(txt_OptionD)) {
                    OptionD.setError("This is a mandatory field");
                    return;
                }

                //wrong id
                String ID="1";
                String TABLE_NAME = "Questions";
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("delete from "+ TABLE_NAME);

                dbHelper.addNewQuestion(txt_Question, txt_answer, txt_OptionB, txt_OptionC, txt_OptionD, ID);

                // after adding the data we are displaying a toast message.
                Toast.makeText(quiz.this, "Question has been added.", Toast.LENGTH_SHORT).show();
                Question.setText("");
                answer.setText("");
                OptionB.setText("");
                OptionC.setText("");
                OptionD.setText("");


                save.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

    }

    public void openActivity2(){
        Intent intent = new Intent(this, readquestion.class);
        startActivity(intent);
    }


}