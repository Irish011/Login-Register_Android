package com.example.register_login_firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link manageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class manageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText cName;
    private EditText bName;
    private EditText Plan;
    private EditText PolicyNo;
    private EditText P_amt;
    private EditText p_Date;
    private EditText Premium;
    private EditText m_Amt;
    private EditText m_Date;
    private EditText remark;
    private Button submit, reset;

    ProgressDialog progressDialog;

    private FirebaseFirestore db;

    public manageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment manageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static manageFragment newInstance(String param1, String param2) {
        manageFragment fragment = new manageFragment();
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
        return inflater.inflate(R.layout.fragment_manage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    db = FirebaseFirestore.getInstance();
    progressDialog = new ProgressDialog(getActivity());
    cName = view.findViewById(R.id.company_name);
    bName = view.findViewById(R.id.branch_name);
    Plan = view.findViewById(R.id.plan);
    PolicyNo = view.findViewById(R.id.policy_number);
    P_amt = view.findViewById(R.id.premium_amount);
    p_Date = view.findViewById(R.id.policy_date);
    Premium = view.findViewById(R.id.no_of_premium);
    m_Amt = view.findViewById(R.id.maturity_amount);
    m_Date = view.findViewById(R.id.maturity_date);
    remark = view.findViewById(R.id.remark);
    submit = view.findViewById(R.id.submit);
    reset = view.findViewById(R.id.reset);

reset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        clear();
    }
});
    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String company_name = cName.getText().toString().trim();
            String branch_name = bName.getText().toString().trim();
            String plan = Plan.getText().toString().trim();
            String policy_number = PolicyNo.getText().toString().trim();
            String premium_amount = P_amt.getText().toString().trim();
            String premium_date = p_Date.getText().toString().trim();
            String no_premium = Premium.getText().toString().trim();
            String maturity_amount = m_Amt.getText().toString().trim();
            String maturity_date = m_Date.getText().toString().trim();
            String Remark = remark.getText().toString().trim();

            if (company_name.isEmpty()) {
                cName.setError("Enter Name");
                cName.requestFocus();
            } else if (branch_name.isEmpty()) {
                bName.setError("Enter Branch");
                bName.requestFocus();
            } else if (plan.isEmpty()) {
                Plan.setError("Enter Plan");
                Plan.requestFocus();
            } else if (policy_number.isEmpty()) {
                PolicyNo.setError("Enter Plan");
                PolicyNo.requestFocus();
            } else if (premium_amount.isEmpty()) {
                P_amt.setError("Enter Plan");
                P_amt.requestFocus();
            } else if (premium_date.isEmpty()) {
                p_Date.setError("Enter Plan");
                p_Date.requestFocus();
            } else if (no_premium.isEmpty()) {
                Premium.setError("Enter Plan");
                Premium.requestFocus();
            } else if (maturity_amount.isEmpty()) {
                m_Amt.setError("Enter Plan");
                m_Amt.requestFocus();
            } else if (maturity_date.isEmpty()) {
                m_Date.setError("Enter Plan");
                m_Date.requestFocus();
            } else if (Remark.isEmpty()) {
                remark.setError("Enter Plan");
                remark.requestFocus();
            } else {
                DocumentReference dbInvestment = db.collection("Investments").document("Insurance");
                //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                Investments investments = new Investments(
                        company_name,
                        branch_name,
                        plan,
                        Double.parseDouble(policy_number),
                        Double.parseDouble(premium_amount),
                        premium_date,
                        Double.parseDouble(no_premium),
                        Double.parseDouble(maturity_amount),
                        maturity_date,
                        Remark
                );
                progressDialog.setMessage("Please Wait...");
                progressDialog.setTitle("Saving Investment");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                //Fragment ff = new portfolioFragment();
                manageFragment mf = new manageFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                dbInvestment.set(investments)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void documentReference) {
                                progressDialog.dismiss();
                                fragmentTransaction.remove(mf);
                           // fragmentTransaction.remove(R.id.fragmentContainerView, mf);
                            //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                           // fragmentTransaction.addToBackStack(null).commit();
                                //fragmentTransaction.commit();
                                //startActivity(new Intent(getActivity(), MainActivity.class));
                                 clear();
                                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        }
    });


    }

    private void clear(){
            cName.setText(null);
            bName.setText(null);
            Plan.setText(null);
            PolicyNo.setText(null);
            P_amt.setText(null);
            p_Date.setText(null);
            Premium.setText(null);
            m_Amt.setText(null);
            m_Date.setText(null);
            remark.setText(null);
    }
}