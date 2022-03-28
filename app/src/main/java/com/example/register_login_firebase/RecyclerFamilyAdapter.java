package com.example.register_login_firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerFamilyAdapter extends RecyclerView.Adapter<RecyclerFamilyAdapter.ViewHolder> {

    Context context;
    ArrayList<FamilyModel> arrayFamily;
    RecyclerFamilyAdapter(Context context, ArrayList<FamilyModel> arrayFamily){
        this.context = context;
        this.arrayFamily = arrayFamily;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.family_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FamilyModel familyModel = arrayFamily.get(position);

        holder.txtname.setText(familyModel.h_name);
        holder.txtnum.setText(familyModel.u_email);
        holder.txtrelation.setText(familyModel.relation);
    }

    @Override
    public int getItemCount() {
        return arrayFamily.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtname, txtnum, txtrelation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.name);
            txtnum = itemView.findViewById(R.id.number);
            txtrelation = itemView.findViewById(R.id.relation);
        }
    }
}
