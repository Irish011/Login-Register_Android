package com.example.register_login_firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerManageAdapter extends RecyclerView.Adapter<RecyclerManageAdapter.ViewHolder> {

    Context context;
    ArrayList<ManageModel> arrayManage;
    RecyclerManageAdapter(Context context, ArrayList<ManageModel> arrayManage){
        this.context = context;
        this.arrayManage = arrayManage;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.formviewadd, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ManageModel manageModel = arrayManage.get(position);

        holder.b_name.setText(manageModel.b_name);
        holder.amount.setText(manageModel.amount);
    }

    @Override
    public int getItemCount() {
        return arrayManage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView b_name, amount;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            b_name = itemView.findViewById(R.id.accountformnameview);
            amount = itemView.findViewById(R.id.accountformamtview);
        }
    }
}
