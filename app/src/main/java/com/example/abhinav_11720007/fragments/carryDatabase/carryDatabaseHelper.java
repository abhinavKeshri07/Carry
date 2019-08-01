package com.example.abhinav_11720007.fragments.carryDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static java.sql.Types.VARCHAR;

public class carryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "carry_database.db";
    private static final String TABLE_NAME = "groups";
    private static final String Col_1="id";
    private static final String Col_2 = "group_name";
    private static final String Col_3 = "item1";
    private static final String Col_4 = "item2";
    private static final String Col_5 = "item3";
    private static final String Col_6 = "item4";
    private static final String Col_7 = "item5";
    private static final String Col_8 = "item6";
    private static final String Col_9 = "item7";
    private static final String Col_10 = "item8";
    private static final String Col_11 = "item9";
    private static final String Col_12 = "item10";
    private static final String Col_13 = "boolean_integer";
    private static int version = 3;
    public carryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+Col_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Col_2+" VARCHAR, "
                + Col_3+" VARCHAR, "
                + Col_4+" VARCHAR , "
                + Col_5 + " VARCHAR ,"
                + Col_6 + " VARCHAR,"
                + Col_7 + " VARCHAR, "
                + Col_8 + " VARCHAR,"
                + Col_9 + " VARCHAR, "
                + Col_10+ " VARCHAR,"
                + Col_11 + " VARCHAR,"
                + Col_12 + " VARCHAR,"
                + Col_13 + "INTEGER DEFAULT 1023"
                +");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insert(String groupName, ArrayList<String> arrayList){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_2,groupName);
        try{
            cv.put(Col_3,arrayList.get(0));
            cv.put(Col_4,arrayList.get(1));
            cv.put(Col_5,arrayList.get(2));
            cv.put(Col_6,arrayList.get(3));
            cv.put(Col_7,arrayList.get(4));
            cv.put(Col_8,arrayList.get(5));
            cv.put(Col_9,arrayList.get(6));
            cv.put(Col_10,arrayList.get(7));
            cv.put(Col_11,arrayList.get(8));
            cv.put(Col_12,arrayList.get(9));
        }
        catch( Exception e)
        {
            
        }
        if(db.insert(TABLE_NAME,null, cv)==-1)
            return true;
        return false;
        // this method returns true when failed insertion.
    }
    public Cursor getGroupNames(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT "+ Col_2+ " FROM " + TABLE_NAME + " ;",null);
        return c;
    }
    public Cursor getItems(String group_name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT "+ Col_3 + " FROM " + TABLE_NAME + " WHERE " + Col_2 + " = " + group_name+ " ;", null);
        return c;
    }
}
