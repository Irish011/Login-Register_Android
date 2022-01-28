package com.example.register_login_firebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link portfolioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class portfolioFragment extends Fragment {

    private Button logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String uID;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public portfolioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment portfolioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static portfolioFragment newInstance(String param1, String param2) {
        portfolioFragment fragment = new portfolioFragment();
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
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }

    PieChart piechart;
    PieData piedata;
    List<PieEntry> pieEntryList = new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //PieChart1
        List<DataEntry> data1 = new ArrayList<>();
        data1.add(new ValueDataEntry("Apr", 300));
        data1.add(new ValueDataEntry("May", 600));
        data1.add(new ValueDataEntry("Jun", 2000));

        AnyChartView anyChartView = view.findViewById(R.id.chart);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        Pie pie1 = AnyChart.pie();
        pie1.data(data1);

        anyChartView.setChart(pie1);

        //PieChart2
        List<DataEntry> data2 = new ArrayList<>();
        data2.add(new ValueDataEntry("Apr", 500));
        data2.add(new ValueDataEntry("May", 900));
        data2.add(new ValueDataEntry("Jun", 1000));

        AnyChartView anyChartView1 = view.findViewById(R.id.chart2);
        APIlib.getInstance().setActiveAnyChartView(anyChartView1);
        Pie pie2 = AnyChart.pie();
        pie2.data(data2);
        anyChartView1.setChart(pie2);




            //Firebase
        /*user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        uID = user.getUid();

    //DataDisplay
         final TextView greetingTextView = (TextView) view.findViewById(R.id.greeting);
         final TextView emailTextView = (TextView) view.findViewById(R.id.emailid);
         final TextView passwordTextView = (TextView) view.findViewById(R.id.password);*/

         /*reference.child(uID).addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 User userProfile = snapshot.getValue(User.class);

                 if(userProfile != null){
                     String userEmail = userProfile.email;
                     String userPassword = userProfile.password;

                     greetingTextView.setText("Welcome, " + userEmail);
                     emailTextView.setText(userEmail);
                     passwordTextView.setText(userPassword);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Toast.makeText(getActivity(), "Something went wrong, Try again!", Toast.LENGTH_LONG).show();
             }
         });*/

            //Logout
           /* logout = (Button) view.findViewById(R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getActivity(), "Successfully Logout", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            });*/
        }




}