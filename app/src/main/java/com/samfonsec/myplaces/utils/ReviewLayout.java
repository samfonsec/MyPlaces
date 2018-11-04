package com.samfonsec.myplaces.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.samfonsec.myplaces.R;
import com.samfonsec.myplaces.databinding.LayoutReviewBinding;

import java.util.Arrays;
import java.util.List;


public class ReviewLayout extends ConstraintLayout {
    ObservableBoolean isDetailLayout = new ObservableBoolean(false);
    LayoutReviewBinding binding;

    public ReviewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    private void initLayout() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.layout_review, this, true);

        binding.setDetailLayout(isDetailLayout);
    }

    public void setReview(double review, boolean isDetail) {
        isDetailLayout.set(isDetail);

        double rating = Math.round(review);
        List<ImageView> starsList = Arrays.asList(binding.ivStar1, binding.ivStar2,
                binding.ivStar3, binding.ivStar4, binding.ivStar5);

        for (int i = 0; i < starsList.size(); i++) {
            if (rating > i)
                starsList.get(i).setImageResource(isDetail ? R.drawable.ic_star_on : R.drawable.ic_star_on_small);
            else
                starsList.get(i).setImageResource(isDetail ? R.drawable.ic_star_off : R.drawable.ic_star_off_small);
        }

        binding.tvRating.setText(String.valueOf(review));
    }


    @BindingAdapter("android:layout_marginStart")
    public static void setMarginStart(View view, float marginStart) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(Math.round(marginStart), layoutParams.topMargin,
                layoutParams.rightMargin, layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }
}
