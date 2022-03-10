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
 * Use the {@link lockerForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lockerForm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText bank_nameLoker, Branch_nameLoker, loker_numberLoker, item_descriptionLoker, yearly_rentLoker,
            due_dateLoker, mode_of_operationLoker, RemarkLoker;
    Button Reset, Submit;

    FirebaseFirestore db;

    public lockerForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment lockerForm.
     */
    // TODO: Rename and change types and number of parameters
    public static lockerForm newInstance(String param1, String param2) {
        lockerForm fragment = new lockerForm();
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
        return inflater.inflate(R.layout.fragment_locker_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db=FirebaseFirestore.getInstance();

        bank_nameLoker = view.findViewById(R.id.Bank_nameLoker);
        Branch_nameLoker = view.findViewById(R.id.branch_nameLoker);
        loker_numberLoker = view.findViewById(R.id.Loker_numberLoker);
        due_dateLoker = view.findViewById(R.id.Due_dateLoker);
        item_descriptionLoker = view.findViewById(R.id.Item_DescriptionLoker);
        yearly_rentLoker = view.findViewById(R.id.Yearly_RentLoker);
        mode_of_operationLoker = view.findViewById(R.id.Mode_of_OperationLoker);
        RemarkLoker = view.findViewById(R.id.remark);
        Reset= view.findViewById(R.id.reset);
        Submit= view.findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fBank_nameLoker = bank_nameLoker.getText().toString().trim();
                String fBranch_nameLoker = Branch_nameLoker.getText().toString().trim();
                String fItem_descriptionLoker = item_descriptionLoker.getText().toString().trim();
                String fDue_dateLoker = due_dateLoker.getText().toString().trim();
                String floker_numberLoker = loker_numberLoker.getText().toString().trim();
                String fYearly_rentLoker = yearly_rentLoker.getText().toString().trim();
                String fMode_of_operationLoker = mode_of_operationLoker.getText().toString().trim();
                String fremarkLoker = RemarkLoker.getText().toString().trim();



                CollectionReference dbUserLoker = db.collection("UserLoker");

                UserLoker userloker=new UserLoker(
                        fBank_nameLoker,
                        fBranch_nameLoker,
                        fItem_descriptionLoker,
                        fDue_dateLoker,
                        fMode_of_operationLoker,
                        fremarkLoker,
                        Integer.parseInt(floker_numberLoker),
                        Integer.parseInt(fYearly_rentLoker)

                );
                dbUserLoker.add(userloker)
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