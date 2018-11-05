package com.samfonsec.myplaces.utils;

import com.samfonsec.myplaces.R;

public enum WeekDays {
    MONDAY(0, R.string.monday),
    TUESDAY(1, R.string.tuesday),
    WEDNESDAY(2, R.string.wednesday),
    THURSDAY(3, R.string.thursday),
    FRIDAY(4, R.string.friday),
    SATURDAY(5, R.string.saturday),
    SUNDAY(6, R.string.sunday),
    NONE(-1, 0);


    private Integer dayId;
    private Integer dayResId;

    WeekDays(Integer dayId, Integer dayResId) {
        this.dayId = dayId;
        this.dayResId = dayResId;
    }

    public Integer getDayId() {
        return dayId;
    }

    public Integer getDayResId() {
        return dayResId;
    }

    public static WeekDays getWeekDay(Integer dayInt) {
        for (WeekDays resId : WeekDays.values()) {
            if (resId.getDayId().equals(dayInt)) {
                return resId;
            }
        }
        return NONE;
    }
}
