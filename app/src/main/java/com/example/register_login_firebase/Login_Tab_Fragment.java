package com.example.register_login_firebase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login_Tab_Fragment extends Fragment {



    EditText email, password;
    TextView forgetpass;
    SharedPreferences sharedPreferences;

    EditText profile;
    Button login;
    float v=0;
    private String[] titles = new String[]{"Login","Signup"};

    FirebaseAuth fAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);


        fAuth = FirebaseAuth.getInstance();
        email = root.findViewById(R.id.emailid);
        password = root.findViewById(R.id.password);
        forgetpass = root.findViewById(R.id.fpass);
        login = root.findViewById(R.id.submitbutton);
        profile = new EditText(getActivity());
        profile = root.findViewById(R.id.profile_hname);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        //Animation

        email.setTranslationX(800);
        password.setTranslationX(800);
        forgetpass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgetpass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        //Login

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailid = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                fAuth.signInWithEmailAndPassword(emailid, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Check Database
                            DocumentReference d = db.collection("Head").document(fAuth.getCurrentUser().getUid());
                            d.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if(task.isSuccessful()){
                                        DocumentSnapshot documentSnapshot = task.getResult();
                                        if(documentSnapshot.exists()){
                                            String name = documentSnapshot.getString("h_name");
                                            String relation = documentSnapshot.getString("relation");
                                            if (name.isEmpty()) {
                                                //Complete Profile Dialog
                                                completeProfile();
                                            }
                                            //Main Screen
                                            else {
                                                Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_SHORT).show();
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString(KEY_NAME, relation);
                                                editor.apply();
                                                String named = sharedPreferences.getString(KEY_NAME, null);
//                                                Log.d("Relation", named);
                                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                            }
                                        }
                                        else{
                                            Toast.makeText(getActivity(), "No document found" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });


                        }
                    }
                });
            }
        });


        //ForgetPassword
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), resetPassword.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return root;
    }

    private void completeProfile(){
        Toast.makeText(getActivity(), "Complete your profile!", Toast.LENGTH_SHORT).show();
        View vi = LayoutInflater.from(getActivity()).inflate(R.layout.activity_dialog_popup, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Complete Profile");
        builder.setView(vi);
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
