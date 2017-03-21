package com.example.ssith123.unoapps.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SSITH123 on 3/10/2017.
 */

public class MySurrounding extends SQLiteOpenHelper {
    final static String DB_NAME = "surrounding_db";
    public MySurrounding(Context context) {
        super(context,DB_NAME,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS restoran(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, lat TEXT, lng TEXT)";
        sqLiteDatabase.execSQL(sql);
        ContentValues values = new ContentValues();
        values.put("_id", "1");
        values.put("name", "The Bakery Cafe");
        values.put("address", "Madan Bhandari Road,Kathmandu 44600");
        values.put("lat", "27.688088");
        values.put("lng", "85.336031 ");
        sqLiteDatabase.insert("restoran", "_id", values);

//        values.put("_id", "2");
//        values.put("name", "Hukum Food Land");
//        values.put("address", "Devkota Sadak, Kathmandu 44600");
//        values.put("lat", "27.691574");
//        values.put("lng", "85.336117");
//        sqLiteDatabase.insert("restoran", "_id", values);
//
//        values.put("_id", "3");
//        values.put("name", "Everest Mandarin Restaurant");
//        values.put("address", "Shankhamul Marga,Kathmandu 44600");
//        values.put("lat", "27.687024");
//        values.put("lng", "85.335259");
//        sqLiteDatabase.insert("restoran", "_id", values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS restoran");
        onCreate(sqLiteDatabase);
    }
}
