package com.arafa.mohamed.studentteachersidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.arafa.mohamed.studentteachersidraapp.R;

public class AboutActivity extends AppCompatActivity {
    AppCompatImageButton btBackArrow;
    AppCompatButton btFacebook;
    AppCompatTextView tvToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btBackArrow = findViewById(R.id.button_back_arrow);
        btFacebook = findViewById(R.id.button_facebook);
        tvToolbar = findViewById(R.id.text_toolbar);

        tvToolbar.setText(R.string.about_appbar);
        btBackArrow.setVisibility(View.VISIBLE);
        btBackArrow.setOnClickListener(v -> finish());

        btFacebook.setOnClickListener(v -> {

            try {
                getPackageManager().getPackageInfo("com.facebook.katana", 0);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1793493414235883")));
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/dar.sedrat.almontaha")));
            }
        });
    }

}