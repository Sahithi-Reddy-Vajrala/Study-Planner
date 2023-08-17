package com.example.studyplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
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
        setContentView(R.layout.nav_activity_main);
        getSupportActionBar().hide();
        viewPager2=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tab_layout);
        viewMajorFragmentAdapter=new ViewMajorFragmentAdapter(this);

        viewPager2.setAdapter(viewMajorFragmentAdapter);
        new TabLayoutMediator(tabLayout, viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home: break; case R.id.nav_calendar:
                        Intent intent = new Intent(MainActivity.this, CalenderActivity.class);
                        startActivity(intent);
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START); return true;
            }
        });





    }
}