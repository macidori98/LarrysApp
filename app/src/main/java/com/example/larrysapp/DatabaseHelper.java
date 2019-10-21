package com.example.larrysapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "hobbies_db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Hobby.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Hobby.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertHobby(String hobby){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Hobby.COLUMN_HOBBY, hobby);
        long id = db.insert(Hobby.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Hobby getHobby(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query( Hobby.TABLE_NAME,
                new String[]{Hobby.COLUMN_ID, Hobby.COLUMN_HOBBY, Hobby.COLUMN_TIMESTAMP},
                Hobby.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Hobby hobby = new Hobby(
                cursor.getInt(cursor.getColumnIndex(Hobby.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Hobby.COLUMN_HOBBY)),
                cursor.getString(cursor.getColumnIndex(Hobby.COLUMN_TIMESTAMP))
        );

        cursor.close();

        return hobby;
    }

    public List<Hobby> getAllHobbies(){
        List<Hobby> hobbies = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Hobby.TABLE_NAME + " ORDER BY " +
                Hobby.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Hobby hobby = new Hobby();
                hobby.setId(cursor.getInt(cursor.getColumnIndex(Hobby.COLUMN_ID)));
                hobby.setHobby(cursor.getString(cursor.getColumnIndex(Hobby.COLUMN_HOBBY)));
                hobby.setTimestamp(cursor.getString(cursor.getColumnIndex(Hobby.COLUMN_TIMESTAMP)));

                hobbies.add(hobby);
            } while (cursor.moveToFirst());
        }

        db.close();
        return hobbies;
    }

    public int getHobbiesCount(){
        String countQuery = "SELECT * FROM " + Hobby.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);

        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int updateHobby(Hobby hobby){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Hobby.COLUMN_HOBBY, hobby.getHobby());

        return db.update(Hobby.TABLE_NAME, values, Hobby.COLUMN_ID + " = ?",
                new String[]{String.valueOf(hobby.getId())});
    }

    public void deleteHobby(Hobby hobby){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Hobby.TABLE_NAME, Hobby.COLUMN_ID + " = ?",
                new String[]{String.valueOf(hobby.getId())});
        db.close();
    }
}
