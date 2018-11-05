package com.samfonsec.myplaces.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.samfonsec.myplaces.R;
import com.samfonsec.myplaces.model.LocationListEntity;
import com.samfonsec.myplaces.utils.DialogUtils;
import com.samfonsec.myplaces.view.fragments.HomeFragment;
import com.samfonsec.myplaces.view.fragments.ProfileFragment;
import com.samfonsec.myplaces.viewmodel.HomeViewModel;

public class MainActivity extends AppCompatActivity {
    HomeViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        viewModel = new HomeViewModel();

        viewModel.onLocationsResponse().observe(this, this::onLocationsResponse);
        viewModel.onError().observe(this, this::onError);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContainer, HomeFragment.newInstance(), null)
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bnNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            clearBackStack();
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContainer, HomeFragment.newInstance(), null)
                            .commit();
                    break;
                case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContainer, ProfileFragment.newInstance(), null)
                            .commit();

            }
            return true;
        });

        DialogUtils.showProgressDialog(this);
        viewModel.getLocations();
    }

    private void onLocationsResponse(LocationListEntity locationsList) {
        DialogUtils.hideProgressDialog();
    }

    public HomeViewModel getViewModel() {
        return viewModel;
    }

    private void onError(String message) {
        DialogUtils.showErrorDialog(
                this,
                getString(R.string.error_dialog_bt_try_again),
                true,
                (dialog, id) -> {
                    DialogUtils.showProgressDialog(MainActivity.this);
                    viewModel.getLocations();
                    dialog.dismiss();
                });
    }

    private void clearBackStack() {
        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
            getSupportFragmentManager().popBackStack();
        }
    }

}
