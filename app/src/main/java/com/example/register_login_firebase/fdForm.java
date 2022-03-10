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
 * Use the {@link fdForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fdForm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText bank_nameFD, Branch_nameFD, holder_nameFD, investment_dateFD, investment_amountFD,
            maturity_dateFD, account_numberFD, maturity_amountFD,
            interest_rateFD, mode_of_interestFD, Mode_of_oprationFD, RemarkFD;
    Button Reset, Submit;

    FirebaseFirestore db;

    public fdForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fdForm.
     */
    // TODO: Rename and change types and number of parameters
    public static fdForm newInstance(String param1, String param2) {
        fdForm fragment = new fdForm();
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

        bank_nameFD = view.findViewById(R.id.Bank_nameFD);
        Branch_nameFD = view.findViewById(R.id.branch_nameFD);
        holder_nameFD = view.findViewById(R.id.HolderNameFD);
        maturity_dateFD = view.findViewById(R.id.Maturity_DateFD);
        investment_dateFD = view.findViewById(R.id.Investment_DateFD);
        investment_amountFD = view.findViewById(R.id.Investment_DateFD);
        account_numberFD = view.findViewById(R.id.Account_NumberFD);
        maturity_amountFD = view.findViewById(R.id.maturity_amountFD);
        interest_rateFD = view.findViewById(R.id.Intrest_RateFD);
        mode_of_interestFD = view.findViewById(R.id.Mode_Of_Interest_PaymentFD);
        Mode_of_oprationFD = view.findViewById(R.id.mode_of_OprationFD);
        RemarkFD = view.findViewById(R.id.remarkFD);
        Reset= view.findViewById(R.id.reset);
        Submit= view.findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fbank_nameFD = bank_nameFD.getText().toString().trim();
                String fBranch_nameFD = Branch_nameFD.getText().toString().trim();
                String fholder_nameFD = holder_nameFD.getText().toString().trim();
                String finvestment_dateFD = investment_dateFD.getText().toString().trim();
                String finvestment_amountFD = investment_amountFD.getText().toString().trim();
                String faccount_numberFD = account_numberFD.getText().toString().trim();
                String fMode_of_oprationFD = Mode_of_oprationFD.getText().toString().trim();
                String fmaturity_amountFD = maturity_amountFD.getText().toString().trim();
                String fmaturity_dateFD = maturity_dateFD.getText().toString().trim();
                String finterest_rateFD = interest_rateFD.getText().toString().trim();
                String fmode_of_interestFD = mode_of_interestFD.getText().toString().trim();
                String fRemarkFD = RemarkFD.getText().toString().trim();


                CollectionReference dbUserFD = db.collection("UserFD");

                UserFD userfd=new UserFD(
                        fbank_nameFD,
                        fBranch_nameFD,
                        fholder_nameFD,
                        finvestment_dateFD,
                        fmaturity_dateFD,
                        fmode_of_interestFD,
                        fMode_of_oprationFD,
                        fRemarkFD,
                        Integer.parseInt(finvestment_amountFD),
                        Integer.parseInt(faccount_numberFD),
                        Integer.parseInt(fmaturity_amountFD),
                        Integer.parseInt(finterest_rateFD)

                );

                dbUserFD.add(userfd)
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
        return inflater.inflate(R.layout.fragment_fd_form, container, false);
    }
}