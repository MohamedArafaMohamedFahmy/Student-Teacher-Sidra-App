package com.arafa.mohamed.studentteachersidraapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.adapter.BranchesAdapter;
import com.arafa.mohamed.studentteachersidraapp.models.BranchModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewBranchesActivity extends AppCompatActivity {

    BranchModel  retrieveBranches;
    BranchesAdapter branchesAdapter;
    ArrayList<BranchModel> listBranches;
    DatabaseReference databaseReference;
    RecyclerView recBranches;
    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_branches);

        recBranches = findViewById(R.id.rec_branches);
        listBranches = new ArrayList<>();

        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);

        tvToolbar.setText(R.string.view_branches_appbar);
        btBackArrow.setVisibility(View.VISIBLE);
        btBackArrow.setOnClickListener(v -> finish());

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Branches").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listBranches.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    retrieveBranches = postSnapshot.getValue(BranchModel.class);
                    listBranches.add(retrieveBranches);
                }
                if(!listBranches.isEmpty()){
                    branchesAdapter = new BranchesAdapter(ViewBranchesActivity.this,listBranches);
                    branchesAdapter.notifyDataSetChanged();
                    recBranches.setAdapter(branchesAdapter);
                    recBranches.setLayoutManager(new LinearLayoutManager(ViewBranchesActivity.this));


                }else {
                    Toast.makeText(ViewBranchesActivity.this, "لا يوجد فروع حاليا", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewBranchesActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}