package com.example.register_login_firebase;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link categoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class categoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public categoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment categoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static categoryFragment newInstance(String param1, String param2) {
        categoryFragment fragment = new categoryFragment();
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
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectCategory(view, R.id.card1category, R.id.toBankAccountForm, "Bank Account");
        selectCategory(view, R.id.card2category, R.id.toFDForm, "Fixed Deposit");
        selectCategory(view, R.id.card3category, R.id.toInsuranceForm, "Insurance");
        selectCategory(view, R.id.card4category, R.id.toMutualFundsForm, "Mutual Fund");
        selectCategory(view, R.id.card5category, R.id.toLockerForm, "Locker");
        selectCategory(view, R.id.card6category, R.id.toLoansForm, "Loan");
        selectCategory(view, R.id.card7category, R.id.toCreditcardForm, "Credit Card");
        selectCategory(view, R.id.card8category, R.id.toOtherInvestmentsForm, "Other Investment");
    }

    private void selectCategory(View view, int cardId, int toId, String cardName){
        View Scrollview = view.findViewById(cardId);
        Scrollview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = ProgressDialog.show(getActivity(), cardName, "Loading", true);
                progressDialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Navigation.findNavController(view).navigate(toId);
                    }
                },1000);
            }
        });
    }
}