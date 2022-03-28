package com.example.register_login_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class HomeActivity extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private String uID;
    private Button logout;

    FirebaseFirestore fstore;
    TextView Name, USname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //BottomBar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());

        //SideBar
        MaterialToolbar toolbar = findViewById(R.id.topbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer);


        //displayFragment
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        NavHostFragment navhostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(navigationView, navhostFragment.getNavController());

        //SideBar Dynamic
        View hView = navigationView.getHeaderView(0);
        RelativeLayout name = (RelativeLayout) hView.findViewById(R.id.user_profile);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Head");
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null){
            uID = firebaseUser.getUid();
        }
        //uID = user.getUid();
        fstore = FirebaseFirestore.getInstance();
        Name = hView.findViewById(R.id.nameprofiledisplay);
        USname = hView.findViewById(R.id.username);

        DocumentReference documentReference = fstore.collection("Head").document(uID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                Name.setText(value.getString("h_name"));
                USname.setText(value.getString("f_name"));

                Log.d("name", uID);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

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


//    @Override
//    public void onBackPressed() {
////
//        super.onBackPressed();
////        int id = R.id.investmentFragment;
////        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
////        NavHostFragment navhostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
////        NavigationUI.setupWithNavController(navigationView, navhostFragment.getNavController());
////        navhostFragment.getNavController().getCurrentDestination().getId(id) == R.id.investmentFragment;
//
//        getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
//
//
//    }
}