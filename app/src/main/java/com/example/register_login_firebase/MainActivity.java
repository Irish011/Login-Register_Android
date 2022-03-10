package com.example.register_login_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView createnewAccount, forgotpassword;
    EditText inputEmail, inputPassword, profilename;
    Button btnLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+", uid;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;

    FirebaseAuth mAuth;
    FirebaseUser mUser,user;

    //2
    LoginAdapter loginAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FloatingActionButton google, fb, mail;
    float v=0;
    private String[] titles = new String[]{"Login","Signup"};

    //3
    Animation zoom;
    ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2

//        tabLayout = findViewById(R.id.tab_layout);
//        viewPager2 = findViewById(R.id.viewpager);
//        loginAdapter = new LoginAdapter(this);
//        google = findViewById(R.id.fab_google);
//        fb = findViewById(R.id.fab_fb);
//        mail = findViewById(R.id.fab_gmail); //2

//        tabLayout.addTab(tabLayout.newTab().setText("Login"));
//        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//2 undo
//        viewPager2.setAdapter(loginAdapter);
//        new TabLayoutMediator(tabLayout, viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();
//
//
//        fb.setTranslationY(300);
//        google.setTranslationY(300);
//        mail.setTranslationY(300);
//        tabLayout.setTranslationY(300);
//
//        fb.setAlpha(v);
//        google.setAlpha(v);
//        mail.setAlpha(v);
//        tabLayout.setAlpha(v);
//
//        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
//        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
//        mail.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
//        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        //no//final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());

        //1
        //createnewAccount = findViewById(R.id.createNewAccount);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        inputEmail = findViewById(R.id.inputEmail);
//        inputPassword = findViewById(R.id.inputPassword);
        //btnLogin = findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //forgotpassword = (TextView) findViewById(R.id.fpassword);

//        forgotpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, resetPassword.class));
//            }
//        });

//        createnewAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
//            }
//        });

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                performLogin();
//            }
//        });

        //3
        zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        img = findViewById(R.id.splash);
        img.startAnimation(zoom);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        },4500);
    }


    private void performLogin() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (!email.matches(emailPattern)) {
            inputEmail.setError("Enter Correct Email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("Enter Proper Password");
        } else {
            progressDialog.setMessage("Wait while Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, ""+ task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }



    private void sendUserToNextActivity() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        profilename = findViewById(R.id.profile_hname);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        DocumentReference docRf = db.collection("Head").document(uid);
        docRf.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        String name = document.getString("h_name");
                        if(name.isEmpty()){
                            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }else{
                        Log.d("Not Exists", "no such document");
                    }
                }else {
                    Log.d("db error", "get failed with ", task.getException());
                }
            }
        });
//        String name = profilename.getText().toString().trim();
//        if(name.isEmpty()){
//            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }else {
//            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
    }

    @Override
    public void onClick(View v) {

    }

}