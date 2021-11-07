package com.arafa.mohamed.studentteachersidraapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import com.arafa.mohamed.studentteachersidraapp.R;
import com.arafa.mohamed.studentteachersidraapp.adapter.TabsSubscriptionRatingAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class RatingSubscriptionDetailsActivity extends AppCompatActivity {
    AppCompatTextView tvToolbar;
    AppCompatImageButton btBackArrow;
    TabLayout tabsItems;
    ViewPager2 viewPager;
    TabsSubscriptionRatingAdapter tabsSubscriptionRatingAdapter;
    ArrayList<String> items;
    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_subscription_details);

        btBackArrow = findViewById(R.id.button_back_arrow);
        tvToolbar = findViewById(R.id.text_toolbar);

        tvToolbar.setText(R.string.rating_subscription_appbar);
        btBackArrow.setOnClickListener(v -> finish());

        tabsItems = findViewById(R.id.tabs_item);
        viewPager = findViewById(R.id.view_pager);
        items = new ArrayList<>();
        extra = getIntent().getExtras();

        items.add("تفاصيل التقييم");
        items.add("تفاصيل الاشتراك");


        tabsSubscriptionRatingAdapter = new TabsSubscriptionRatingAdapter(this,extra.getString("codeStudent"), extra.getString("nameStudent"), extra.getString("classStudent"));
        viewPager.setAdapter(tabsSubscriptionRatingAdapter);
        new TabLayoutMediator(tabsItems, viewPager, (tab, position) -> tab.setText(items.get(position))).attach();
    }
}