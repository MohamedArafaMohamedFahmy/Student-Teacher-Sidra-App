package com.arafa.mohamed.studentteachersidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.arafa.mohamed.studentteachersidraapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgSelected;
    AppCompatRadioButton rbEnter;
    AppCompatButton btEnter;
    TextInputEditText etEnterCode;
    int radioButtonID, retrieveID;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgSelected = findViewById(R.id.group_selected);
        btEnter = findViewById(R.id.button_enter);
        etEnterCode = findViewById(R.id.editText_code);

        btEnter.setOnClickListener(v -> {
            code = Objects.requireNonNull(etEnterCode.getText()).toString();
            radioButtonID = rgSelected.getCheckedRadioButtonId();
            rbEnter = findViewById(radioButtonID);
            retrieveID = rbEnter.getId();
            if (!code.isEmpty()) {
                if (retrieveID == R.id.radio_button_student) {
                    Intent intentStudent = new Intent(MainActivity.this, StudentDetailsActivity.class);
                    intentStudent.putExtra("codeStudent",code);
                    startActivity(intentStudent);
                }else if(retrieveID == R.id.radio_button_teacher){
                    Intent intentTeacher = new Intent(MainActivity.this, TeacherDetailsActivity.class);
                    intentTeacher.putExtra("codeTeacher",code);
                    startActivity(intentTeacher);
                }
            }
            if (code.isEmpty()){
                etEnterCode.setText("من فضلك ادخل الكود");
            }
        });
    }
}