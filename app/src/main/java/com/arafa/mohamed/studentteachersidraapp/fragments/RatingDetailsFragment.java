package com.arafa.mohamed.studentteachersidraapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.models.RatingModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class RatingDetailsFragment extends Fragment {
    TextInputEditText etReview, etPreservation, etAudience, etAbsence, etTotal;
    FloatingActionButton btAddRating;
    String codeStudent;
    DatabaseReference databaseReference;
    RatingModel ratingModel;

    public RatingDetailsFragment() {
    }

    public RatingDetailsFragment(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        View  viewRating = inflater.inflate(R.layout.fragment_rating_details, container, false);
        etReview = viewRating.findViewById(R.id.editText_review);
        etPreservation = viewRating.findViewById(R.id.editText_preservation);
        etAudience = viewRating.findViewById(R.id.editText_audience);
        etAbsence = viewRating.findViewById(R.id.editText_absence);
        etTotal = viewRating.findViewById(R.id.editText_total);
        btAddRating = viewRating.findViewById(R.id.button_add_rating);

        databaseReference.child("Rating").child(codeStudent).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ratingModel = snapshot.getValue(RatingModel.class);
                if(ratingModel != null){
                    etReview.setText(ratingModel.getReview());
                    etPreservation.setText(ratingModel.getPreservation());
                    etAudience.setText(ratingModel.getAudience());
                    etAbsence.setText(ratingModel.getAbsence());
                    etTotal.setText(ratingModel.getTotal());

                }else{
                    Toast.makeText(getActivity(), "لا يوجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return viewRating;
    }
}