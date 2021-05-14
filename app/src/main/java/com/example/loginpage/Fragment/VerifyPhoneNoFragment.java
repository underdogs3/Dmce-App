package com.example.loginpage.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpage.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;


public class VerifyPhoneNoFragment extends Fragment {
    Button verify_btn;
    EditText otp_entered;
    String phoneNo,countryCodee="+91";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    String veri;

    public VerifyPhoneNoFragment () {
        // Required empty public constructor
    }


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_verify_phone_no, container, false);
        otp_entered = view.findViewById(R.id.otp_entered);
        verify_btn =view.findViewById(R.id.verify_button);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("students").document(userId);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess (DocumentSnapshot value) {
                        if(value.exists()){
                            phoneNo =value.getString("phone_no").trim();
                            sendVerificationCode(phoneNo);

                        }else{

                            Toast.makeText(getContext(),"Document does not exist", Toast.LENGTH_SHORT).show();
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



        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Toast.makeText(getActivity(), "Verifing....", Toast.LENGTH_SHORT).show();
                String code = otp_entered.getText().toString().trim();
                if(veri!=null){
                    PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(veri,code);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete (@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){
                                        UpdatePasswordFragment fragment = new UpdatePasswordFragment();
                                        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                                        ft.replace(R.id.frame_layout,fragment);
                                        ft.commit();
                                        Toast.makeText(getContext(), "OTP Verified", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getContext(), "Entered OTP is invalid   ", Toast.LENGTH_SHORT).show();
                                    }
                                    if(task.isCanceled()){
                                        Toast.makeText(getContext(), "Entered OTP is invalid   ", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });



//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//               "+91"+phoneNo,
//              60,
//              TimeUnit.SECONDS,
//              getActivity(),
//              new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                  @Override
//                  public void onVerificationCompleted (@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                  }
//
//                  @Override
//                  public void onVerificationFailed (@NonNull FirebaseException e) {
//                      Toast.makeText(getContext(),"+91"+phoneNo+ e.getMessage(), Toast.LENGTH_SHORT).show();
//                  }
//
//                  @Override
//                  public void onCodeSent (@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                    veri = verificationId;
//                      Toast.makeText(getContext(), "Code send", Toast.LENGTH_SHORT).show();
//                  }
//              }
//      );


        return view;
    }
    private void sendVerificationCode(final String phoneNo) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phoneNo,
                60,
                TimeUnit.SECONDS,
                requireActivity(),
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted (@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed (@NonNull FirebaseException e) {
                        Toast.makeText(getContext(),"+91"+phoneNo+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent (@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        veri = verificationId;
                        Toast.makeText(getContext(), "Code send", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

}