package com.example.mobileproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String dbName = "mobileDB6";
    static final int dbVersion = 4;





    static final String usersTable = "Users";
    static final String colID = "userID";
    static final String colName = "userName";
    static final String colEmail = "userEmail";
    static final String colPass = "userPass";
    //static final String colType = "userType";

    static final String ownersTable = "Owners";
    static final String colID2 = "ownerID";
    static final String colName2 = "ownerName";
    static final String colEmail2 = "ownerEmail";
    static final String colPass2 = "ownerPass";
    static final String colLatitude = "latitude";
    static final String colLongitude = "longitude";


    //Owner location

    static final String QuestionsTable = "Questions";
    static final String colID3 = "QuestionID";
    static final String Question = "Question";
    static final String Answer = "Answer";
    static final String OptionB = "OptionB";
    static final String OptionC = "OptionC";
    static final String OptionD = "OptionD";
    static final String OwnerId = "ownerID";






    public DatabaseHelper (Context context) {
        super(context, dbName, null, dbVersion);
    }

    public DatabaseHelper () {

        super( null, dbName, null, dbVersion);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE if not exists " + usersTable +
                " ( " +
                colID + " INTEGER PRIMARY KEY autoincrement," +
                colName + " TEXT," +
                colEmail + " Text," +
                colPass + " Text)";
        // Users Table
        db.execSQL(query);

        // owners table
        query = "CREATE TABLE if not exists " + ownersTable +
                " ( " +
                colID2 + " INTEGER PRIMARY KEY autoincrement," +
                colName2 + " TEXT," +
                colEmail2 + " Text," +
                colPass2 + " Text," +
                colLatitude + " Text," +
                colLongitude + " Text)";

        db.execSQL(query);

        //startActivity(new Intent(this, Register.class));


        query = "CREATE TABLE if not exists " + QuestionsTable +
                " ( " +
                colID3 + " INTEGER PRIMARY KEY autoincrement," +
                Question + " TEXT," +
                Answer + " Text," +
                OptionB + " Text," +
                OptionC + " Text," +
                OptionD + " Text," +
                OwnerId+ " Text)";
        db.execSQL(query);

    }
    public void addNewQuestion(String question, String answer, String optionB, String optionC,String optionD ,String ownerid) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Question, question);
        values.put(Answer, answer);
        values.put(OptionB, optionB);
        values.put(OptionC, optionC);
        values.put(OptionD, optionD);
        values.put(OwnerId, ownerid);



        db.insert(QuestionsTable, null, values);

        db.close();
    }
    public Question getQuestion() {
        SQLiteDatabase dbase;
        // Select Query
        String selectQuery = "SELECT  * FROM " + QuestionsTable;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        Question quest = new Question();
        if (cursor.moveToFirst()) {
            quest.setID(cursor.getInt(0));
            quest.setQUESTION(cursor.getString(1));
            quest.setANSWER(cursor.getString(2));
            quest.setOPTB(cursor.getString(3));
            quest.setOPTC(cursor.getString(4));
            quest.setOPTD(cursor.getString(5));
        }
        // return quest
        return quest;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}