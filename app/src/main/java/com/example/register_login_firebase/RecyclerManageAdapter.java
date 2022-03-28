package com.example.register_login_firebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class RecyclerManageAdapter extends RecyclerView.Adapter<RecyclerManageAdapter.ViewHolder> {

    Context context;
    ArrayList<ManageModel> arrayManage;
    private onManageListener manageListener;
    RecyclerManageAdapter(Context context, ArrayList<ManageModel> arrayManage, onManageListener manageListener){
        this.context = context;
        this.arrayManage = arrayManage;
        this.manageListener = manageListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.formviewadd, parent, false);

        ViewHolder viewHolder = new ViewHolder(v, manageListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ManageModel manageModel = arrayManage.get(position);

        holder.b_name.setText(manageModel.b_name);
        holder.amount.setText(manageModel.amount);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
//                bankAccountForm bankAccountform = new bankAccountForm();
//                NavigationView navigationView = (NavigationView) v.findViewById(R.id.navigation_view);
//                NavHostFragment navHostFragment = (NavHostFragment) appCompatActivity.getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
//                NavigationUI.setupWithNavController(navigationView, navHostFragment.getNavController());
//                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.accountrecycleview, bankAccountform).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayManage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView b_name, amount;
        onManageListener onManageListener;
        public ViewHolder(@NonNull View itemView, onManageListener onManageListener){
            super(itemView);

            b_name = itemView.findViewById(R.id.accountformnameview);
            amount = itemView.findViewById(R.id.accountformamtview);
            this.onManageListener = onManageListener ;

            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            onManageListener.onManageClick(getAdapterPosition());
        }
    }

    public interface onManageListener{
        void onManageClick(int position);
    }
}
