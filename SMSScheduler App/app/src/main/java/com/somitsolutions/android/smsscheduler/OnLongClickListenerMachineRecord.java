package com.somitsolutions.android.smsscheduler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

/**
 * Created by vista on 10/19/2017.
 */

class OnLongClickListenerMachineRecord implements View.OnLongClickListener {
    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {

        context = view.getContext();
        id = view.getTag().toString();

        //final CharSequence[] items = { "Edit", "Delete" };
        final CharSequence[] items = { "Delete" };

        new AlertDialog.Builder(context).setTitle("Machine Records")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        dialog.dismiss();

                        if (item == 0) {
                            //editRecord(Integer.parseInt(id));

                            boolean deleteSuccessful = new TableControllerMachine(context).delete(Integer.parseInt(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Machine record was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete Machine record.", Toast.LENGTH_SHORT).show();
                            }

                            ((AddMachineActivity) context).countRecords();
                            ((AddMachineActivity) context).readRecords();
                        }
                       /* else if (item == 1) {

                            boolean deleteSuccessful = new TableControllerMachine(context).delete(Integer.parseInt(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Machine record was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete Machine record.", Toast.LENGTH_SHORT).show();
                            }

                            ((AddMachineActivity) context).countRecords();
                            ((AddMachineActivity) context).readRecords();

                        }*/




                    }
                }).show();

        return false;
    }
}
