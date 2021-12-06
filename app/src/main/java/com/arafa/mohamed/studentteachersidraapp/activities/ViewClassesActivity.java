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
import com.arafa.mohamed.studentteachersidraapp.adapter.ClassesAdapter;
import com.arafa.mohamed.studentteachersidraapp.models.BranchModel;
import com.arafa.mohamed.studentteachersidraapp.models.ClassModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ViewClassesActivity extends AppCompatActivity {

    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow, btViewClasses;
    ClassModel retrieveDataClass;
    BranchModel retrieveDataBranch;
    RecyclerView recClasses;
    DatabaseReference databaseReference;
    ArrayList<ClassModel> listClasses;
    ClassesAdapter classesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classes);

        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);
        recClasses = findViewById(R.id.rec_classes);
        btViewClasses = findViewById(R.id.button_rating);
        listClasses = new ArrayList<>();


        final Object objDetailed = getIntent().getSerializableExtra("dataBranch");
        retrieveDataBranch = (BranchModel) objDetailed;


        tvToolbar.setText(R.string.view_classes_appbar);
        btBackArrow.setVisibility(View.VISIBLE);
        btBackArrow.setOnClickListener(v -> finish());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Classes").child(retrieveDataBranch.getIdBranch()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listClasses.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    retrieveDataClass = postSnapshot.getValue(ClassModel.class);
                    listClasses.add(retrieveDataClass);
                }
                if(!listClasses.isEmpty()){
                    classesAdapter = new ClassesAdapter(ViewClassesActivity.this,listClasses, retrieveDataBranch.getIdBranch());
                    classesAdapter.notifyDataSetChanged();
                    recClasses.setAdapter(classesAdapter);
                    recClasses.setLayoutManager(new LinearLayoutManager(ViewClassesActivity.this));

                }else {
                    Toast.makeText(ViewClassesActivity.this, "لا يوجد فصول حاليا", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewClassesActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}