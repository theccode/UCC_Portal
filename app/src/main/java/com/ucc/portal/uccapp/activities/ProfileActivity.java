package com.ucc.portal.uccapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ucc.portal.uccapp.ui.fragments.ProfileFragment;

public class ProfileActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new ProfileFragment();
    }
}