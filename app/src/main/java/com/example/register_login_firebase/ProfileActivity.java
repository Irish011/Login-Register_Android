package com.example.register_login_firebase;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileActivity extends AppCompatActivity {

    ImageView button;
    EditText fname, hname, mobno;
    Button button2, button3;
    String uID, oldfname;

    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    ActivityResultLauncher<String> mTakePhoto;
    Uri imageUri;
    FirebaseAuth fAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fAuth = FirebaseAuth.getInstance();
        fname = findViewById(R.id.profile_fname);
        hname = findViewById(R.id.profile_hname);
        mobno = findViewById(R.id.profile_mno);
        firebaseUser = fAuth.getCurrentUser();
        uID = firebaseUser.getUid();

        storage = FirebaseStorage.getInstance();
        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if(result != null){
                            Toast.makeText(ProfileActivity.this, "Photo", Toast.LENGTH_SHORT).show();
                            imageUri = result;
                        }
                    }
                }
        );
        button2 = findViewById(R.id.profile_uploadbtn);
        button3 = findViewById(R.id.profilesubmitbtn);
        button = findViewById(R.id.profile_uploadicon);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("upload");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTakePhoto.launch("image/*");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Clicked", Toast.LENGTH_SHORT).show();

                if(imageUri != null){
                    StorageReference st1 = storage.getReference().child("images/" + UUID.randomUUID().toString());

                    st1.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        }
                    });

                }
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String familyname = fname.getText().toString();
                String headname = hname.getText().toString();

//                fname.setText("");
//                hname.setText("");


//              db.collection("Head").document(uID)
//                      .addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                          @Override
//                          public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                              oldfname = value.getString("f_name");
//                          }
//                      });

                updateData(familyname, headname);
            }
        });


    }

    private void updateData(String familyname, String headname) {
        Map<String, Object> userDetail = new HashMap<>();
        userDetail.put("f_name", familyname);
        userDetail.put("h_name", headname);


                    db.collection("Head").document(uID).update(userDetail)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(ProfileActivity.this, "Success Updated", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ProfileActivity.this, "Failed updation", Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }
