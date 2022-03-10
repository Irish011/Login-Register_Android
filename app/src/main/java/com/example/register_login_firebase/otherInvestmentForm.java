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
 * Use the {@link otherInvestmentForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class otherInvestmentForm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText Company_NameOI, holder_nameOI, account_numberOI, investment_dateOI, amountOI,
            Maturity_dateOI, investment_typeOI, RemarkOI;
    Button Reset, Submit;

    FirebaseFirestore db;

    public otherInvestmentForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment otherInvestmentForm.
     */
    // TODO: Rename and change types and number of parameters
    public static otherInvestmentForm newInstance(String param1, String param2) {
        otherInvestmentForm fragment = new otherInvestmentForm();
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

        db=FirebaseFirestore.getInstance();

        Company_NameOI = view.findViewById(R.id.Company_nameOI);
        holder_nameOI = view.findViewById(R.id.Holder_NameOI);
        account_numberOI = view.findViewById(R.id.Account_NumberOI);
        investment_dateOI = view.findViewById(R.id.Investment_DateOI);
        amountOI = view.findViewById(R.id.AmountOI);
        Maturity_dateOI = view.findViewById(R.id.Maturity_DateOI);
        investment_typeOI = view.findViewById(R.id.Investment_TypeOI);
        RemarkOI = view.findViewById(R.id.remarkOI);
        Reset= view.findViewById(R.id.reset);
        Submit= view.findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fCompany_nameOI = Company_NameOI.getText().toString().trim();
                String fholder_nameOI = holder_nameOI.getText().toString().trim();
                String finvestment_dateOI = investment_dateOI.getText().toString().trim();
                String faccount_numberOI = account_numberOI.getText().toString().trim();
                String famountOI = amountOI.getText().toString().trim();
                String fMaturity_dateOI = Maturity_dateOI.getText().toString().trim();
                String finvestment_typeOI = investment_typeOI.getText().toString().trim();
                String fRemarkOI = RemarkOI.getText().toString().trim();



                CollectionReference dbUserOI = db.collection("UserOI");

                UserOI useroi=new UserOI(
                        fCompany_nameOI,
                        fholder_nameOI,
                        finvestment_dateOI,
                        fMaturity_dateOI,
                        finvestment_typeOI,
                        fRemarkOI,
                        Integer.parseInt(faccount_numberOI),
                        Integer.parseInt(famountOI)

                );

                dbUserOI.add(useroi)
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other_investment_form, container, false);
    }
}