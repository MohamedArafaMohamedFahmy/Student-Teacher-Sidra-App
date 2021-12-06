package com.arafa.mohamed.studentteachersidraapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.activities.SeatReservationActivity;
import com.arafa.mohamed.studentteachersidraapp.activities.ViewClassesActivity;
import com.arafa.mohamed.studentteachersidraapp.models.ClassModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class ClassesAdapter  extends RecyclerView.Adapter<ClassesAdapter.MyViewHolder> {
    Context context;
    ArrayList<ClassModel> downloadData;
    DatabaseReference databaseReference;
    String idBranch;

    public ClassesAdapter(Context context, ArrayList<ClassModel> downloadData, String idBranch ){
        this.context=context;
        this.downloadData=downloadData;
        this.idBranch = idBranch;
    }

    @NonNull
    @Override
    public ClassesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_classes, parent, false);
        return new ClassesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesAdapter.MyViewHolder holder, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference();


        if (downloadData.get(position).getStatus().equals("مغلق")){
            holder.imgStatus.setImageResource(R.drawable.close);
            holder.tvNameClass.setText(downloadData.get(position).getNameClass());
            holder.tvDays.setText(downloadData.get(position).getDays());
            holder.tvTiming.setText(downloadData.get(position).getTiming());
            holder.itemView.setOnClickListener(v -> {
                Toast.makeText(context, "هذا الفصل غير متاح , من فضلك اختر الفصل المتاح", Toast.LENGTH_LONG).show();
            });
        }
        if (downloadData.get(position).getStatus().equals("مفتوح")){
            holder.imgStatus.setImageResource(R.drawable.open);
            holder.tvNameClass.setText(downloadData.get(position).getNameClass());
            holder.tvDays.setText(downloadData.get(position).getDays());
            holder.tvTiming.setText(downloadData.get(position).getTiming());
            holder.itemView.setOnClickListener(v -> {
                Intent intentReservation = new Intent(context, SeatReservationActivity.class);
                intentReservation.putExtra("dataClass",downloadData.get(position));
                context.startActivity(intentReservation);
            });
        }
    }

    @Override
    public int getItemCount () {

        return downloadData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvNameClass, tvDays, tvTiming;
        AppCompatImageView imgStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameClass = itemView.findViewById(R.id.text_name_class);
            tvDays = itemView.findViewById(R.id.text_days);
            tvTiming = itemView.findViewById(R.id.text_timing);
            imgStatus = itemView.findViewById(R.id.image_status);
        }
    }

}
