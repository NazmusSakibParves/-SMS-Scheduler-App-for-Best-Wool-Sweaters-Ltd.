package com.somitsolutions.android.smsscheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vista on 10/7/2017.
 */

public class TableControllerMachine extends DataBaseHandlerMachine {
    public TableControllerMachine(Context context) {
        super(context);
    }
    public boolean create(ObjectMachine objectMachine) {

        ContentValues values = new ContentValues();

        values.put("machineID", objectMachine.MachineID);
        values.put("machineName", objectMachine.MachineName);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("machines", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM machines";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }


    public List<ObjectMachine> read() {

        List<ObjectMachine> recordsList = new ArrayList<ObjectMachine>();

        String sql = "SELECT * FROM machines ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String machineID = cursor.getString(cursor.getColumnIndex("machineID"));
                String machineName = cursor.getString(cursor.getColumnIndex("MachineName"));

                ObjectMachine objectMachine = new ObjectMachine();
                objectMachine.id = id;
                objectMachine.MachineID = machineID;
                objectMachine.MachineName = machineName;

                recordsList.add(objectMachine);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }



    public ObjectMachine readSingleRecord(int MachineID) {

        ObjectMachine objectMachine = null;

        String sql = "SELECT * FROM machines WHERE id = " + MachineID;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String machineID = cursor.getString(cursor.getColumnIndex("machineID"));
            String machineName = cursor.getString(cursor.getColumnIndex("MachineName"));

            objectMachine = new ObjectMachine();
            objectMachine.id = id;
            objectMachine.MachineID = machineID;
            objectMachine.MachineName = machineName;

        }

        cursor.close();
        db.close();

        return objectMachine;

    }

    public boolean update(ObjectMachine objectMachine) {

        ContentValues values = new ContentValues();

        values.put("machineID", objectMachine.MachineID);
        values.put("machineName", objectMachine.MachineName);

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(objectMachine.id) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("machines", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;

    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("machines", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;

    }


    /*public List<String> getAllMachines()
    {
        List<String> machineList = new ArrayList<>();
        //get readable database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MachineName FROM machines",null);
        if(cursor.moveToFirst())
        {
            do {
                machineList.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        //close the cursor
        cursor.close();
        //close the database
        db.close();
        return machineList;
    }
*/
}
