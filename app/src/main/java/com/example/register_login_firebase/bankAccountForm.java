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
 * Use the {@link bankAccountForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bankAccountForm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText bank_name, Branch_name, account_number, amount, holder_name,
            maturity_date, mode_of_operation, Remark;
    Button Reset, Submit;

    FirebaseFirestore db;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db=FirebaseFirestore.getInstance();

        bank_name = view.findViewById(R.id.Bank_name_account);
        Branch_name= view.findViewById(R.id.branch_name_account);
        account_number = view.findViewById(R.id.Account_Number_account);
        maturity_date = view.findViewById(R.id.Maturity_date_account);
        amount = view.findViewById(R.id.Amount_account);
        holder_name = view.findViewById(R.id.Holder_Name_account);
        mode_of_operation = view.findViewById(R.id.Mode_of_Operation_account);
        Remark= view.findViewById(R.id.remark_account);
        Reset= view.findViewById(R.id.reset_account);
        Submit= view.findViewById(R.id.submit_account);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fBank_name = bank_name.getText().toString().trim();
                String fBranch_name = Branch_name.getText().toString().trim();
                String fHolder_name = holder_name.getText().toString().trim();
                String fMaturity_date = maturity_date.getText().toString().trim();
                String fAccount_number = account_number.getText().toString().trim();
                String fAmount = amount.getText().toString().trim();
                String fMode_of_operation = mode_of_operation.getText().toString().trim();
                String fRemark = Remark.getText().toString().trim();



                CollectionReference dbUserAcc = db.collection("User");

                UserAcc useracc=new UserAcc(
                        fBank_name,
                        fBranch_name,
                        fHolder_name,
                        fMaturity_date,
                        fMode_of_operation,
                        fRemark,
                        Integer.parseInt(fAccount_number),
                        Integer.parseInt(fAmount)
                );

                dbUserAcc.add(useracc)
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

    public bankAccountForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bankAccountForm.
     */
    // TODO: Rename and change types and number of parameters
    public static bankAccountForm newInstance(String param1, String param2) {
        bankAccountForm fragment = new bankAccountForm();
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
        return inflater.inflate(R.layout.fragment_bank_account_form, container, false);
    }
}