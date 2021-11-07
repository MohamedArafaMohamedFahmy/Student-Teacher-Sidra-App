package com.arafa.mohamed.studentteachersidraapp.adapter;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.arafa.mohamed.studentteachersidraapp.fragments.RatingTeacherFragment;
import com.arafa.mohamed.studentteachersidraapp.fragments.SalaryFragment;

public class TabsSalaryRatingAdapter extends FragmentStateAdapter {
    FragmentActivity fragmentActivity;
    String codeTeacher, nameTeacher;

    public TabsSalaryRatingAdapter(@NonNull FragmentActivity fragmentActivity, String codeTeacher, String nameTeacher) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
        this.codeTeacher = codeTeacher;
        this.nameTeacher = nameTeacher;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new SalaryFragment(codeTeacher,nameTeacher);

            case 1:
                return new RatingTeacherFragment(codeTeacher);
            default:
                Toast.makeText(fragmentActivity, "No Page", Toast.LENGTH_SHORT).show();
        }
        return new SalaryFragment(codeTeacher,nameTeacher);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
