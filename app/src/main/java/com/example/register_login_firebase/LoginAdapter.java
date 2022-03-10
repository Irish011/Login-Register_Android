package com.example.register_login_firebase;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Context;

public class LoginAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"Login","Signup"};
    private Context context;
    int Totaltabs;

    public LoginAdapter(@NonNull FragmentActivity fragment) {
        super(fragment);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Login_Tab_Fragment();

            case 1:
                return new SignupTabFragment();
        }
        return new Login_Tab_Fragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
