package com.arafa.mohamed.studentteachersidraapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.models.AdminModel;
import com.arafa.mohamed.studentteachersidraapp.models.TeacherModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class TeacherDetailsActivity extends AppCompatActivity {

    AppCompatTextView tvToolbar,tvMessage;
    AppCompatImageButton btBackArrow,btSalaryRating;
    TextInputEditText etNameTeacher,etEnrollmentTeacher,etCodeTeacher,etMobileTeacher;
    LinearLayout linearProgressBar;
    RelativeLayout relativeContent;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    TeacherModel teacherModel, retrieveCodeTeacher;
    AdminModel adminModel;
    ArrayList<String> listCodeTeacher;
    Bundle extra ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);

        btBackArrow = findViewById(R.id.button_back_arrow);
        btSalaryRating = findViewById(R.id.button_rating);
        tvToolbar = findViewById(R.id.text_toolbar);
        tvMessage = findViewById(R.id.text_code_not_register);
        etNameTeacher = findViewById(R.id.editText_name_teacher);
        etEnrollmentTeacher = findViewById(R.id.editText_enrollment_teacher);
        etCodeTeacher = findViewById(R.id.editText_code_teacher);
        etMobileTeacher = findViewById(R.id.editText_mobile);
        linearProgressBar = findViewById(R.id.linear_progress_bar);
        relativeContent = findViewById(R.id.relative_content);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        listCodeTeacher = new ArrayList<>();

        extra = getIntent().getExtras();

        tvToolbar.setText(R.string.teacher_details_appbar);
        btBackArrow.setVisibility(View.VISIBLE);
        btBackArrow.setOnClickListener(v -> finish());
        btSalaryRating.setVisibility(View.VISIBLE);

        linearProgressBar.setVisibility(View.VISIBLE);

        databaseReference.child("TeachersData").child(extra.getString("codeTeacher")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                teacherModel = snapshot.getValue(TeacherModel.class);
                if(teacherModel != null){

                    linearProgressBar.setVisibility(View.GONE);
                    etNameTeacher.setText(teacherModel.getNameTeacher());
                    etCodeTeacher.setText(teacherModel.getCodeTeacher());
                    etEnrollmentTeacher.setText(teacherModel.getDateEnrollment());
                    etMobileTeacher.setText(teacherModel.getPhoneNumber());

                }else {
                    linearProgressBar.setVisibility(View.GONE);
                    btSalaryRating.setVisibility(View.GONE);
                    relativeContent.setVisibility(View.GONE);
                    tvMessage.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TeacherDetailsActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child("AdminsData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    adminModel = postSnapshot.getValue(AdminModel.class);
                    if (adminModel != null && adminModel.getEmailAdmin().equals(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail())) {
                        databaseReference.child("SupervisorTeacher").child(adminModel.getIdAdmin()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                    retrieveCodeTeacher = postSnapshot.getValue(TeacherModel.class);
                                    if (retrieveCodeTeacher !=null) {
                                        listCodeTeacher.add(retrieveCodeTeacher.getCodeTeacher());
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(TeacherDetailsActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TeacherDetailsActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btSalaryRating.setOnClickListener(v -> {
            if (listCodeTeacher.contains(extra.getString("codeTeacher")) || Objects.equals(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail(), "sedra4quran@gmail.com")) {
                Intent intentRatingSalary = new Intent(TeacherDetailsActivity.this, RatingSalaryActivity.class);
                intentRatingSalary.putExtra("codeTeacher", extra.getString("codeTeacher"));
                intentRatingSalary.putExtra("nameTeacher", teacherModel.getNameTeacher());
                startActivity(intentRatingSalary);
            }else {
                Toast.makeText(this, "غير مسموح لك بالدخول", Toast.LENGTH_LONG).show();
            }
        });

    }
}