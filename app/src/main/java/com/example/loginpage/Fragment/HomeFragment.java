package com.example.loginpage.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loginpage.R;


public class HomeFragment extends Fragment {

    int[] images = {R.drawable.news1,
                    R.drawable.news2};

    String[] mHead = {"Application acceptance for Fresh & Renewal applicants for Academic Year 2020-21 will be commencing from 03rd December 2020",
                        "All email id's are updated on ERP Login, Please update your profile"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        ListView listView = view.findViewById(R.id.feed_list);
        MyAdapter myAdapter = new MyAdapter(getActivity(), mHead, images);
        listView.setAdapter(myAdapter);



        Button btn1 = (Button)view.findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout, new TimeTableFragment());
                fr.commit();
            }
        });

        Button btn2 = (Button)view.findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout, new MyCourseFragment());
                fr.commit();
            }
        });

        Button btn3 = (Button)view.findViewById(R.id.btn3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout, new AttendanceFragment());
                fr.commit();
            }
        });

        Button btn4 = (Button)view.findViewById(R.id.btn4);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout, new AnnouncementFragment());
                fr.commit();
            }
        });

        return view;

    }
    public static class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String[] rhead;
        int[] rImages;

        MyAdapter(Context c, String[] head, int[] images) {
            super(c, R.layout.newsfeed_list, R.id.txthead, head);
            this.context = c;
            this.rhead = head;
            this.rImages = images;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View row1 = layoutInflater.inflate(R.layout.newsfeed_list, parent, false);

            ImageView imageView = row1.findViewById(R.id.imgview);
            TextView myTitle = row1.findViewById(R.id.txthead);

            imageView.setImageResource(rImages[position]);
            myTitle.setText(rhead[position]);

            return row1;
        }
    }
}


