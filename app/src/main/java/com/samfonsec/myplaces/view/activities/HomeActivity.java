package com.samfonsec.myplaces.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.samfonsec.myplaces.R;
import com.samfonsec.myplaces.databinding.ActHomeBinding;
import com.samfonsec.myplaces.model.LocationListEntity;
import com.samfonsec.myplaces.utils.DialogUtils;
import com.samfonsec.myplaces.view.adapters.LocationAdapter;
import com.samfonsec.myplaces.viewmodel.HomeViewModel;


public class HomeActivity extends AppCompatActivity {
    ActHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.act_home);

        HomeViewModel viewModel = new HomeViewModel();

        viewModel.onLocationsResponse().observe(this, this::onLocationsResponse);
        viewModel.onError().observe(this, this::onError);

        DialogUtils.showProgressDialog(this);
        viewModel.getLocations();
    }

    private void onLocationsResponse(LocationListEntity locationsList) {
        DialogUtils.hideProgressDialog();

        LocationAdapter locationAdapter = new LocationAdapter(this, locationsList.getLocations());
        locationAdapter.onItemClick().observe(this, this::onItemClicked);
        binding.rvLocations.setAdapter(locationAdapter);
        binding.rvLocations.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.rvLocations.setItemAnimator(null);

    }

    private void onError(String message) {
        DialogUtils.showErrorDialog(this, message);
    }

    private void onItemClicked(int id) {

    }
}
