package com.ucc.portal.uccapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ucc.portal.uccapp.R;
import com.ucc.portal.uccapp.ui.fragments.AcademicFragment;

public class AcademicActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new AcademicFragment();
    }
}