package com.example.loginpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MyAdapter1 extends ArrayAdapter<Monday> {
    private static final String TAG = "LecturesOMOnday";
    private Context context;

    public MyAdapter1 (@NonNull Context context, int resource, @NonNull ArrayList<Monday> objects) {
        super(context,resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Monday monday =getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.timetable_row,parent,false);
        }
        TextView subject = (TextView) convertView.findViewById(R.id.subject);
        TextView instructorname = (TextView) convertView.findViewById(R.id.instructorname);
        TextView classtype = (TextView) convertView.findViewById(R.id.classtype);
        TextView classno = (TextView) convertView.findViewById(R.id.classno);
        TextView from = (TextView) convertView.findViewById(R.id.from);
        TextView to = (TextView) convertView.findViewById(R.id.to);


        subject.setText(monday.Subject);
        instructorname.setText(monday.InstructorName);
        classtype.setText(monday.classType);
        classno.setText(monday.classNo);
        from.setText(monday.From);
        to.setText(monday.to);

        return  convertView;
    }

}