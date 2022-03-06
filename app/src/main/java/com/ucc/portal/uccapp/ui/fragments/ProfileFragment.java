package com.ucc.portal.uccapp.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.ucc.portal.uccapp.R;
import com.ucc.portal.uccapp.cache.SQLiteHandler;
import com.ucc.portal.uccapp.databinding.FragmentProfileBinding;
import com.ucc.portal.uccapp.databinding.FragmentProfileBindingImpl;
import com.ucc.portal.uccapp.helpers.SessionManager;
import com.ucc.portal.uccapp.models.StudentProfile;
import com.ucc.portal.uccapp.models.StudentViewModel;

import java.util.HashMap;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private StudentProfile mStudentProfile;
    private SessionManager mSessionManager;
    private SQLiteHandler mDB;
    Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSessionManager = new SessionManager(getContext());
        mDB = new SQLiteHandler(getContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentProfileBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        mToolbar = binding.toolbar;
        mToolbar.setTitle(" ");
        ((AppCompatActivity) requireActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        HashMap<String, String> user = mDB.getUserDetails();
        Log.d("PROF", user.toString());
        mStudentProfile = new StudentProfile();
        mStudentProfile.setRegistrationNumber(user.get("regno"));
        mStudentProfile.setFirstName(user.get("fname"));
        mStudentProfile.setMiddleName(user.get("mname"));
        mStudentProfile.setLastName(user.get("lname"));
        mStudentProfile.setFullName(user.get("fname") + " " + user.get("mname") + " " + user.get("lname"));
        mStudentProfile.setProgramme(user.get("programme"));
        mStudentProfile.setMajor(user.get("major"));
        mStudentProfile.setGender(user.get("gender"));
        mStudentProfile.setDOB(user.get("dob"));
        mStudentProfile.setLevel(user.get("level"));
        mStudentProfile.setHall(user.get("hallId"));
        mStudentProfile.setEmail(user.get("email"));
        mStudentProfile.setRoomNo(user.get("roomNo"));
        mStudentProfile.setAddress(user.get("address"));
        mStudentProfile.setGPSAddress(user.get("gpsAddress"));
        mStudentProfile.setCellPhone(user.get("cellPhone"));
        mStudentProfile.setHomePhone(user.get("homePhone"));
        mStudentProfile.setHomeAddress(user.get("homeAddress"));
        mStudentProfile.setPostalAddress(user.get("postBox"));
        mStudentProfile.setPostalTown(user.get("postTown"));
        mStudentProfile.setPlaceOfBirth(user.get("pobTown"));
        mStudentProfile.setHomeTown(user.get("homeTown"));
        binding.setStudentModel(new StudentViewModel());
        binding.getStudentModel().setStudentProfile(mStudentProfile);
        binding.btnUpdate.setOnClickListener(v -> {
            Toast.makeText(getContext(), "#TODO", Toast.LENGTH_LONG).show();
        });
        return binding.getRoot();
    }
}
