package com.example.loginpage.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loginpage.HomePage;
import com.example.loginpage.R;

public class MyCourseFragment extends Fragment {

    String[] mTitle = {
            "Applied Mathematics-III (Maths-III)",
            "Data Structure (DTS)",
            "Computer Graphics (CG)",
            "Digital Logic and Computer Organization and Architecture (DLCOA)",
            "Discrete Structure and Graph Theory",
            "Object Oriented Programming Methodology (OOPM)"};
    String[] mDescription = {
            "Prof. Abhiruchi A. Dakwale",
            "Prof. Subodh S. Karve",
            "Prof. Prasant Y. Itankar",
            "Prof. Jayant D. Sawarkar & Prof. Manoj D. Patil",
            "Prof. Jyoti A. Gaikwad & Prof. Manoj D. Patil",
            "Prof. Shreya N. Patankar"};

    int[] Images = {R.drawable.ic_baseline_person_24,
            R.drawable.ic_baseline_person_24,
            R.drawable.ic_baseline_person_24,
            R.drawable.ic_baseline_person_24,
            R.drawable.ic_baseline_person_24,
            R.drawable.ic_baseline_person_24};

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_course, container, false);


        ListView listView = view.findViewById(R.id.list);

        MyAdapter myAdapter = new MyAdapter(getActivity(), mTitle, mDescription, Images);
        listView.setAdapter(myAdapter);

        return view;


    }

    public static class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String[] rTitle;
        String[] rDescription;
        int[] rImages;

        MyAdapter(Context c, String[] title, String[] description, int[] images) {
            super(c, R.layout.row1, R.id.txt1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImages = images;

        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View raw = layoutInflater.inflate(R.layout.row1, parent, false);
            ImageView images = raw.findViewById(R.id.image);
            TextView myTitle = raw.findViewById(R.id.txt1);
            TextView myDescription = raw.findViewById(R.id.txt2);

            // now set our resources on views
            images.setImageResource(rImages[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);

            return raw;
        }
    }
}