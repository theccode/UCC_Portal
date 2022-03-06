package com.ucc.portal.uccapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.ucc.portal.uccapp.R;
import com.ucc.portal.uccapp.databinding.FragmentAcademicBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AcademicFragment extends Fragment {
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAcademicBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_academic, container, false);
        mToolbar = binding.toolbar;
        ((AppCompatActivity)requireActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTabLayout = binding.tabs;
        mViewPager = binding.viewPager;
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        return binding.getRoot();
    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(requireActivity().getSupportFragmentManager());
        adapter.addFragments(new RegisterFragment(), "Register");
        adapter.addFragments(new TimetableFragment(), "Timetable");
        adapter.addFragments(new ResultsFragment(), "Results");
        viewPager.setAdapter(adapter);
    }
    private static class ViewPagerAdapter extends FragmentPagerAdapter  {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        @Override
        public CharSequence getPageTitle(int position){
            return mFragmentTitleList.get(position);
        }
        public void addFragments(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }
}
