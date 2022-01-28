package com.example.register_login_firebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link insuranceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class insuranceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseFirestore db;

    public insuranceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment insuranceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static insuranceFragment newInstance(String param1, String param2) {
        insuranceFragment fragment = new insuranceFragment();
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
        return inflater.inflate(R.layout.fragment_insurance, container, false);
    }

    private TextView cName, bName, Plan, Policy, Premium, pDate, noPremium, maturityA, maturityD, remark;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        cName = view.findViewById(R.id.company_nameview);
        bName = view.findViewById(R.id.branch_nameview);
        Plan = view.findViewById(R.id.planview);
        Policy = view.findViewById(R.id.policy_numberview);
        Premium = view.findViewById(R.id.premium_amountview);
        pDate = view.findViewById(R.id.policy_dateview);
        noPremium = view.findViewById(R.id.no_of_premiumview);
        maturityA = view.findViewById(R.id.maturity_amountview);
        maturityD = view.findViewById(R.id.maturity_dateview);
        remark = view.findViewById(R.id.remarkview);

        DocumentReference docRef = db.collection("Investments").document("Insurance");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("exists", "DocumentSnapshot data: " + document.getData());
                        cName.setText(document.getString("company_name"));
                        bName.setText(document.getString("branch_name"));
                    } else {
                        Log.d("not exists", "No such document");
                    }
                } else {
                    Log.d("db error", "get failed with ", task.getException());
                }
            }
        });
    }
}