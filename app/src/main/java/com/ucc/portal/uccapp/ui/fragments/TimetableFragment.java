package com.ucc.portal.uccapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucc.portal.uccapp.R;
import com.ucc.portal.uccapp.databinding.FragmentTimetableBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class TimetableFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTimetableBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false);

        return binding.getRoot();
    }
}
