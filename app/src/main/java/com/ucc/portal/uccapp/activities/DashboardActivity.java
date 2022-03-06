package com.ucc.portal.uccapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ucc.portal.uccapp.R;
import com.ucc.portal.uccapp.ui.fragments.DashboardFragment;

public class DashboardActivity extends SingleFragmentActivity {
    private static final String EXTRA_NAME = "com.ucc.portal.uccapp.activities";
    public static Intent newIntent(Context packageContext, String firstName){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NAME, firstName);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return new DashboardFragment();
    }

    public static class ProfileActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_profile);
        }
    }
}