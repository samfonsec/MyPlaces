package com.samfonsec.myplaces.model;

import com.google.gson.annotations.SerializedName;

public class ScheduleEntity {
    @SerializedName("open")
    private String open;

    @SerializedName("close")
    private String close;

    /* getters */

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }
}
