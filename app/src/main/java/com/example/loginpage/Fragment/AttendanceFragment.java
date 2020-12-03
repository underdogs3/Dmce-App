package com.example.loginpage.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.loginpage.R;


public class AttendanceFragment extends Fragment {
    @NonNull
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);



        String[] mSubject = {"Digital Logic & Computer Organization and Architecture", "Applied Mathematics III", "Computer Graphics", "Data Structure", "Discrete Structures & Graph Theory", "Object Oriented Programing with Java"};
        String[] mPercentage = {"100%", "100%", "100%", "100%", "100%", "100%" };
        String[] mClassesAttended = {"43", "43", "43", "43", "43", "43"};
        String[] mTotalClasses = {"43", "43", "43", "43", "43", "43"};

        ListView listView = view.findViewById(R.id.listview);

        MyAdapter adapter = new MyAdapter(getActivity(), mSubject, mPercentage, mClassesAttended, mTotalClasses);
        listView.setAdapter(adapter);

        return view;
    }

    static class MyAdapter extends ArrayAdapter<String> {


        Context context;
        String[] rSubject;
        String[] rPercentage;
        String[] rClassesAttended;
        String[] rTotalClasses;

        MyAdapter(Context c, String[] subject, String[] Percentage, String[] ClassesAttended, String[] TotalClasses) {
            super(c, R.layout.row, R.id.sub, subject);
            this.context = c;
            this.rSubject = subject;
            this.rPercentage = Percentage;
            this.rClassesAttended = ClassesAttended;
            this.rTotalClasses = TotalClasses;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView Subject = row.findViewById(R.id.sub);
            TextView Percentage = row.findViewById(R.id.Percentage);
            TextView ClassesAttended = row.findViewById(R.id.classesAttended);
            TextView TotalClasses = row.findViewById(R.id.classesTotal);

            Subject.setText(rSubject[position]);
            Percentage.setText(rPercentage[position]);
            ClassesAttended.setText(rClassesAttended[position]);
            TotalClasses.setText(rTotalClasses[position]);
            return row;
        }
}}




