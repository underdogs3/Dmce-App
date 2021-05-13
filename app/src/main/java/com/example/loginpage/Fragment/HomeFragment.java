package com.example.loginpage.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.loginpage.R;
import com.example.loginpage.course_model;
import com.example.loginpage.newsfeed_model;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class HomeFragment extends Fragment {

//    int[] images = {R.drawable.news1,
//                    R.drawable.news2};
//
//    String[] mHead = {"Application acceptance for Fresh & Renewal applicants for Academic Year 2020-21 will be commencing from 03rd December 2020",
//                        "All email id's are updated on ERP Login, Please update your profile"};

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;

    private FirestoreRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        ListView listView = view.findViewById(R.id.feed_list);
//        MyAdapter myAdapter = new MyAdapter(getActivity(), mHead, images);
//        listView.setAdapter(myAdapter);


        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = view.findViewById(R.id.feed_list);
        recyclerView.setHasFixedSize(true);

        Query query = firebaseFirestore.collection("newsfeeds");

        FirestoreRecyclerOptions<newsfeed_model> options = new FirestoreRecyclerOptions.Builder<newsfeed_model>()
                .setQuery(query, newsfeed_model.class)
                .build();


        Button btn1 = (Button) view.findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout, new TimeTableFragment());
                fr.commit();
            }
        });

        Button btn2 = (Button) view.findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout, new MyCourseFragment());
                fr.commit();
            }
        });

        Button btn3 = (Button) view.findViewById(R.id.btn3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout, new AttendanceFragment());
                fr.commit();
            }
        });

        Button btn4 = (Button) view.findViewById(R.id.btn4);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout, new AnnouncementFragment());
                fr.commit();
            }
        });

        adapter = new FirestoreRecyclerAdapter<newsfeed_model, newsviewHolder>(options) {
            @NonNull
            @Override
            public newsviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_list, parent, false);
                return new newsviewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull newsviewHolder holder, int position, @NonNull newsfeed_model model) {

                holder.ntitle.setText(model.getTitle());
                Glide.with(holder.nimage.getContext()).load(model.getPurl()).into(holder.nimage);
            }


        };


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;

    }

    private class newsviewHolder extends RecyclerView.ViewHolder {


        private ImageView nimage;
        private TextView ntitle;

        public newsviewHolder(@NonNull View itemView) {
            super(itemView);

            ntitle = itemView.findViewById(R.id.txthead);
            nimage = itemView.findViewById(R.id.imgview);


        }
//    public static class MyAdapter extends ArrayAdapter<String> {
//
//        Context context;
//        String[] rhead;
//        int[] rImages;
//
//        MyAdapter(Context c, String[] head, int[] images) {
//            super(c, R.layout.newsfeed_list, R.id.txthead, head);
//            this.context = c;
//            this.rhead = head;
//            this.rImages = images;
//
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            @SuppressLint("ViewHolder") View row1 = layoutInflater.inflate(R.layout.newsfeed_list, parent, false);
//
//            ImageView imageView = row1.findViewById(R.id.imgview);
//            TextView myTitle = row1.findViewById(R.id.txthead);
//
//            imageView.setImageResource(rImages[position]);
//            myTitle.setText(rhead[position]);
//
//            return row1;
//        }
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}



