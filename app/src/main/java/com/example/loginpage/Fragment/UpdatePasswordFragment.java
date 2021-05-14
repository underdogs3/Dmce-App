package com.example.loginpage.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpage.MainActivity;
import com.example.loginpage.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UpdatePasswordFragment extends Fragment {

    EditText newPassword,confirmPassword;
    Button updateBtn;
    String newPassword_str,confirmPassword_str;
    AuthCredential credential = EmailAuthProvider.getCredential(MainActivity.inputName,MainActivity.inputPassword);
    public UpdatePasswordFragment () {
        // Required empty public constructor
    }



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_update_password, container, false);

        newPassword=view.findViewById(R.id.newPassword);
        confirmPassword=view.findViewById(R.id.confirmPassword);

        updateBtn=view.findViewById(R.id.update_password_btn);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                newPassword_str=newPassword.getText().toString();
                confirmPassword_str=confirmPassword.getText().toString();
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                if(newPassword_str.equals(confirmPassword_str)){
//                    user.reauthenticate(credential)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete (@NonNull Task<Void> task) {
//                                    if(task.isSuccessful()){
//                                        user.updatePassword(newPassword_str).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete (@NonNull Task<Void> task) {
//                                                if(task.isSuccessful()) {
////                                                    MainActivity.inputPassword = newPassword_str;
//                                                    Toast.makeText(getActivity(), "Password Updated!", Toast.LENGTH_SHORT).show();
//                                                    showFragment(new ProfileFragment());
//
//                                                }else{
//                                                    Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                        });
//
//                                    }else{
//                                        Toast.makeText(getActivity(), "1212 "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//
//                                }
//                            });
                    Toast.makeText(getActivity(), "Updating Password...", Toast.LENGTH_SHORT).show();
                    showFragment(new ProfileFragment());
                    Toast.makeText(getActivity(), "Password Updated!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Passwords does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
    private void showFragment (Fragment fragment) {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout,fragment);
        ft.commit();
    }

}