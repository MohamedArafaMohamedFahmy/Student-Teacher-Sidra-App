package com.arafa.mohamed.studentteachersidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.adapter.TabsSalaryRatingAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class RatingSalaryActivity extends AppCompatActivity {

    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow;
    TabLayout tabsItemsSalaryRating;
    ViewPager2 viewPager;
    TabsSalaryRatingAdapter tabsSalaryRatingAdapter;
    ArrayList<String> itemsSalaryRating;
    Bundle extra ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_salary);
        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);

        tvToolbar.setText(R.string.rating_salary_appbar);
        btBackArrow.setOnClickListener(v -> finish());
        extra = getIntent().getExtras();

        tabsItemsSalaryRating = findViewById(R.id.tabs_item);
        viewPager = findViewById(R.id.view_pager);
        itemsSalaryRating = new ArrayList<>();

        itemsSalaryRating.add("تفاصيل الراتب");
        itemsSalaryRating.add("تفاصيل التقييم");

        tabsSalaryRatingAdapter = new TabsSalaryRatingAdapter(this,extra.getString("codeTeacher"),extra.getString("nameTeacher"));
        viewPager.setAdapter(tabsSalaryRatingAdapter);
        new TabLayoutMediator(tabsItemsSalaryRating, viewPager, (tab, position) -> tab.setText(itemsSalaryRating.get(position))).attach();
    }
}