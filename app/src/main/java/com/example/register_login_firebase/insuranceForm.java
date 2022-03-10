package com.example.register_login_firebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link insuranceForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class insuranceForm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText Company_name, Branch_name, Plan, Policy_number, Premium_amount,
            Policy_date, No_of_premium, Maturity_amount,
            Maturity_date, Sum_assured, Mode_of_premium, Remark;
    Button Reset, Submit;
    String text;
    FirebaseFirestore db;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        db=FirebaseFirestore.getInstance();

        Company_name=view.findViewById(R.id.Company_name);
        Branch_name=view.findViewById(R.id.branch_name);
        Plan=view.findViewById(R.id.Plan);
        Policy_date=view.findViewById(R.id.policy_date);
        Policy_number=view.findViewById(R.id.Policy_Number);
        Premium_amount=view.findViewById(R.id.Premium_Amount);
        No_of_premium=view.findViewById(R.id.no_of_premium);
        Maturity_amount=view.findViewById(R.id.maturity_amount);
        Maturity_date=view.findViewById(R.id.maturity_date);
        Sum_assured=view.findViewById(R.id.sum_assured);
        Mode_of_premium=view.findViewById(R.id.mode_of_premium);
        Remark=view.findViewById(R.id.remark);
        Reset=view.findViewById(R.id.resetview);
        Submit=view.findViewById(R.id.submitview);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fCompany_name = Company_name.getText().toString().trim();
                String fBranch_name = Branch_name.getText().toString().trim();
                String fPlan = Plan.getText().toString().trim();
                String fPolicy_date = Policy_date.getText().toString().trim();
                String fPolicy_number = Policy_number.getText().toString().trim();
                String fPremium_amount = Premium_amount.getText().toString().trim();
                String fNo_of_premium = No_of_premium.getText().toString().trim();
                String fMaturity_amount = Maturity_amount.getText().toString().trim();
                String fMaturity_date = Maturity_date.getText().toString().trim();
                String fSum_assured = Sum_assured.getText().toString().trim();
                String fMode_of_premium = Mode_of_premium.getText().toString().trim();
                String fRemark = Remark.getText().toString().trim();



                CollectionReference dbUser = db.collection("User");

                UserInsurance user=new UserInsurance(
                        fCompany_name,
                        fBranch_name,
                        fPlan,
                        fPolicy_date,
                        fMaturity_date,
                        fMode_of_premium,
                        fRemark,
                        text,
                        Integer.parseInt(fPolicy_number),
                        Integer.parseInt(fPremium_amount),
                        Integer.parseInt(fNo_of_premium),
                        Integer.parseInt(fMaturity_amount),
                        Integer.parseInt(fSum_assured)

                );

                dbUser.add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getActivity(),"User Added",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });
    }

    public insuranceForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment insuranceForm.
     */
    // TODO: Rename and change types and number of parameters
    public static insuranceForm newInstance(String param1, String param2) {
        insuranceForm fragment = new insuranceForm();
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
        return inflater.inflate(R.layout.fragment_insurance_form, container, false);
    }
}