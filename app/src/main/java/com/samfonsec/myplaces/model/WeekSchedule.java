package com.samfonsec.myplaces.model;

import com.google.gson.annotations.SerializedName;

public class WeekSchedule {
    @SerializedName("monday")
    private ScheduleEntity monday;

    @SerializedName("tuesday")
    private ScheduleEntity tuesday;

    @SerializedName("wednesday")
    private ScheduleEntity wednesday;

    @SerializedName("thursday")
    private ScheduleEntity thursday;

    @SerializedName("friday")
    private ScheduleEntity friday;

    @SerializedName("saturday")
    private ScheduleEntity saturday;

    @SerializedName("sunday")
    private ScheduleEntity sunday;

    /* getter*/
    public ScheduleEntity getMonday() {
        return monday;
    }

    public ScheduleEntity getTuesday() {
        return tuesday;
    }

    public ScheduleEntity getWednesday() {
        return wednesday;
    }

    public ScheduleEntity getThursday() {
        return thursday;
    }

    public ScheduleEntity getFriday() {
        return friday;
    }

    public ScheduleEntity getSaturday() {
        return saturday;
    }

    public ScheduleEntity getSunday() {
        return sunday;
    }
}
