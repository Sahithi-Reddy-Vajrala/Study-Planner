package com.example.studyplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    ViewMajorFragmentAdapter viewMajorFragmentAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    private String[] titles=new String[]{"StudyPlans","Assignments","Exams","Lectures"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        viewPager2=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tab_layout);
        viewMajorFragmentAdapter=new ViewMajorFragmentAdapter(this);

        viewPager2.setAdapter(viewMajorFragmentAdapter);
        new TabLayoutMediator(tabLayout, viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();
    }
}