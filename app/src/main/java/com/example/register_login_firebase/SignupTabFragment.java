package com.example.register_login_firebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SignupTabFragment extends Fragment {


    EditText hname, email, mno, password, cpassword;
    Button signup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    DatabaseReference reference;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_fragment_tab, container, false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Head_Name");
        hname = root.findViewById(R.id.headname_reg);
        email = root.findViewById(R.id.email_reg);
        mno = root.findViewById(R.id.mobile_reg);
        password = root.findViewById(R.id.password_reg);
        cpassword = root.findViewById(R.id.conf_password_reg);
        signup = root.findViewById(R.id.submitbutton);
        db = FirebaseFirestore.getInstance();

        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(getActivity(), "Error in verification " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Intent intent = new Intent(getActivity(), activity_otp.class);
                intent.putExtra("mobile", mno.getText().toString());
                intent.putExtra("otp", s);
                startActivity(intent);
            }
        };

    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String headname = hname.getText().toString().trim();
            final String emailid = email.getText().toString().trim();
            final String mobile = mno.getText().toString().trim();
            final String pass = password.getText().toString().trim();
            final String cpass = cpassword.getText().toString().trim();

            if(!emailid.matches(emailPattern)){
                email.setError("Incorrect Email id");
            }else if(mobile.length() != 10){
                mno.setError("Incorrect Mobile Number");
            }else if(pass.isEmpty() || pass.length()<6){
                password.setError("Password should not be empty or less than 6 characters");
            }else if(!pass.equals(cpass)){
                cpassword.setError("Passwords do not match");
            }
            else{
                //progress bar

                //Create User
                mAuth.createUserWithEmailAndPassword(emailid, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Users head = new Users(headname, emailid, pass, Double.parseDouble(mobile), "(Head)");

                                   db.collection("Head").document(mAuth.getCurrentUser().getUid()).set(head)
                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) {
                                                   if(task.isSuccessful()) {
                                                       insertData();
//                                                       Toast.makeText(getActivity(), "User Create Success !", Toast.LENGTH_SHORT).show();
                                                       //Check Database
                                                       DocumentReference d = db.collection("Head").document(mAuth.getCurrentUser().getUid());
                                                       d.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                           @Override
                                                           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                               if(task.isSuccessful()){
                                                                   DocumentSnapshot documentSnapshot = task.getResult();
                                                                   if(documentSnapshot.exists()){
                                                                       String name = documentSnapshot.getString("f_name");
                                                                       if(name.isEmpty()){
                                                                           completeProfile();
                                                                           clear();
                                                                       }
                                                                       else{
                                                                           //Toast.makeText(getActivity(), "Successfull", Toast.LENGTH_SHORT).show();
                                                                       }
                                                                   }
                                                               }
                                                           }
                                                       });
                                                       //
                                                   }
                                               }
                                           })

                                           .addOnFailureListener(new OnFailureListener() {
                                               @Override
                                               public void onFailure(@NonNull Exception e) {
                                                   Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                                               }
                                           });
                                }
                            }
                        })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    });

//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!mno.getText().toString().trim().isEmpty()){
//                    if(mno.getText().toString().trim().length()==10) {
//
//                        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
//                                .setPhoneNumber("+91" + mno.getText().toString())
//                                .setTimeout(60L, TimeUnit.SECONDS)
//                                .setActivity(getActivity())
//                                .setCallbacks(mCallBacks)
//                                .build();
//
//                        PhoneAuthProvider.verifyPhoneNumber(options);
//
//                    }else{
//                        Toast.makeText(getActivity(), "Enter Correct Mobile No", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(getActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        return root;
    }

    private void clear() {
        email.setText("");
        hname.setText("");
        password.setText("");
        cpassword.setText("");
        mno.setText("");
    }

    private void completeProfile(){
        Toast.makeText(getActivity(), "Complete your profile!", Toast.LENGTH_SHORT).show();
        View vi = LayoutInflater.from(getActivity()).inflate(R.layout.activity_dialog_popup, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(vi);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "It's mandatory to complete profile", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void insertData(){
        db.collection("Head").document(mAuth.getCurrentUser().getUid()).update("f_name", "").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
