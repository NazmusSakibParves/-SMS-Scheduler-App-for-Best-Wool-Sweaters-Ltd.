package com.somitsolutions.android.smsscheduler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nazmus Sakib Parves on 10/7/2017.
 */

public class DataBaseHandlerMachine extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "MachineDatabase";

    public static final String KEY_ID="machineID";

    public static final String TABLE_NAME="machines";

    public static final String KEY_Name="MachineName";

    public DataBaseHandlerMachine(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       /* String sql = "CREATE TABLE machines " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "machineID TEXT, " +
                "MachineName TEXT ) ";*/

        String sql = "CREATE TABLE "+TABLE_NAME+"( id INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_ID+" VARCHAR, "+KEY_Name+" VARCHAR)";


        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //String sql = "DROP TABLE IF EXISTS machines";
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //db.execSQL(sql);

        onCreate(db);
    }
}
