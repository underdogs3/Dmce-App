package com.example.loginpage.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.loginpage.R;
import com.example.loginpage.course_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;


public class MyCourseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;

    private FirestoreRecyclerAdapter adapter;

    public MyCourseFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MyCourseFragment newInstance(String param1, String param2) {
        MyCourseFragment fragment = new MyCourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_course, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = view.findViewById(R.id.recview);
        recyclerView.setHasFixedSize(true);


        Query query = firebaseFirestore.collection("subjects");

        FirestoreRecyclerOptions<course_model> options = new FirestoreRecyclerOptions.Builder<course_model>()
                .setQuery(query, course_model.class)
                .build();
         adapter = new FirestoreRecyclerAdapter<course_model,viewHolder>(options) {
             @Override
             protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull course_model model) {
                 holder.ms_name.setText(model.getS_name());
                 holder.mp_name.setText(model.getP_name());
                 Glide.with(holder.mimage.getContext()).load(model.getPurl()).into(holder.mimage);
             }


             @NonNull
             @Override
             public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row1,parent,false);
               return new viewHolder(view);
             }


         };


            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);


        return view;
    }

    private class viewHolder extends RecyclerView.ViewHolder {

        private TextView ms_name, mp_name;
        private ImageView mimage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            ms_name = itemView.findViewById(R.id.subjectname);
            mp_name = itemView.findViewById(R.id.Professorname);
            mimage = itemView.findViewById(R.id.imageview);


        }

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