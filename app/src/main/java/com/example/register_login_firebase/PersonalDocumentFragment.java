package com.example.register_login_firebase;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalDocumentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalDocumentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    StorageReference storageReference;
    private Uri ImageUri;
    DatabaseReference databaseReference;

    Dialog dialog;
    int SELECT_DOCUMENT = 200;
    FloatingActionButton addDoc;
    ArrayList<PersonalModel> arrayPersonal = new ArrayList<>();
    RecyclerView recyclerView;
    PersonalRecyclerAdapter adapter;



    private String productRandomKey,saveCurrentDate, saveCurrentTime ,downloadImageUrl;
    public PersonalDocumentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalDocumentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalDocumentFragment newInstance(String param1, String param2) {
        PersonalDocumentFragment fragment = new PersonalDocumentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_document, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.personalrecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        arrayPersonal = new ArrayList<PersonalModel>();
        adapter = new PersonalRecyclerAdapter(getActivity(), arrayPersonal);

        recyclerView.setAdapter(adapter);

        arrayPersonal.add(new PersonalModel("Aadhar Card"));
        addDoc = view.findViewById(R.id.document_add);

        storageReference = FirebaseStorage.getInstance().getReference().child("Photos");
        databaseReference = FirebaseDatabase.getInstance().getReference("pdf");

//        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("MyUpload");
//        databaseRef = FirebaseDatabase.getInstance().getReference().child("MyUpload");
//        reference = FirebaseDatabase.getInstance().getReference("Family");


        addDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.upload_doc);
                dialog.show();

                EditText doc_name = dialog.findViewById(R.id.pdoc_name);
                EditText doc_type = dialog.findViewById(R.id.pdoc_type);
                TextView udoc_name = dialog.findViewById(R.id.select_doc_name);

                Button doc_button = dialog.findViewById(R.id.select_document);

                doc_button.setOnClickListener(new View.OnClickListener() {

                    final String docname = doc_name.getText().toString();
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    intent.setType("application/pdf");
//                    someActivityResultLauncher.launch(intent);
//                    udoc_name.setText(docname + ".pdf");
//                    dialog.show();
                    Intent i = new Intent();
                    i.setType("application/pdf");
                    i.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(i, "Select Document"), SELECT_DOCUMENT);

                    ValidateProductData();
                }
            });

            }

        });

//        addDoc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    private void ValidateProductData() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;


        final StorageReference filePath = storageReference.child(ImageUri.getLastPathSegment() + productRandomKey + ".pdf");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(AdminProductActivity.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();

                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

//                            Toast.makeText(AdminProductActivity.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Uri uri = result.getData().getData();
                    StorageReference ref2 = storageReference.child("Document Files");
                    DatabaseReference ref = databaseReference.child("Images").push();
                    final String pushID = ref.getKey();
                    final StorageReference path = ref2.child(pushID + ".pdf");

                }
            }
    );
}