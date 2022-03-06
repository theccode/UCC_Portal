package com.ucc.portal.uccapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.ucc.portal.uccapp.R;
import com.ucc.portal.uccapp.cache.SQLiteHandler;
import com.ucc.portal.uccapp.helpers.SessionManager;
import com.ucc.portal.uccapp.ui.fragments.LoginFragment;

public class LoginActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new LoginFragment();
    }
}