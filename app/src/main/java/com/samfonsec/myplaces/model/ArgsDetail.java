package com.samfonsec.myplaces.model;

public class ArgsDetail {
    private int locationId;
    private int color;
    private int icon;

    public ArgsDetail(int locationId, int color, int icon) {
        this.locationId = locationId;
        this.color = color;
        this.icon = icon;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getColor() {
        return color;
    }

    public int getIcon() {
        return icon;
    }
}
