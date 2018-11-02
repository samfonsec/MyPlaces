package com.samfonsec.myplaces.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.samfonsec.myplaces.BR;

public class LocationEntity extends BaseObservable {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("review")
    private Double review;

    @SerializedName("type")
    private String type;

    /* getters and setters */

    @Bindable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public Double getReview() {
        return review;
    }

    public void setReview(Double review) {
        this.review = review;
        notifyPropertyChanged(BR.review);
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }
}
