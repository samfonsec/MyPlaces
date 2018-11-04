package com.samfonsec.myplaces.model;

public class ArgsDetail {
    private int locationId;
    private int icon;

    public ArgsDetail(int locationId, int icon) {
        this.locationId = locationId;
        this.icon = icon;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getIcon() {
        return icon;
    }
}
