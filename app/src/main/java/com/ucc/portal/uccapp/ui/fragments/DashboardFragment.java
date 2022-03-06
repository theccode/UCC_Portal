package com.ucc.portal.uccapp.ui.fragments;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ucc.portal.uccapp.R;
import com.ucc.portal.uccapp.activities.AcademicActivity;
import com.ucc.portal.uccapp.activities.LoginActivity;
import com.ucc.portal.uccapp.activities.ProfileActivity;
import com.ucc.portal.uccapp.cache.SQLiteHandler;
import com.ucc.portal.uccapp.databinding.FragmentDashboardBinding;
import com.ucc.portal.uccapp.helpers.SessionManager;
import com.ucc.portal.uccapp.models.StudentProfile;
import com.ucc.portal.uccapp.models.StudentViewModel;

import java.util.HashMap;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {
    private StudentProfile mStudentProfile;
    private SessionManager mSessionManager;
    private SQLiteHandler mDB;
    private BottomNavigationView mBottomNavigationView;
    public static DashboardFragment newInstance(){
        return new DashboardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSessionManager = new SessionManager(getContext());
        mDB = new SQLiteHandler(getContext());

        if(!mSessionManager.isLoggedIn()){
            logout();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDashboardBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        binding.btnProfile.setOnClickListener(v -> startActivity(new Intent(getActivity(), ProfileActivity.class)));
        binding.btnAcademic.setOnClickListener(v -> startActivity(new Intent(getActivity(), AcademicActivity.class)));
        HashMap<String, String> user = mDB.getUserDetails();
        Log.d("USER", user.toString());
        mStudentProfile = new StudentProfile();
        mStudentProfile.setFirstName(user.get("fname"));
//        mStudentProfile.setMiddleName(user.get("mname"));
//        mStudentProfile.setLastName(user.get("lname"));
//        mStudentProfile.setProgramme(user.get("progid"));
//        mStudentProfile.setMajor(user.get("majorid"));
//        mStudentProfile.setGender(user.get("sex"));
//        mStudentProfile.setDOB(user.get("dob"));
//        mStudentProfile.setLevel(user.get("level"));
//        mStudentProfile.setHall(user.get("hallid"));
//        mStudentProfile.setEmail(user.get("email"));
//        mStudentProfile.setRoomNo(user.get("room_no"));
//        mStudentProfile.setAddress(user.get("non_res_add"));
//        mStudentProfile.setGPSAddress(user.get("gps_address"));
//        mStudentProfile.setCellPhone(user.get("cellphone"));
//        mStudentProfile.setHomePhone(user.get("homephone"));
//        mStudentProfile.setHomeAddress(user.get("homeadd"));
//        mStudentProfile.setPostalAddress(user.get("post_box"));
//        mStudentProfile.setPostalTown(user.get("post_town"));
//        mStudentProfile.setPlaceOfBirth(user.get("pob"));
//        mStudentProfile.setHomeTown(user.get("hometown"));
        binding.setStudentModel(new StudentViewModel());
        binding.getStudentModel().setStudentProfile(mStudentProfile);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.profile:
                    startActivity(new Intent(getActivity(), ProfileActivity.class));
                    break;
                case R.id.exit:
                    logout();
                    break;
            }
            return true;
        });
        return binding.getRoot();
    }

    private void logout() {
        mSessionManager.setLogIn(false);
        mDB.deleteUsers();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}
