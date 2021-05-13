








package com.example.loginpage;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyAdapter1 extends ArrayAdapter<Monday> {
    private static final String TAG = "LecturesOMOnday";
    private Context context;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    public MyAdapter1 (@NonNull Context context, int resource, @NonNull ArrayList<Monday> objects) {
        super(context,resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Monday monday =getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.timetable_row,parent,false);
        }
        final TextView subject = (TextView) convertView.findViewById(R.id.subject);
        final TextView instructorname = (TextView) convertView.findViewById(R.id.instructorname);
        final TextView classtype = (TextView) convertView.findViewById(R.id.classtype);
        final TextView classno = (TextView) convertView.findViewById(R.id.classno);
        final TextView from = (TextView) convertView.findViewById(R.id.from);
        final TextView to = (TextView) convertView.findViewById(R.id.to);




        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("monday").document("monday1");
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess (DocumentSnapshot value) {
                        if(value.exists()){
                           subject.setText(value.getString("className"));
                           classtype.setText(value.getString("classType"));
                           instructorname.setText(value.getString("instructor"));
                           classno.setText(value.getString("classNo"));
                           from.setText(value.getString("from"));
                           to.setText(value.getString("to"));
                        }else{

                            Toast.makeText(getContext(), "Document does not exist", Toast.LENGTH_SHORT).show();
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





//        subject.setText(monday.Subject);
//        instructorname.setText(monday.InstructorName);
//        classtype.setText(monday.classType);
//        classno.setText(monday.classNo);
//        from.setText(monday.From);
//        to.setText(monday.to);

        return  convertView;
    }

}