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
 * Use the {@link creditCardForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class creditCardForm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText bank_nameCC, card_numberCC, card_typeCC, limitCC, validatyCC, renewal_amountCC, RemarkCC;
    Button Reset, Submit;

    FirebaseFirestore db;

    public creditCardForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment creditCardForm.
     */
    // TODO: Rename and change types and number of parameters
    public static creditCardForm newInstance(String param1, String param2) {
        creditCardForm fragment = new creditCardForm();
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
        return inflater.inflate(R.layout.fragment_credit_card_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db=FirebaseFirestore.getInstance();

        bank_nameCC = view.findViewById(R.id.Bank_nameCC);
        card_numberCC = view.findViewById(R.id.Card_NumberCC);
        card_typeCC = view.findViewById(R.id.Card_TypeCC);
        limitCC = view.findViewById(R.id.LimitCC);
        validatyCC = view.findViewById(R.id.ValidityCC);
        renewal_amountCC = view.findViewById(R.id.Renewal_AmountCC);
        RemarkCC = view.findViewById(R.id.remarkCC);
        Reset= view.findViewById(R.id.reset);
        Submit= view.findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fbank_nameCC = bank_nameCC.getText().toString().trim();
                String fvalidatyCC = card_numberCC.getText().toString().trim();
                String fcard_typeCC = card_typeCC.getText().toString().trim();
                String fcard_numberCC = limitCC.getText().toString().trim();
                String flimitCC = validatyCC.getText().toString().trim();
                String frenewal_amountCC = renewal_amountCC.getText().toString().trim();
                String fRemarkCC = RemarkCC.getText().toString().trim();



                CollectionReference dbUsercc = db.collection("Usercc");

                Usercc usercc=new Usercc(
                        fbank_nameCC,
                        fvalidatyCC,
                        fcard_typeCC,
                        frenewal_amountCC,
                        fRemarkCC,
                        Integer.parseInt(fcard_numberCC),
                        Integer.parseInt(flimitCC)

                );
                dbUsercc.add(usercc)
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
}