package com.somitsolutions.android.smsscheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nazmus Sakib Parves on 10/10/2017.
 */

public class SQLiteListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> machineID;
    ArrayList<String> machineName;


    public SQLiteListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> name
    ){
        this.context = context2;
        this.machineID = id;
        this.machineName = name;

    }


    @Override
    public int getCount() {
        return machineID.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View child, ViewGroup parent) {
        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

            holder = new Holder();

            holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.textviewname = (TextView) child.findViewById(R.id.textViewName);
//            holder.textviewphone_number = (TextView) child.findViewById(R.id.textViewPHONE_NUMBER);
//            holder.textviewsubject = (TextView) child.findViewById(R.id.textViewSUBJECT);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(machineID.get(position));
        holder.textviewname.setText(machineName.get(position));
//        holder.textviewphone_number.setText(User_PhoneNumber.get(position));
//        holder.textviewsubject.setText(UserSubject.get(position));

        return child;
    }

    public class Holder {
        TextView textviewid;
        TextView textviewname;
//        TextView textviewphone_number;
//        TextView textviewsubject;
    }

}
