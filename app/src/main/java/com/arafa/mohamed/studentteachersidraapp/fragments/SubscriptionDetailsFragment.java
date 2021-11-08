package com.arafa.mohamed.studentteachersidraapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.models.SubscriptionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class SubscriptionDetailsFragment extends Fragment {

    TextInputEditText etJan, etFeb, etMar, etApr, etMay, etJun, etJul, etAug, etSep, etOct, etNov, etDec;
    SubscriptionModel subscriptionModel;
    DatabaseReference databaseReference;
    String codeStudent, nameStudent, classStudent;

    public SubscriptionDetailsFragment(String codeStudent, String nameStudent, String classStudent) {
        this.codeStudent = codeStudent;
        this.nameStudent = nameStudent;
        this.classStudent = classStudent;
    }


    public SubscriptionDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        View viewSubscription = inflater.inflate(R.layout.fragment_subscription_details, container, false);
        etJan = viewSubscription.findViewById(R.id.editText_jan);
        etFeb = viewSubscription.findViewById(R.id.editText_feb);
        etMar = viewSubscription.findViewById(R.id.editText_mar);
        etApr = viewSubscription.findViewById(R.id.editText_apr);
        etMay = viewSubscription.findViewById(R.id.editText_may);
        etJun = viewSubscription.findViewById(R.id.editText_jun);
        etJul = viewSubscription.findViewById(R.id.editText_jul);
        etAug = viewSubscription.findViewById(R.id.editText_aug);
        etSep = viewSubscription.findViewById(R.id.editText_sep);
        etOct = viewSubscription.findViewById(R.id.editText_oct);
        etNov = viewSubscription.findViewById(R.id.editText_nov);
        etDec = viewSubscription.findViewById(R.id.editText_dec);

        databaseReference.child("Subscription").child(codeStudent).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                subscriptionModel = snapshot.getValue(SubscriptionModel.class);
                if(subscriptionModel != null){

                        etJan.setText(subscriptionModel.getJan());
                        etFeb.setText(subscriptionModel.getFeb());
                        etMar.setText(subscriptionModel.getMar());
                        etApr.setText(subscriptionModel.getApr());
                        etMay.setText(subscriptionModel.getMay());
                        etJun.setText(subscriptionModel.getJun());
                        etJul.setText(subscriptionModel.getJul());
                        etAug.setText(subscriptionModel.getAug());
                        etSep.setText(subscriptionModel.getSep());
                        etOct.setText(subscriptionModel.getOct());
                        etNov.setText(subscriptionModel.getNov());
                        etDec.setText(subscriptionModel.getDec());

                }else{
                    Toast.makeText(getActivity(), "لا يوجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return viewSubscription;
    }
}