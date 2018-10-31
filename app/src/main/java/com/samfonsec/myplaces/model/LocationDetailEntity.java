package com.samfonsec.myplaces.model;

import com.google.gson.annotations.SerializedName;

public class LocationDetailEntity {
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

    @SerializedName("address")
    private String address;

    @SerializedName("schedule")
    private WeekSchedule schedule;

    /* getters */

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getReview() {
        return review;
    }

    public String getType() {
        return type;
    }

    public String getAbout() {
        return about;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public WeekSchedule getSchedule() {
        return schedule;
    }
}
