package com.example.donatefood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "DonateFood.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "donor_name";
    private static final String COLUMN_LOCATION = "location_donor";
    private static final String COLUMN_PHONENO = "phone_no";
    private static final String COLUMN_FOODTYPE = "food_type";
    private static final String COLUMN_DISHNAME = "dish_name";
    private static final String COLUMN_COMMENTS = "comments";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_LOCATION + " TEXT, " +
                        COLUMN_PHONENO + " TEXT, " +
                        COLUMN_FOODTYPE + " TEXT, " +
                        COLUMN_DISHNAME + " TEXT, " +
                        COLUMN_COMMENTS + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addInfo(String title, String location, String comments, String foodtype, String dishname, String phoneno){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_PHONENO, phoneno);
        cv.put(COLUMN_FOODTYPE, foodtype);
        cv.put(COLUMN_DISHNAME, dishname);
        cv.put(COLUMN_COMMENTS, comments);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
