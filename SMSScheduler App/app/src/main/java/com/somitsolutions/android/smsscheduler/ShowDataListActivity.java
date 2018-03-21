package com.somitsolutions.android.smsscheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Nazmus Sakib Parves on 10/10/2017.
 */

public class ShowDataListActivity  extends Activity{

    DataBaseHandlerMachine SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;

    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);

        LISTVIEW = (ListView) findViewById(R.id.listView);

        SQLITEHELPER = new DataBaseHandlerMachine(this);

        LISTVIEW.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final AlertDialog.Builder adChooseAction = new AlertDialog.Builder(ShowDataListActivity.this) ;
                adChooseAction.setTitle("WHAT TO DO?");
                //GO TO EDIT SCREEN
                adChooseAction.setPositiveButton("SMS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(ShowDataListActivity.this,SetuppageActivity.class);
                        startActivity(intent);
                        /*Object obj = LISTVIEW.getAdapter().getItem(i);
                        String value= obj.toString();
                        Intent confirm = new Intent(ShowDataListActivity.this, SetuppageActivity.class);
                        confirm.putExtra("value", value);

                        startActivity(confirm);*/
                       // sendSMSMessage();
                    }
                    
                });
                //DELETE THE ROW THE USER CHOOSED
              /*  adChooseAction.setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //method to delete movies
                        adapter.getItem(i);
                        mds.open();
                        //will access the MoviesDataSource class to deleteMoviesByRow method
                        mds.deleteMovieByRow(i);
                        mds.close();
                    }
                });*/
                adChooseAction.show();
                Toast.makeText(ShowDataListActivity.this, "Item in position " + i + " clicked", Toast.LENGTH_LONG).show();
                // Return true to consume the click event. In this case the
                // onListItemClick listener is not called anymore.
                return true;
            }
        });

    }

/*
    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String ("01715539427"));
        smsIntent.putExtra("sms_body"  , "Test ");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ShowDataListActivity.this, "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
*/


    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM machines", null);

        ID_ArrayList.clear();
        NAME_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHandlerMachine.KEY_ID)));

                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHandlerMachine.KEY_Name)));
                ;

            } while (cursor.moveToNext());
        }

        ListAdapter = new SQLiteListAdapter(ShowDataListActivity.this, ID_ArrayList,NAME_ArrayList);

        LISTVIEW.setAdapter((android.widget.ListAdapter) ListAdapter);

        cursor.close();
    }

}
