package com.example.register_login_firebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bankAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bankAccountFragment extends Fragment implements RecyclerManageAdapter.onManageListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String KEY = "textviewvalue";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button submit;
    private TextView number;
    private EditText find;

    CardView formview;

    FirebaseFirestore db;
    Button Submit;
    FirebaseUser user; String uid;
    ProgressDialog pd;
    ArrayList<ManageModel> arrayManage = new ArrayList<>();
    RecyclerManageAdapter adapter;
    RecyclerView recyclerViewAccount;
    View view;

    public bankAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bankAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static bankAccountFragment newInstance(String param1, String param2) {
        bankAccountFragment fragment = new bankAccountFragment();
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

        recyclerViewAccount = view.findViewById(R.id.accountrecycleview);
        recyclerViewAccount.setHasFixedSize(true);
        recyclerViewAccount.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayManage = new ArrayList<ManageModel>();
        adapter = new RecyclerManageAdapter(getActivity(), arrayManage, this);
        recyclerViewAccount.setAdapter(adapter);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

//        formview = view.findViewById(R.id.accountrecycleview);

//        submit = view.findViewById(R.id.addbtn);
//        number = view.findViewById(R.id.text9);
//        find = view.findViewById(R.id.edittext);
//        find.setText(getvalue());

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String findno = find.getText().toString().trim();
//                number.setText(findno);

//                saveFormEditText(number.getText().toString());
//                Bundle bundle = new Bundle();
//                bundle.putString("key",number.getText().toString());
//
//                fdFragment fragment = new fdFragment();
//                fragment.setArguments(bundle);
//                Navigation.findNavController(view).navigate(R.id.toFD);
//
//            }
//        });
//
//        number.setText(getvalue());

        Submit= view.findViewById(R.id.submit_account);
    //        Submit.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    ////                eventChangeListener();
    //            }
    //        });


        arrayManage.add(new ManageModel("ICICI Bank", "12342"));

        recyclerViewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
//                Navigation.findNavController(recyclerViewAccount).navigate(R.id.toBankAccountForm);
            }
        });

    }

    private void eventChangeListener() {

        db.collection("Head").document(uid).collection("Investments")
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
                                arrayManage.add(dc.getDocument().toObject(ManageModel.class));
                            }

                            adapter.notifyDataSetChanged();
                            if(pd.isShowing())
                                pd.dismiss();
                        }
                    }
                });
    }

    private String getvalue(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String savedvalue = sharedPreferences.getString(KEY, "");

        return savedvalue;
    }

    private void saveFormEditText(String text){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY, text);
        editor.apply();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bank_account, container, false);

//        Button button = view.findViewById(R.id.addbtn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText editText = view.findViewById(R.id.edittext);
//                Bundle bundle = new Bundle();
//                bundle.putString("key", editText.getText().toString());
//                getParentFragmentManager().setFragmentResult("dataFrom1", bundle);
//                editText.setText("");
//            }
//        });
        return view;
    }

    @Override
    public void onManageClick(int position) {
        arrayManage.get(position);
        Toast.makeText(getActivity(), "Hellos", Toast.LENGTH_SHORT).show();
    }
}