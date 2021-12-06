package com.arafa.mohamed.studentteachersidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.models.ClassModel;
import com.arafa.mohamed.studentteachersidraapp.models.ReservationModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class SeatReservationActivity extends AppCompatActivity {
    AppCompatTextView tvDays,tvTiming,tvNameClass;
    DatabaseReference databaseReference;
    ClassModel retrieveDataClass;
    ReservationModel reservationModel;
    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow;
    TextInputEditText etNamePerson, etMobileNumber;
    AppCompatButton btReservation, btYes;
    String namePerson, mobileNumber, idReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_reservation);

        tvDays = findViewById(R.id.view_days);
        tvTiming = findViewById(R.id.view_timing);
        tvNameClass = findViewById(R.id.view_name_class);
        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);
        etNamePerson = findViewById(R.id.editText_name);
        etMobileNumber = findViewById(R.id.editText_mobile);
        btReservation = findViewById(R.id.button_reservation);

        tvToolbar.setText(R.string.reservation_page_appbar);
        btBackArrow.setVisibility(View.VISIBLE);
        btBackArrow.setOnClickListener(v -> finish());

        databaseReference = FirebaseDatabase.getInstance().getReference();

        btReservation.setOnClickListener(v -> {
            namePerson = Objects.requireNonNull(etNamePerson.getText()).toString();
            mobileNumber = Objects.requireNonNull(etMobileNumber.getText()).toString();

            if (!namePerson.isEmpty() && mobileNumber.length() == 11 &&
                    !retrieveDataClass.getDays().isEmpty() && !retrieveDataClass.getTiming().isEmpty() &&
                    !retrieveDataClass.getNameClass().isEmpty()){

                idReservation = databaseReference.push().getKey();
                reservationModel = new ReservationModel(namePerson, mobileNumber,
                        retrieveDataClass.getDays(), retrieveDataClass.getTiming(),retrieveDataClass.getNameClass(),idReservation);

                databaseReference.child("Reservations").child(idReservation).setValue(reservationModel).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                         showCustomDialog();
                    }
                });
            }

            if (namePerson.isEmpty()){
                etNamePerson.setError("من فضلك ادخل الاسم للتواصل معك");
            }

            if (mobileNumber.length() <11){
                etMobileNumber.setError("من فضلك ادخل 11 رقم");
            }
        });

        final Object objDetailed = getIntent().getSerializableExtra("dataClass");
        retrieveDataClass = (ClassModel) objDetailed;

        if (retrieveDataClass != null){
            tvDays.setText(retrieveDataClass.getDays());
            tvNameClass.setText(retrieveDataClass.getNameClass());
            tvTiming.setText(retrieveDataClass.getTiming());
        }
    }

    public  void showCustomDialog(){

        Dialog dialog = new Dialog(SeatReservationActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_normal);

        btYes = dialog.findViewById(R.id.button_yes);

        btYes.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}