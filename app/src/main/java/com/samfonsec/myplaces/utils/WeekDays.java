package com.samfonsec.myplaces.utils;

public enum WeekDays {
    MONDAY(0, "seg"),
    TUESDAY(1, "ter"),
    WEDNESDAY(2, "qua"),
    THURSDAY(3, "qui"),
    FRIDAY(4, "sex"),
    SATURDAY(5, "sab"),
    SUNDAY(6, "dom"),
    NONE(-1, "none");


    private Integer dayInt;
    private String day;

    WeekDays(Integer dayInt, String day) {
        this.dayInt = dayInt;
        this.day = day;
    }

    public Integer getDayInt() {
        return dayInt;
    }

    public String getDay() {
        return day;
    }

    public static WeekDays getWeekDayByInt(Integer dayInt) {
        for (WeekDays value : WeekDays.values()) {
            if (value.getDayInt().equals(dayInt)) {
                return value;
            }
        }
        return NONE;
    }
}
