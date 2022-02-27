package com.arafa.mohamed.studentteachersidraapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.adapter.MonthAdapter;
import com.arafa.mohamed.studentteachersidraapp.models.CustomSpinner;
import com.arafa.mohamed.studentteachersidraapp.models.RatingTeacherModel;
import com.arafa.mohamed.studentteachersidraapp.models.SubscriptionModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class RatingTeacherFragment extends Fragment implements CustomSpinner.OnSpinnerEventsListener {


    TextInputEditText etNotesAttendanceDeparture, etScoreAttendanceDeparture, etNotesClassroomCleanTidy,
            etScoreClassroomCleanTidy, etNotesValueGame, etScoreValueGame, etNotesDealingAtmosphere,
            etScoreDealingAtmosphere, etNotesTimeManagement, etScoreTimeManagement, etTotal, etAchievements;

    private String   codeTeacher;

    DatabaseReference databaseReference;
    RatingTeacherModel ratingTeacherModel;
    CustomSpinner spinnerMonth;
    int indexMonth;
    MonthAdapter monthAdapter;
    Context context;


    public RatingTeacherFragment() {
        // Required empty public constructor
    }

    public RatingTeacherFragment(Context context,String codeTeacher) {
        this.codeTeacher = codeTeacher;
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        View viewRating =  inflater.inflate(R.layout.fragment_rating_teacher, container, false);
        etNotesAttendanceDeparture = viewRating.findViewById(R.id.editText_notes_attendance_and_departure);
        etScoreAttendanceDeparture = viewRating.findViewById(R.id.editText_score_attendance_and_departure);
        etNotesClassroomCleanTidy = viewRating.findViewById(R.id.editText_notes_cleanliness_order_classroom);
        etScoreClassroomCleanTidy = viewRating.findViewById(R.id.editText_score_cleanliness_order_classroom);
        etNotesValueGame = viewRating.findViewById(R.id.editText_notes_value_game_material);
        etScoreValueGame = viewRating.findViewById(R.id.editText_score_value_game_material);
        etNotesDealingAtmosphere = viewRating.findViewById(R.id.editText_notes_dealing_with_students);
        etScoreDealingAtmosphere = viewRating.findViewById(R.id.editText_score_dealing_with_students);
        etNotesTimeManagement = viewRating.findViewById(R.id.editText_notes_time_management);
        etScoreTimeManagement = viewRating.findViewById(R.id.editText_score_time_management);
        etTotal = viewRating.findViewById(R.id.editText_score_total);
        etAchievements = viewRating.findViewById(R.id.editText_achievements);

        spinnerMonth = viewRating.findViewById(R.id.spinner_month);
        spinnerMonth.setSpinnerEventsListener(this);


        monthAdapter = new MonthAdapter(context, SubscriptionModel.getMonth());
        spinnerMonth.setAdapter(monthAdapter);
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indexMonth = spinnerMonth.getSelectedItemPosition();
                if (indexMonth == 0) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("jan").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {

                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 1) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("feb").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (indexMonth == 2) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("mar").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 3) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("apr").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (indexMonth == 4) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("may").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (indexMonth == 5) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("jun").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {

                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 6) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("jul").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (indexMonth == 7) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("aug").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (indexMonth == 8) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("sep").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (indexMonth == 9) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("oct").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (indexMonth == 10) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("nov").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (indexMonth == 11) {
                    databaseReference.child("RatingTeachers").child(codeTeacher).child("dec").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ratingTeacherModel = snapshot.getValue(RatingTeacherModel.class);

                            if (ratingTeacherModel != null){
                                etNotesAttendanceDeparture.setText(ratingTeacherModel.getNotesAttendanceDeparture());
                                etScoreAttendanceDeparture.setText(ratingTeacherModel.getScoreAttendanceDeparture());
                                etNotesClassroomCleanTidy.setText(ratingTeacherModel.getNotesClassroomCleanTidy());
                                etScoreClassroomCleanTidy.setText(ratingTeacherModel.getScoreClassroomCleanTidy());
                                etNotesValueGame.setText(ratingTeacherModel.getNotesValueGame());
                                etScoreValueGame.setText(ratingTeacherModel.getScoreValueGame());
                                etNotesDealingAtmosphere.setText(ratingTeacherModel.getNotesDealingAtmosphere());
                                etScoreDealingAtmosphere.setText(ratingTeacherModel.getScoreDealingAtmosphere());
                                etNotesTimeManagement.setText(ratingTeacherModel.getNotesTimeManagement());
                                etScoreTimeManagement.setText(ratingTeacherModel.getScoreTimeManagement());
                                etTotal.setText(ratingTeacherModel.getTotal());
                                etAchievements.setText(ratingTeacherModel.getAchievements());

                            } else{
                                Objects.requireNonNull(etNotesAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etScoreAttendanceDeparture.getText()).clear();
                                Objects.requireNonNull(etNotesClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etScoreClassroomCleanTidy.getText()).clear();
                                Objects.requireNonNull(etNotesValueGame.getText()).clear();
                                Objects.requireNonNull(etScoreValueGame.getText()).clear();
                                Objects.requireNonNull(etNotesDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etScoreDealingAtmosphere.getText()).clear();
                                Objects.requireNonNull(etNotesTimeManagement.getText()).clear();
                                Objects.requireNonNull(etScoreTimeManagement.getText()).clear();
                                Objects.requireNonNull(etTotal.getText()).clear();
                                Objects.requireNonNull(etAchievements.getText()).clear();
                                Toast.makeText(context, "لا توجد بيانات حاليا", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(context, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return viewRating;
    }

    @Override
    public void onPopupWindowOpened(AppCompatSpinner spinner) {
        spinnerMonth.setBackground(AppCompatResources.getDrawable(context,R.drawable.bg_spinner_month_up));
    }

    @Override
    public void onPopupWindowClosed(AppCompatSpinner spinner) {
        spinnerMonth.setBackground(AppCompatResources.getDrawable(context,R.drawable.bg_spinner_month));
    }
}