package com.example.register_login_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String uID;
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        uID = user.getUid();

       // final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
       // final TextView emailTextView = (TextView) findViewById(R.id.emailid);
       // final TextView passwordTextView = (TextView) findViewById(R.id.password);
       // logout = (Button) findViewById(R.id.logout);

       // reference.child(uID).addListenerForSingleValueEvent(new ValueEventListener() {
         //   @Override
           // public void onDataChange(@NonNull DataSnapshot snapshot) {
             //   User userProfile = snapshot.getValue(User.class);
               // if(userProfile != null){
                 //   String userEmail = userProfile.email;
                   // String userPassword = userProfile.password;

//                    greetingTextView.setText("Welcome, " + userEmail);
  //                  emailTextView.setText(userEmail);
    //                passwordTextView.setText(userPassword);
      //          }
        //    }

            //@Override
            //public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(HomeActivity.this, "Something went Wrong", Toast.LENGTH_LONG).show();
            //}
        //});

        //logout.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();
                //Toast.makeText(HomeActivity.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(HomeActivity.this, MainActivity.class));
            //}
        //});
    }
}