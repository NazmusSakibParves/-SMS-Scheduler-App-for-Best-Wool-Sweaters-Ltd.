package com.somitsolutions.android.smsscheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Nazmus Sakib Parves on 10/5/2017.
 */

public class AddMachineActivity extends Activity {

    private static Button btnAdd;
    private Button btnShowValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine);

        //setContentView(R.layout.layout_select_machine);

        btnShowValue = (Button)findViewById(R.id.showValue);

        btnShowValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddMachineActivity.this, ShowDataListActivity.class);
                startActivity(intent);

            }
        });

        btnAdd = (Button)findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 final Context context = view.getRootView().getContext();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formElementsView = inflater.inflate(R.layout.machine_input_form, null, false);

                final EditText editTextMachineID = (EditText) formElementsView.findViewById(R.id.editTextMachineID);
                final EditText editTextMachineName = (EditText) formElementsView.findViewById(R.id.editTextMachineName);

                new AlertDialog.Builder(context)
                        .setView(formElementsView)
                        .setTitle("Create Machine")
                        .setPositiveButton("Add",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dialog.cancel();

                                        String machineID = editTextMachineID.getText().toString();
                                        String machineName = editTextMachineName.getText().toString();

                                        ObjectMachine objectMachine = new ObjectMachine();
                                        objectMachine.MachineID= machineID;
                                        objectMachine.MachineName= machineName;

                                        boolean createSuccessful = new TableControllerMachine(context).create(objectMachine);

                                        if(createSuccessful){
                                            Toast.makeText(context, "Machine information is saved.", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(context, "Unable to save student information.", Toast.LENGTH_SHORT).show();
                                        }


                                        countRecords();
                                        //readRecords();
                                        ((AddMachineActivity) context).readRecords();

                                    }

                                }).show();
            }
        });


        countRecords();
        readRecords();


    }

    public void countRecords(){
        int recordCount = new TableControllerMachine(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }


  public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<ObjectMachine> machines = new TableControllerMachine(this).read();

        if (machines.size() > 0) {

            for (ObjectMachine obj : machines) {

                int id = obj.id;
                String machineID = obj.MachineID;
                String machineName = obj.MachineName;

                String textViewContents = machineID + " - " + machineName;

                TextView textViewMachineItem= new TextView(this);
                textViewMachineItem.setPadding(0, 10, 0, 10);
                textViewMachineItem.setText(textViewContents);
                textViewMachineItem.setTextSize(20);
                textViewMachineItem.setTag(Integer.toString(id));

                textViewMachineItem.setOnLongClickListener(new OnLongClickListenerMachineRecord());

                linearLayoutRecords.addView(textViewMachineItem);
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }

   @Override
    public void onBackPressed() {

       Intent intent = new Intent(AddMachineActivity.this,SetuppageActivity.class);
       startActivity(intent);
       finish();
    }


}
