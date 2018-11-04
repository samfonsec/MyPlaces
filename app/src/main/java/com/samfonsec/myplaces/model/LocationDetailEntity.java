package com.samfonsec.myplaces.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.samfonsec.myplaces.BR;

public class LocationDetailEntity extends BaseObservable {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("review")
    private Double review;

    @SerializedName("type")
    private String type;

    @SerializedName("about")
    private String about;

    @SerializedName("phone")
    private String phone;

    @SerializedName("adress")
    private String adress;

    @SerializedName("schedule")
    private WeekSchedule schedule;

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

    @Bindable
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
        notifyPropertyChanged(BR.about);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
        notifyPropertyChanged(BR.adress);
    }

    public WeekSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(WeekSchedule schedule) {
        this.schedule = schedule;
    }
}
