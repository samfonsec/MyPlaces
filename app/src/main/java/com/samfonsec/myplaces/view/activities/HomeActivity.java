package com.samfonsec.myplaces.view.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.samfonsec.myplaces.R;
import com.samfonsec.myplaces.databinding.ActHomeBinding;
import com.samfonsec.myplaces.model.ArgsDetail;
import com.samfonsec.myplaces.model.LocationListEntity;
import com.samfonsec.myplaces.utils.DialogUtils;
import com.samfonsec.myplaces.view.adapters.LocationAdapter;
import com.samfonsec.myplaces.viewmodel.HomeViewModel;

import static com.samfonsec.myplaces.view.Constants.ARG_ACTION_BAR_COLOR;
import static com.samfonsec.myplaces.view.Constants.ARG_ICON_RES_ID;
import static com.samfonsec.myplaces.view.Constants.ARG_LOCATION_ID;
import static com.samfonsec.myplaces.view.Constants.BUNDLE_ARGS;


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

    private void onItemClicked(ArgsDetail argsDetail) {
        Bundle args = new Bundle();
        args.putInt(ARG_LOCATION_ID, argsDetail.getLocationId());
        args.putInt(ARG_ACTION_BAR_COLOR, argsDetail.getColor());
        args.putInt(ARG_ICON_RES_ID, argsDetail.getIcon());
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(BUNDLE_ARGS, args);
        startActivity(intent);
    }
}
