package com.example.register_login_firebase;

import static androidx.navigation.Navigation.findNavController;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link investmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class investmentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button submit;
//    private Button card1,card2;
    private Integer field2, field1;
    private List<DataEntry> data1;
    View view2;
int k=1;

    public investmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment investmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static investmentFragment newInstance(String param1, String param2) {
        investmentFragment fragment = new investmentFragment();
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

    FirebaseFirestore db;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view2 = inflater.inflate(R.layout.fragment_investment, container, false);

         return view2;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Add Investments
        submit = view.findViewById(R.id.submit3);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.toManageFragment);
            }
        });

        //Pie Chart using Firestore

        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Investments").document("Pie");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("exists", "DocumentSnapshot data: " + document.getData() + k);
                        field1 = Integer.parseInt(document.getString("data1"));
                        field2 = Integer.parseInt(document.getString("data2"));

                    } else {
                        Log.d("not exists", "No such document");
                    }
                } else {
                    Log.d("db error", "get failed with ", task.getException());
                }

                data1 = new ArrayList<DataEntry>();
//                List<DataEntry> data1 = new ArrayList<>();


                if(data1 != null) {
                    Log.d("k", "before clear" + data1);
                    data1.clear();
                    Log.d("k","before" + data1);

                    data1.add(new ValueDataEntry("Assests", field1));
                    data1.add(new ValueDataEntry("Liabilities", field2));
                    Log.d("k","after" + data1);
                }
                    Log.d("k2","k" + data1);

                    AnyChartView anyChartView1 = view2.findViewById(R.id.investmentchart);
                    APIlib.getInstance().setActiveAnyChartView(anyChartView1);
                    Pie pie = AnyChart.pie();
                    pie.data(data1);
                    anyChartView1.setChart(pie);

//                Log.d("k", "k"+ k++);


            }

        });


        //Pie Chart Values from another Fragment

        /*getParentFragmentManager().setFragmentResultListener("dataFrom1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("key");

        Pie pie1 = AnyChart.pie();
        int hi = Integer.parseInt(data);
        List<DataEntry> data1 = new ArrayList<>();
        data1.add(new ValueDataEntry("Assests", hi));
        data1.add(new ValueDataEntry("Liabilities", 600));

        pie1.data(data1);

        AnyChartView anyChartView1 = view.findViewById(R.id.investmentchart);
        anyChartView1.setChart(pie1);
            }
        });*///

        //Cards (View Investments)


        viewInvestment(view, R.id.card1, R.id.toBankAccount, "Bank Account");
        viewInvestment(view, R.id.card2, R.id.toFD, "Fixed Deposit");
        viewInvestment(view, R.id.card3, R.id.toInsurance, "Insurance");
        viewInvestment(view, R.id.card4, R.id.toMutualFunds, "Mutual Fund");
        viewInvestment(view, R.id.card5, R.id.toLocker, "Locker");
        viewInvestment(view, R.id.card6, R.id.toLoans, "Loan");
        viewInvestment(view, R.id.card7, R.id.toCreditcard, "Credit Card");
        viewInvestment(view, R.id.card8, R.id.toOtherInvestments, "Other Investment");



    }


    private void viewInvestment(View view, int cardId, int toId, String cardName){
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