package com.example.register_login_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegisterActivity extends AppCompatActivity {

    TextView alreadyHaveaccount;
    EditText headname, username, inputEmail, inputPassword, inputConformPassword, inputNumber;
    Button btnRegister;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser,user;

    //2
    LoginAdapter loginAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FloatingActionButton google, fb, mail;
    float v=0;
    private String[] titles = new String[]{"Login","Signup"};


    /*private EditText email;
    private EditText mobile;
    private EditText password;
    private Button submit;*/
    private FirebaseFirestore db;
    private  String uid;
    EditText profilename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        alreadyHaveaccount=findViewById(R.id.alreadyHaveaccount);

//        headname = findViewById(R.id.headname);
//        username = findViewById(R.id.usrname);
//        inputEmail=findViewById(R.id.inputEmail);
//        inputNumber=findViewById(R.id.inputNumber);
//        inputPassword=findViewById(R.id.inputPassword);
//        inputConformPassword=findViewById(R.id.inputConformPassword);
//        btnRegister = findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        user = FirebaseAuth.getInstance().getCurrentUser();
        //uid = mUser.getUid();

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null){
            uid = firebaseUser.getUid();
        }

        db = FirebaseFirestore.getInstance();

       // email = findViewById(R.id.inputEmail);
       // mobile = findViewById(R.id.inputNumber);
       // password = findViewById(R.id.inputPassword);
      //  submit = findViewById(R.id.btnRegister);

//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String u_name = username.getText().toString().trim();
//                String h_name = headname.getText().toString().trim();
//                String u_email = inputEmail.getText().toString().trim();
//                String u_password = inputPassword.getText().toString().trim();
//                String u_mobile = inputNumber.getText().toString().trim();
//
//
//                  db.collection("Head").document(u_name).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if(task.getResult().exists()){
//                            username.setError("Username Already Exist");
//                            username.requestFocus();
//                        }else{
//                            db.collection("Head").document(u_name).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                    DocumentSnapshot documentSnapshot = task.getResult();
//
//                                    if(documentSnapshot.get("u_email")==u_name){
//                                        Toast.makeText(RegisterActivity.this, "Already exists", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                            PerformAuth();
//                            CollectionReference dbProducts = db.collection("Head");//.document(uid);
//
//                            Users user = new Users(
//                                    u_name,
//                                    h_name,
//                                    u_email,
//                                    u_password,
//                                    Double.parseDouble(u_mobile)
//                            );
//
//                            /*dbProducts.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                        Toast.makeText(RegisterActivity.this, "Registeration Successfull" + uid, Toast.LENGTH_SHORT).show();
//                                }
//                            })*/
//
//                                    dbProducts.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//                                            Toast.makeText(RegisterActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
////                          Toast.makeText(RegisterActivity.this, "Failed to Restister", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//
//                        }
//                    }
//                });
//
//                //PerformAuth();
//
//            }
//        });


//        alreadyHaveaccount.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
//            }
//        });

       /* btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });*/

        //2

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewpager);
        loginAdapter = new LoginAdapter(this);
//        google = findViewById(R.id.fab_google);
//        fb = findViewById(R.id.fab_fb);
//        mail = findViewById(R.id.fab_gmail);

        viewPager2.setAdapter(loginAdapter);
        new TabLayoutMediator(tabLayout, viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();


//        fb.setTranslationY(300);
//        google.setTranslationY(300);
//        mail.setTranslationY(300);
        tabLayout.setTranslationY(300);

//        fb.setAlpha(v);
//        google.setAlpha(v);
//        mail.setAlpha(v);
        tabLayout.setAlpha(v);

//        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
//        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
//        mail.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();

        profilename = findViewById(R.id.profile_hname);
//        String name =  profilename.getText().toString().trim();
//        if(name.isEmpty()) {
////                                Intent intent2 = new Intent(getActivity(), ProfileActivity.class);
////                                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
////                                startActivity(intent2);
//
//            View vi = LayoutInflater.from(RegisterActivity.this).inflate(R.layout.activity_profile, null, false);
//            profilename = vi.findViewById(R.id.profilenameedit);
//            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//
//            builder.setTitle("Complete Profile");
//            builder.setView(vi);
//            builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            AlertDialog dialog = builder.create();
//            dialog.show();
//        }show

    }

    private void PerformAuth() {
        String hname=headname.getText().toString();
        String uname = username.getText().toString();
        String email=inputEmail.getText().toString();
        String number=inputNumber.getText().toString();
        String password=inputPassword.getText().toString();
        String confirmPassword=inputConformPassword.getText().toString();
        if(!email.matches(emailPattern)){
            inputEmail.setError("Enter Correct Email");
        }else if(number.length() != 10){
            inputNumber.setError("Enter Correct Mobile Number");
        } else if(password.isEmpty() || password.length()<6){
            inputPassword.setError("Enter Proper Password");
        }else if(!password.equals(confirmPassword)) {
            inputConformPassword.setError("Password do not match");
        }


        else{
            progressDialog.setMessage("Wait while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();
//                        User user = new User(email, password, number);
//                        FirebaseDatabase.getInstance().getReference("User")
//                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if(task.isSuccessful()){
//                                    progressDialog.dismiss();
//                                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                                    sendUserToNextActivity();
//                                }else{
//                                    progressDialog.dismiss();
//                                    Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+ task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(RegisterActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
    }
}