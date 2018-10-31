package com.samfonsec.myplaces.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.samfonsec.myplaces.R;
import com.samfonsec.myplaces.model.LocationListEntity;
import com.samfonsec.myplaces.utils.DialogUtils;
import com.samfonsec.myplaces.viewmodel.HomeViewModel;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);

        HomeViewModel viewModel = new HomeViewModel();

        // start to observe
        viewModel.onLocationsResponse().observe(this, this::onDetailResponse);
        viewModel.onError().observe(this, this::onError);

        DialogUtils.showProgressDialog(this);
        viewModel.getLocations();
    }

    private void onDetailResponse(LocationListEntity locationsList) {
        DialogUtils.hideProgressDialog();

    }

    private void onError(String message) {
        DialogUtils.showErrorDialog(this, message);
    }
}
