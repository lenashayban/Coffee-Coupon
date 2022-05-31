package com.example.mobileproject;

public class Question {
    private int ID;
    private String QUESTION;
    private String ANSWER;
    private String OPTB;
    private String OPTC;
    private String OPTD;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }

    public String getOPTB() {
        return OPTB;
    }

    public void setOPTB(String OPTB) {
        this.OPTB = OPTB;
    }

    public String getOPTC() {
        return OPTC;
    }

    public void setOPTC(String OPTC) {
        this.OPTC = OPTC;
    }

    public String getOPTD() {
        return OPTD;
    }

    public void setOPTD(String OPTD) {
        this.OPTD = OPTD;
    }

    public Question(String QUESTION, String ANSWER, String OPTB, String OPTC, String OPTD) {
        this.QUESTION = QUESTION;
        this.ANSWER = ANSWER;
        this.OPTB = OPTB;
        this.OPTC = OPTC;
        this.OPTD = OPTD;
    }

    public Question() {
        ID = 0;
        QUESTION = "";
        OPTD = "";
        OPTB = "";
        OPTC = "";
        ANSWER = "";
    }
}