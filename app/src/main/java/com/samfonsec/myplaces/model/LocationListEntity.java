package com.samfonsec.myplaces.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationListEntity {
    @SerializedName("listLocations")
    private List<LocationEntity> locations;

    public List<LocationEntity> getLocations() {
        return locations;
    }
}
