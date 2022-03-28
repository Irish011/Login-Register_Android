package com.example.register_login_firebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loanForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loanForm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText bank_nameLoan, Branch_nameLoan, loan_amountLoan, loan_EMILoan, RoiLoan,
            loan_account_numberLoan, disbursement_dateLoan, end_dateLoan, RemarkLoan, KeyLoan;
    Button Reset, Submit;
    Integer number;

    String AES="AES";
    String outputstring, output2;
    String ED = "E";

    FirebaseFirestore db;
    FirebaseUser user;

    public loanForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loanForm.
     */
    // TODO: Rename and change types and number of parameters
    public static loanForm newInstance(String param1, String param2) {
        loanForm fragment = new loanForm();
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

        bank_nameLoan = view.findViewById(R.id.Bank_nameLoan);
        Branch_nameLoan = view.findViewById(R.id.branch_nameLoan);
        loan_amountLoan = view.findViewById(R.id.Loan_AmountLoan);
        loan_account_numberLoan = view.findViewById(R.id.Loan_Account_NumberLoan);
        loan_EMILoan = view.findViewById(R.id.Loan_EMILoan);
        RoiLoan = view.findViewById(R.id.ROILoan);
        disbursement_dateLoan = view.findViewById(R.id.Disbursement_dateLoan);
        end_dateLoan = view.findViewById(R.id.End_dateLoan);
        RemarkLoan = view.findViewById(R.id.remark);
        Reset= view.findViewById(R.id.reset);
        Submit= view.findViewById(R.id.submit);
        KeyLoan = view.findViewById(R.id.KeyLoan);
        user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        number = 1;


        if(ED.equals("E")) {

            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    KeyLoan.setText("hello");

                    String fbank_nameLoan = null;
                    String fbranch_nameLoan = null;
                    String fdisbursement_dateLoan= null;
                    String fend_dateLoan= null;
                    String floan_amountLoan= null;
                    String floan_account_numberLoan= null;
                    String floan_EMILoan= null;
                    String fRoiLoan= null;
                    String fremarkLoan= null;

                    try {
                        fbank_nameLoan = encrypt(bank_nameLoan.getText().toString().trim(), KeyLoan.getText().toString());
                        fbranch_nameLoan = encrypt(Branch_nameLoan.getText().toString().trim(), KeyLoan.getText().toString());
                        fdisbursement_dateLoan = encrypt(disbursement_dateLoan.getText().toString().trim(), KeyLoan.getText().toString());
                        fend_dateLoan = encrypt(end_dateLoan.getText().toString().trim(), KeyLoan.getText().toString());
                        floan_amountLoan = encrypt(loan_amountLoan.getText().toString().trim(), KeyLoan.getText().toString());
                        floan_account_numberLoan = encrypt(loan_account_numberLoan.getText().toString().trim(), KeyLoan.getText().toString());
                        floan_EMILoan = encrypt(loan_EMILoan.getText().toString().trim(), KeyLoan.getText().toString());
                        fRoiLoan = encrypt(RoiLoan.getText().toString().trim(), KeyLoan.getText().toString());
                        fremarkLoan = encrypt(RemarkLoan.getText().toString().trim(), KeyLoan.getText().toString());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                    DocumentReference dbUserLoan = db.collection("user").document(id).collection("Investments_Loan").document(String.valueOf(number));

                    UserLoan userloan = new UserLoan(
                            fbank_nameLoan,
                            fbranch_nameLoan,
                            fdisbursement_dateLoan,
                            fend_dateLoan,
                            fremarkLoan,
                            floan_amountLoan,
                            floan_account_numberLoan,
                            floan_EMILoan,
                            fRoiLoan
                    );

                    dbUserLoan.set(userloan)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getActivity(), "Document Added!", Toast.LENGTH_SHORT).show();
                                    number++;
                                }
                            })
//                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                @Override
//                                public void onSuccess(DocumentReference documentReference) {
//                                    Toast.makeText(getActivity(), "User Added", Toast.LENGTH_LONG).show();
//                                }
//                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            });
        }

        else{
            Submit.setVisibility(View.INVISIBLE);
            Reset.setVisibility(View.INVISIBLE);

            try {
                outputstring = decrypt(outputstring, bank_nameLoan.getText().toString());
                output2 = decrypt(output2, Branch_nameLoan.getText().toString());
                bank_nameLoan.setText(outputstring);
                Branch_nameLoan.setText(output2);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loan_form, container, false);
    }

    private String decrypt(String dcpt, String n2) throws  Exception{
        SecretKeySpec key = generateKey(n2);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.decode(dcpt, Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private SecretKeySpec generateKey(String n2) throws Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = n2.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
    private String encrypt(String n1, String n2) throws Exception {

        SecretKeySpec key = generateKey(n2);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encVal = c.doFinal(n1.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;
    }

}