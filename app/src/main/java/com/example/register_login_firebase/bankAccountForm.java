package com.example.register_login_firebase;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
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
import com.google.errorprone.annotations.Var;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.MessageDigest;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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
    FirebaseUser user;
    FirebaseFirestore db;
    String uid, bname, brname;
    String ED = "D";
    ProgressDialog pd;
    Integer number;

    String fBank_nameAcc;
    String fBranch_nameAcc;


    EditText bank_name, Branch_name, account_number, amount, holder_name,
            maturity_date, mode_of_operation, Remark, KeyAccount;

//    String KeyAcc;
    Button Reset, Submit;
    String AES = "AES";
    ArrayList<ManageModel> arrayManage = new ArrayList<>();
    RecyclerManageAdapter adapter;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db=FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        uid = user.getUid();
        number = 1;

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
        KeyAccount = view.findViewById(R.id.KeyAcc);
        KeyAccount.setText("hello");

        if(ED.equals("E")) {

            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    KeyAccount.setText("hello");
//                KeyAcc = "Hello";

                   fBank_nameAcc = null;
                   fBranch_nameAcc = null;
                    String fHolder_nameAcc = null;
                    String fMaturity_dateAcc = null;
                    String fAccount_numberAcc = null;
                    String fAmountAcc = null;
                    String fMode_of_operationAcc = null;
                    String fRemarkAcc = null;

                    try {
                        fBank_nameAcc = encrypt(bank_name.getText().toString().trim(), KeyAccount.getText().toString());
                        fBranch_nameAcc = encrypt(Branch_name.getText().toString().trim(), KeyAccount.getText().toString());
                        fHolder_nameAcc = encrypt(holder_name.getText().toString().trim(), KeyAccount.getText().toString());
                        fMaturity_dateAcc = encrypt(maturity_date.getText().toString().trim(), KeyAccount.getText().toString());
                        fAccount_numberAcc = encrypt(account_number.getText().toString().trim(), KeyAccount.getText().toString());
                        fAmountAcc = encrypt(amount.getText().toString().trim(), KeyAccount.getText().toString());
                        fMode_of_operationAcc = encrypt(mode_of_operation.getText().toString().trim(), KeyAccount.getText().toString());
                        fRemarkAcc = encrypt(Remark.getText().toString().trim(), KeyAccount.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    DocumentReference dbUserAcc = db.collection("Head").document(id).collection("Investments").document(String.valueOf(number));

                    UserAcc useracc = new UserAcc(
                            fBank_nameAcc,
                            fBranch_nameAcc,
                            fHolder_nameAcc,
                            fMaturity_dateAcc,
                            fMode_of_operationAcc,
                            fRemarkAcc,
                            fAccount_numberAcc,
                            fAmountAcc
                    );

                    dbUserAcc.set(useracc)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getActivity(), "User Added", Toast.LENGTH_LONG).show();
                                    number++;
                                }
                            })
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getActivity(),"User Added",Toast.LENGTH_LONG).show();
//                    }
//                })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });


                }

                private String encrypt(String n1, String n2) throws Exception {

                    SecretKeySpec key = generateKey(n2);
                    Cipher c = Cipher.getInstance(AES);
                    c.init(Cipher.ENCRYPT_MODE, key);
                    byte[] encVal = c.doFinal(n1.getBytes());
                    String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
                    return encryptedValue;
                }
            });
        }
        else {

            Submit.setVisibility(View.INVISIBLE);
            Reset.setVisibility(View.INVISIBLE);

            db.collection("Head").document(uid).collection("Investments").document("1")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            bname = value.getString("fbank_name");
                            brname = value.getString("fbranch_name");
                        }
                    });

            try {

                fBank_nameAcc  = decrypt(bname, KeyAccount.getText().toString());
                fBranch_nameAcc  = decrypt(brname, KeyAccount.getText().toString());
                bank_name.setText(fBank_nameAcc);
                Branch_name.setText(fBranch_nameAcc);
                Log.d("Bank Name", "fBank_nameAcc");
                Log.d("Branch Name", fBranch_nameAcc);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

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

    private SecretKeySpec generateKey(String n2) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = n2.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
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