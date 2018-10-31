package com.samfonsec.myplaces.model;

import com.google.gson.annotations.SerializedName;

public class LocationEntity {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("review")
    private Double review;

    @SerializedName("type")
    private String type;

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
}
