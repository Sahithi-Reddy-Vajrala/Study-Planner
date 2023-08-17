package com.example.studyplanner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewMajorFragmentAdapter extends FragmentStateAdapter {
    private String[] titles=new String[]{"StudyPlans","Assignments","Exams","Lectures"};

    public ViewMajorFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new StudyPlans();
            case 1:
                return new Assignments();
            case 2:
                return new Exams();
            case 3:
                return new Lectures();
        }
        return new StudyPlans();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
