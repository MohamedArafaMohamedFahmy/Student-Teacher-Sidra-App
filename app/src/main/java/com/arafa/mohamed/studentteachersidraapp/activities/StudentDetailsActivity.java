package com.arafa.mohamed.studentteachersidraapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.models.StudentModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.squareup.picasso.Picasso;

public class StudentDetailsActivity extends AppCompatActivity {

    AppCompatTextView tvToolbar, tvMessage;
    AppCompatImageButton btBackArrow,btSubRating;
    TextInputEditText etNameStudent,etEnrollmentStudent,etCodeStudent,etMobileFather,etMobileMother,
            etClassStudent, etDateSession, etBornDate, etBranch, etStartSaving;
    AppCompatImageView imgStudent,imgQRCode;
    LinearLayout linearProgressBar;
    RelativeLayout relativeContent;
    DatabaseReference databaseReference;
    StudentModel studentModel;
    Bundle extra ;
    String  codeStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_details);
        btBackArrow = findViewById(R.id.button_back_arrow);
        btSubRating = findViewById(R.id.button_rating);
        tvToolbar = findViewById(R.id.text_toolbar);
        tvMessage = findViewById(R.id.text_code_not_register);
        etNameStudent = findViewById(R.id.editText_name_student);
        etEnrollmentStudent = findViewById(R.id.editText_date_student);
        etCodeStudent = findViewById(R.id.editText_code_student);
        etMobileFather = findViewById(R.id.editText_mobile_father);
        etMobileMother = findViewById(R.id.editText_mobile_mother);
        etClassStudent = findViewById(R.id.editText_class_student);
        etDateSession = findViewById(R.id.editText_date_session_student);
        etBornDate = findViewById(R.id.editText_born_date);
        etBranch = findViewById(R.id.editText_branch);
        etStartSaving = findViewById(R.id.editText_start_saving);
        linearProgressBar = findViewById(R.id.linear_progress_bar);
        imgStudent = findViewById(R.id.image_student_details);
        imgQRCode = findViewById(R.id.image_qr);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        relativeContent = findViewById(R.id.relative_content_student);
        extra = getIntent().getExtras();
        codeStudent = extra.getString("codeStudent");

        tvToolbar.setText(R.string.student_details_appbar);
        btSubRating.setVisibility(View.VISIBLE);
        btBackArrow.setVisibility(View.VISIBLE);
        btBackArrow.setOnClickListener(v -> finish());

        linearProgressBar.setVisibility(View.VISIBLE);

        databaseReference.child("StudentsData").child(codeStudent).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                studentModel = snapshot.getValue(StudentModel.class);
                if (studentModel != null){
                    linearProgressBar.setVisibility(View.GONE);
                    Picasso.get().load(Uri.parse(studentModel.getUrlStudent())).into(imgStudent);
                    etNameStudent.setText(studentModel.getNameStudent());
                    etEnrollmentStudent.setText(studentModel.getEnrollmentStudent());
                    etCodeStudent.setText(studentModel.getCodeStudent());
                    etMobileFather.setText(studentModel.getMobileFather());
                    etMobileMother.setText(studentModel.getMobileMother());
                    etClassStudent.setText(studentModel.getClassStudent());
                    etDateSession.setText(studentModel.getDateSession());
                    etBornDate.setText(studentModel.getBornDate());
                    etBranch.setText(studentModel.getBranch());
                    etStartSaving.setText(studentModel.getStartSaving());
                    generateQRCode(studentModel.getCodeStudent());

                }else{

                    linearProgressBar.setVisibility(View.GONE);
                    linearProgressBar.setVisibility(View.GONE);
                    btSubRating.setVisibility(View.GONE);
                    relativeContent.setVisibility(View.GONE);
                    tvMessage.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(StudentDetailsActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btSubRating.setOnClickListener(v -> {
            Intent intentRatingSubscription = new Intent(StudentDetailsActivity.this,RatingSubscriptionDetailsActivity.class);
            intentRatingSubscription.putExtra("codeStudent",studentModel.getCodeStudent());
            intentRatingSubscription.putExtra("nameStudent",studentModel.getNameStudent());
            intentRatingSubscription.putExtra("classStudent",studentModel.getClassStudent());
            startActivity(intentRatingSubscription);
        });


    }

    public void generateQRCode(String text){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {

            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 1000, 1000);
            Bitmap bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.RGB_565);
            for (int x = 0; x < 1000; x++) {
                for (int y = 0; y < 1000; y++) {
                    linearProgressBar.setVisibility(View.VISIBLE);
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.parseColor("#DBEFD6"));
                }
            }
            imgQRCode.setImageBitmap(bitmap);
            linearProgressBar.setVisibility(View.GONE);

        } catch (Exception e) {
            Toast.makeText(StudentDetailsActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}