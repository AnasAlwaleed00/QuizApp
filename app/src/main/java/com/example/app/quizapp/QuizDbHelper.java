package com.example.app.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.app.quizapp.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyQuizApp.db";
    private static final int DATABASE_VERSION  =4;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + "TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Santiago is the capital of which country?", "Congo", "Cuba", "Croatia","Chile", 4);
        addQuestion(q1);
        Question q2 = new Question("Zagreb is is the capital of which country?", "Croatia", "Costa Rica", "Estonia","Fiji", 1);
        addQuestion(q2);
        Question q3 = new Question("Tbilisi is the capital of which country? ", "Georgia", "Gabon", "Guatemala","Indonesia", 1);
        addQuestion(q3);
        Question q4 = new Question("Tehran is the capital of which country? ", "Iraq", "Iran", "Kenya","Kosovo", 2);
        addQuestion(q4);
        Question q5 = new Question("What is the capital of Gabon?", "Cayenne", "Helsinki", "Caracas","Libreville", 4);
        addQuestion(q5);
        Question q6 = new Question("Buenos Aires is the capital of which country?", "Uruguay", "Bolivia", "Argentina","Armenia", 3);
        addQuestion(q6);
        Question q7 = new Question("New Delhi is the capital of which country?", "India", "New Guyana", "Kuwait","Kygyzstan", 1);
        addQuestion(q7);
        Question q8 = new Question("Pyongyang is the capital of which country?", "South Korea", "North Korea", "Kazakhstan","Hungary", 2);
        addQuestion(q8);
        Question q9 = new Question("Kabul is the capital of which country?", "Andorra", "Ukraine", "Afganistan","Azerbaijan", 3);
        addQuestion(q9);
        Question q10 = new Question("Mogadishu is the capital of which country?", "Tanzania", "Senegal", "Kenya","Somalia", 4);
        addQuestion(q10);
        Question q11 = new Question("Hanoi is the capital of which country? ", "Moldova", "Vietnam", "Gibraltar","South Sudan", 2);
        addQuestion(q11);
        Question q12 = new Question("Kampala is the capital of which country?", "Venezuela", "Uganda", "Senegal","Tajikistan", 2);
        addQuestion(q12);
        Question q13 = new Question("What is the capital of Morocco?", "Rabat", "Stepanakert", "Tunis","Riyadh", 1);
        addQuestion(q13);
        Question q14 = new Question("What is the capital of Zimbabwe?", "Dictato", "Harare", "Mugabe","Zimba", 2);
        addQuestion(q14);
        Question q15 = new Question("What is the capital of Somalia?", "Victoria", "Honiara", "Mogadishu","Bloemfontein", 3);
        addQuestion(q15);
        Question q16 = new Question("What is the capital of Spain?", "Barcelona", "Madrid", "Lisbon","London", 2);
        addQuestion(q16);
        Question q17 = new Question("What is the capital of Canada? ", "Ottawa", "Toronto", "Montreal","Vancouver", 1);
        addQuestion(q17);
        Question q18 = new Question("What is the capital of Brazil?", "Salvador", "Sao Paulo", "Rio de Janeiro","Brazilia", 4);
        addQuestion(q18);
        Question q19= new Question("What is the capital of Russia?", "Kazan", "Novosibirsk", "St Petersburg","Moscow", 4);
        addQuestion(q19);
        Question q20= new Question("What is the capital of Poland?", "Poznan", "Wrocław", "Warsaw","Krakow", 3);
        addQuestion(q20);
        Question q21 = new Question("What is the capital of Rwanda", "Addis Ababa", "Kigali", "Nyamata","Nairobi", 2);
        addQuestion(q21);
        Question q22 = new Question("What is the capital of ", "Bamako", "Timbuktu", "Mopti","Lagos", 1);
        addQuestion(q22);
        Question q23 = new Question("What is the capital of Nigeria", "Lagos", "Abuja", "Cairo","Juba", 1);
        addQuestion(q23);
        Question q24 = new Question("What is the capital of Chad", "Dar es Salaam", "Accra", "N'Djamena","Dakar", 3);
        addQuestion(q24);
        Question q25 = new Question("What is the capital of Ghana", "Accra", "Yaounde", "Abuja","Luanda", 1);
        addQuestion(q25);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        //cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                //question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    /*public List<Question> getQuestions(String difficulty) {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();


        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }*/
}