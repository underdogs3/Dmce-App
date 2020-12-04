package com.example.loginpage.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loginpage.R;


public class ProfileFragment extends Fragment {



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        String[] ptitle = {
                "First Name",
                "Last Name",
                "Date of Birth",
                "Email id",
                "Phone",
                "Father Name",
                "Mother Name"
        };

        String[] psubtitle = {
                "xyz",
                "xyz",
                "DD/MM/YYY",
                "xyz@",
                "123456789",
                "xyz",
                "xyz",
        };

        ListView listView = view.findViewById(R.id.list2);
        MyAdapter adapter = new MyAdapter(getActivity(),ptitle,psubtitle);
        listView.setAdapter(adapter);

                return view;
    }

        static class MyAdapter extends ArrayAdapter<String>{

        Context context;
        String[] rtitle;
        String[] rsubtitle;

        MyAdapter(Context c,String[] title, String[] subtitle){
            super(c,R.layout.profilelist,R.id.titletxt,title);
            this.context = c;
            this.rtitle = title;
            this.rsubtitle = subtitle;
        }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                @SuppressLint("ViewHolder") View profilelist = layoutInflater.inflate(R.layout.profilelist, parent, false);

                TextView myTitle = profilelist.findViewById(R.id.titletxt);
                TextView mysubtitle = profilelist.findViewById(R.id.subtitletxt);

                myTitle.setText(rtitle[position]);
                mysubtitle.setText(rsubtitle[position]);

                return profilelist;
            }
        }
}