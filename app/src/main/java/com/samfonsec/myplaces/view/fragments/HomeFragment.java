package com.samfonsec.myplaces.view.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.samfonsec.myplaces.R;
import com.samfonsec.myplaces.databinding.FragHomeBinding;
import com.samfonsec.myplaces.model.ArgsDetail;
import com.samfonsec.myplaces.model.LocationListEntity;
import com.samfonsec.myplaces.view.activities.DetailActivity;
import com.samfonsec.myplaces.view.activities.MainActivity;
import com.samfonsec.myplaces.view.adapters.LocationAdapter;
import com.samfonsec.myplaces.viewmodel.HomeViewModel;

import static com.samfonsec.myplaces.view.Constants.ARG_ICON_RES_ID;
import static com.samfonsec.myplaces.view.Constants.ARG_LOCATION_ID;
import static com.samfonsec.myplaces.view.Constants.BUNDLE_ARGS;

public class HomeFragment extends android.support.v4.app.Fragment {
    FragHomeBinding binding;
    HomeViewModel actViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_home, container, false);

        MainActivity mainActivity = ((MainActivity) getActivity());
        if (mainActivity != null)
            actViewModel = mainActivity.getViewModel();

        actViewModel.onLocationsResponse().observe(this, this::onLocationsResponse);

        return binding.getRoot();
    }

    private void onLocationsResponse(LocationListEntity locationsList) {
        LocationAdapter locationAdapter = new LocationAdapter(getActivity(), locationsList.getLocations());

        locationAdapter.onItemClick().observe(this, this::onItemClicked);
        binding.rvLocations.setAdapter(locationAdapter);
        binding.rvLocations.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.rvLocations.setItemAnimator(null);

    }

    private void onItemClicked(ArgsDetail argsDetail) {
        Bundle args = new Bundle();
        args.putInt(ARG_LOCATION_ID, argsDetail.getLocationId());
        args.putInt(ARG_ICON_RES_ID, argsDetail.getIcon());
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(BUNDLE_ARGS, args);
        startActivity(intent);
    }
}
