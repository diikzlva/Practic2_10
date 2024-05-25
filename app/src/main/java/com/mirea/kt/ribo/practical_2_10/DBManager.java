package com.mirea.kt.ribo.practical_2_10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBManager {

    private SQLiteOpenHelper sqLiteHelper;

    public DBManager(SQLiteOpenHelper sqLiteHelper){

        this.sqLiteHelper = sqLiteHelper;
    }

    public boolean saveTelephoneToDatabase(Telephone telephone){
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("model", telephone.getModel());
        cv.put("serial_number", telephone.getSerialNumber());
        cv.put("price", telephone.getPrice());

        long rowId = db.insert("TABLE_TELEPHONE", null, cv);
        cv.clear();
        db.close();
        return rowId != -1;
    }

    public ArrayList<Telephone> loadAllTelephonesFromDatabase(){
        ArrayList<Telephone> telephones = new ArrayList<>();
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        Cursor dbCursor = db.query("TABLE_TELEPHONE",
                null, null, null,
                null, null, null);
        if (dbCursor.moveToFirst()){
            do {
                String model = dbCursor.getString(dbCursor.getColumnIndexOrThrow("model"));
                String serialNumber = dbCursor.getString(dbCursor.getColumnIndexOrThrow("serial_number"));
                int price = dbCursor.getInt(dbCursor.getColumnIndexOrThrow("price"));
                telephones.add(new Telephone(model, serialNumber, price));
            }while (dbCursor.moveToNext());
        }
        dbCursor.close();
        db.close();
        return telephones;
    }
}