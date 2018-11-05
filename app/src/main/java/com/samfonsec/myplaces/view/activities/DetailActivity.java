package com.samfonsec.myplaces.view.activities;

import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.samfonsec.myplaces.R;
import com.samfonsec.myplaces.databinding.ActDetailBinding;
import com.samfonsec.myplaces.model.LocationDetailEntity;
import com.samfonsec.myplaces.model.ScheduleEntity;
import com.samfonsec.myplaces.model.WeekSchedule;
import com.samfonsec.myplaces.utils.DialogUtils;
import com.samfonsec.myplaces.utils.WeekDays;
import com.samfonsec.myplaces.viewmodel.DetailViewModel;

import java.util.Map;
import java.util.TreeMap;

import static com.samfonsec.myplaces.view.Constants.ARG_ICON_RES_ID;
import static com.samfonsec.myplaces.view.Constants.ARG_LOCATION_ID;
import static com.samfonsec.myplaces.view.Constants.BUNDLE_ARGS;

public class DetailActivity extends AppCompatActivity {
    ActDetailBinding binding;
    Map<Integer, String> weekDaysSchedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.act_detail);
        DetailViewModel viewModel = new DetailViewModel();

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        weekDaysSchedule = new TreeMap<>();

        Bundle bundle = getIntent().getBundleExtra(BUNDLE_ARGS);
        int locationId = bundle.getInt(ARG_LOCATION_ID);
        int appbarImage = bundle.getInt(ARG_ICON_RES_ID);

        binding.appbarImage.setImageResource(appbarImage);
        binding.appbarImage.setColorFilter(Color.WHITE);
        binding.collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        binding.collapsingToolbar.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
        binding.collapsingToolbar.setCollapsedTitleTypeface(ResourcesCompat.getFont(this, R.font.open_sans_light));
        binding.collapsingToolbar.setExpandedTitleTypeface(ResourcesCompat.getFont(this, R.font.open_sans_light));

        viewModel.onDetailResponse().observe(this, this::onDetailResponse);
        viewModel.onError().observe(this, this::onError);

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

    private void onDetailResponse(LocationDetailEntity details) {
        DialogUtils.hideProgressDialog();
        binding.setDetail(details);
        binding.collapsingToolbar.setVisibility(View.VISIBLE);

        binding.reviewLayout.setReview(details.getReview(), true);

        setOperationHours(details.getSchedule());
    }

    private void setOperationHours(WeekSchedule schedule) {
        String operationHours = "";

        setWeekDaysSchedule(0, schedule.getMonday());
        setWeekDaysSchedule(1, schedule.getTuesday());
        setWeekDaysSchedule(2, schedule.getWednesday());
        setWeekDaysSchedule(3, schedule.getThursday());
        setWeekDaysSchedule(4, schedule.getFriday());
        setWeekDaysSchedule(5, schedule.getSaturday());
        setWeekDaysSchedule(6, schedule.getSunday());

        int weekSize = weekDaysSchedule.keySet().size();
        for (Integer day : weekDaysSchedule.keySet()) {
            WeekDays weekDay = WeekDays.getWeekDayByInt(day);
            operationHours = operationHours.concat(weekDay.getDay() + ": ").concat(weekDaysSchedule.get(day));
            weekSize--;
            if (weekSize > 0)
                operationHours = operationHours.concat("\n");
        }

        binding.tvTime.setText(operationHours);

    }

    private void setWeekDaysSchedule(Integer day, ScheduleEntity daySchedule) {

        String dayOperationHours = daySchedule != null ?
                daySchedule.getOpen().concat(" às ").concat(daySchedule.getClose()) :
                "";

        if (!dayOperationHours.isEmpty())
            weekDaysSchedule.put(day, dayOperationHours);

    }


    private void onError(String message) {
        DialogUtils.showErrorDialog(
                this,
                getString(R.string.error_dialog_bt_close),
                false,
                (v, id) -> finish());
    }

}
