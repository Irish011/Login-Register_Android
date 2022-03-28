package com.example.register_login_firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonalRecyclerAdapter extends RecyclerView.Adapter<PersonalRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<PersonalModel> arraypersonal;
    PersonalRecyclerAdapter(Context context, ArrayList<PersonalModel> personalModel){
        this.context = context;
        this.arraypersonal = personalModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View v = LayoutInflater.from(context).inflate(R.layout.documentadd, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalRecyclerAdapter.ViewHolder holder, int position) {

        PersonalModel personalModel = arraypersonal.get(position);
        holder.doc_name.setText(personalModel.doc_name);
    }

    @Override
    public int getItemCount() {
        return arraypersonal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView doc_name;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            doc_name = itemView.findViewById(R.id.document_name);
        }
    }
}
