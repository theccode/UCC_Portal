package com.ucc.portal.uccapp.activities;

import com.ucc.portal.uccapp.ui.fragments.OnboardingFragment;

import androidx.fragment.app.Fragment;

public class OnboardingActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new OnboardingFragment();
    }
}