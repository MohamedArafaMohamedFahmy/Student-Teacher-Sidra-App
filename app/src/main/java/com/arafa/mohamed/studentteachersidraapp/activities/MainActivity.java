package com.arafa.mohamed.studentteachersidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;
import com.arafa.mohamed.studentteachersidraapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgSelected;
    AppCompatRadioButton rbEnter;
    AppCompatButton btEnter, btYes, btNo;;
    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow, btPopUpMenu;
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
        btBackArrow = findViewById(R.id.button_back_arrow);
        btPopUpMenu = findViewById(R.id.button_pop_menu);
        tvToolbar = findViewById(R.id.text_toolbar);

        tvToolbar.setText(R.string.main_home_appbar);
        btBackArrow.setVisibility(View.GONE);

        btPopUpMenu.setVisibility(View.VISIBLE);
        btPopUpMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {

                if (item.getItemId() == R.id.action_about){
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                }
                else if(item.getItemId() == R.id.action_privacy_policy){
                    startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                }
                else if(item.getItemId() == R.id.action_log_out){
                    showCustomDialog();
                }

                return true;
            });
            popup.show();//showing popup menu

        });



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

    public  void showCustomDialog(){

        Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_log_out);

        btYes = dialog.findViewById(R.id.button_yes);
        btNo = dialog.findViewById(R.id.button_no);

        btYes.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();
            dialog.dismiss();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        btNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }


}