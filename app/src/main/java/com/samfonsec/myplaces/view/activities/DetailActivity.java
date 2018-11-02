package com.samfonsec.myplaces.view.activities;

import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.samfonsec.myplaces.R;
import com.samfonsec.myplaces.databinding.ActDetailBinding;
import com.samfonsec.myplaces.model.LocationDetailEntity;
import com.samfonsec.myplaces.utils.DialogUtils;
import com.samfonsec.myplaces.viewmodel.DetailViewModel;

import static com.samfonsec.myplaces.view.Constants.ARG_ACTION_BAR_COLOR;
import static com.samfonsec.myplaces.view.Constants.ARG_ICON_RES_ID;
import static com.samfonsec.myplaces.view.Constants.ARG_LOCATION_ID;
import static com.samfonsec.myplaces.view.Constants.BUNDLE_ARGS;

public class DetailActivity extends AppCompatActivity {
    ActDetailBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.act_detail);
        DetailViewModel viewModel = new DetailViewModel();

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getBundleExtra(BUNDLE_ARGS);
        int locationId = bundle.getInt(ARG_LOCATION_ID);
        int appbarColor = bundle.getInt(ARG_ACTION_BAR_COLOR);
        int appbarImage = bundle.getInt(ARG_ICON_RES_ID);

        binding.appbar.setBackgroundColor(appbarColor);
        binding.appbarImage.setImageResource(appbarImage);

        viewModel.onDetailResponse().observe(this, this::onDetailResponse);
        viewModel.onError().observe(this, this::onError);

        binding.collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        binding.collapsingToolbar.setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT));

        DialogUtils.showProgressDialog(this);
        viewModel.getLocationDetail(locationId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onDetailResponse(LocationDetailEntity entity) {
        DialogUtils.hideProgressDialog();

    }

    private void onError(String message) {
        DialogUtils.showErrorDialog(this, message);
    }

}
