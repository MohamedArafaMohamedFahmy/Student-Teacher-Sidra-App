package com.arafa.mohamed.studentteachersidraapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.activities.ViewClassesActivity;
import com.arafa.mohamed.studentteachersidraapp.models.BranchModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class BranchesAdapter extends RecyclerView.Adapter<BranchesAdapter.MyViewHolder> {
    Context context;
    ArrayList<BranchModel> downloadData;
    DatabaseReference databaseReference;
    AppCompatButton btYes,btNo;
    AppCompatTextView tvMessage;


    public BranchesAdapter(Context context, ArrayList<BranchModel> downloadData ){
        this.context=context;
        this.downloadData=downloadData;
    }

    @NonNull
    @Override
    public BranchesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_branch, parent, false);
        return new BranchesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchesAdapter.MyViewHolder holder, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        holder.tvNameBranch.setText(downloadData.get(position).getNameBranch());
        holder.itemView.setOnClickListener(v -> {
            Intent intentClasses = new Intent(context, ViewClassesActivity.class);
            intentClasses.putExtra("dataBranch",downloadData.get(position));
            context.startActivity(intentClasses);
        });

    }

    @Override
    public int getItemCount () {

        return downloadData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvNameBranch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameBranch = itemView.findViewById(R.id.text_name_branch);
        }
    }


}
