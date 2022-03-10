package com.example.register_login_firebase;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link familyTreeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class familyTreeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<FamilyModel> arrayFamily = new ArrayList<>();
    String relation;
    FloatingActionButton btnAdd;
    RecyclerFamilyAdapter adapter;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser user; String uid;
    RecyclerView recyclerView;
    ProgressDialog pd;
    SharedPreferences sharedPreferences;
    String user_name, user_num, user_relation, user_email;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";


    public familyTreeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment familyTreeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static familyTreeFragment newInstance(String param1, String param2) {
        familyTreeFragment fragment = new familyTreeFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pd = new ProgressDialog(getActivity());
        pd.setCancelable(false);
        pd.setMessage("Fetching Data");
        pd.show();

        recyclerView = view.findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        arrayFamily = new ArrayList<FamilyModel>();
        adapter = new RecyclerFamilyAdapter(getActivity(), arrayFamily);
        btnAdd = view.findViewById(R.id.addbutton);

        recyclerView.setAdapter(adapter);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);

        if(name != null){
            relation = name;
            Log.d("relation", relation);
            Log.d("Name", name);
        }
        if(relation.equals("head")){
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.add_update);

                    EditText ename = dialog.findViewById(R.id.ename);
                    EditText enumber = dialog.findViewById(R.id.enumber);
                    EditText eemail = dialog.findViewById(R.id.eemail);
                    EditText erelation = dialog.findViewById(R.id.erelation);

                    Button btn = dialog.findViewById(R.id.abtn);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            user_name = ename.getText().toString();
                            user_num = enumber.getText().toString();
                            user_email = eemail.getText().toString();
                            user_relation = erelation.getText().toString();


                                arrayFamily.add(new FamilyModel(user_name, user_email, user_relation));

                                adapter.notifyItemInserted(arrayFamily.size() - 1);
                                recyclerView.scrollToPosition(arrayFamily.size() - 1);

                                user = FirebaseAuth.getInstance().getCurrentUser();
                                uid = user.getUid();

                                mAuth.createUserWithEmailAndPassword(user_email, "irish432")
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    Users nuser = new Users(user_name, user_email, "irish432", Double.parseDouble(user_num), user_relation);
                                                    db.collection("Head").document(uid).collection("Family").document(user_email).set(nuser)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {

                                                                        Toast.makeText(getActivity(), "Success User created", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                }else{
                                                    Toast.makeText(getActivity(), "erroer" + task.getException(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });


                                dialog.dismiss();

                        }
                    });


                    dialog.show();


                }
            });


        }else{
            btnAdd.hide();
        }

        eventChangeListener();
    }



    private void eventChangeListener() {

        db.collection("Head")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){
                            if(pd.isShowing())
                                pd.dismiss();
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                arrayFamily.add(dc.getDocument().toObject(FamilyModel.class));
                            }

                            adapter.notifyDataSetChanged();
                            if(pd.isShowing())
                                pd.dismiss();
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_family_tree, container, false);
    }
}