package com.example.loginpage.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.loginpage.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment {
    TextView name, gr_number, year, sem, email, phone, attendence, dob,resetLink;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.name);
        gr_number = view.findViewById(R.id.gr_number);
        year = view.findViewById(R.id.year);
        sem = view.findViewById(R.id.semester);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        dob = view.findViewById(R.id.dob);
        attendence = view.findViewById(R.id.attendence);
        resetLink =view.findViewById(R.id.resetLink);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("students").document(userId);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess (DocumentSnapshot value) {
                        if(value.exists()){
                            name.setText(value.getString("name"));
                            gr_number.setText(value.getString("gr_no"));
                            year.setText(value.getString("major"));
                            phone.setText(value.getString("phone_no"));
                            sem.setText(value.getString("sem"));
                            email.setText(value.getString("email"));
                           dob.setText(value.getString("dob"));
                            attendence.setText(value.getString("attendance"));
                        }else{

                            Toast.makeText(getContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure (@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        Log.d("TAG",e.toString());
                    }
                });


        resetLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                try {
                    VerifyPhoneNoFragment fragment = new VerifyPhoneNoFragment();
                  //  UpdatePasswordFragment fragment = new UpdatePasswordFragment();
                    FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout,fragment);
                    ft.commit();

                }
                catch(Exception e) {
                    Toast.makeText(getActivity(),"" + e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;

    }



}