package com.samfonsec.myplaces.utils;

import com.samfonsec.myplaces.R;

public enum TypeIcons {
    BAKERY("Padaria", R.drawable.ic_bakery),
    BARBER("Barbearia", R.drawable.ic_barber),
    BAR("Bares", R.drawable.ic_beer),
    COFFEE("Cafeteria", R.drawable.ic_coffee),
    JUICE("Sucos Naturais", R.drawable.ic_juice),
    SUPERMARKET("Supermercado", R.drawable.ic_market),
    NATURAL("Produtos naturais", R.drawable.ic_natural),
    RESTAURANT("Restaurante", R.drawable.ic_restaurant),
    COWORKING("Coworking", R.drawable.ic_coworking),
    NONE("NONE", R.drawable.ic_pin);

    private String type;
    private int iconRes;

    TypeIcons(String type, int iconRes) {
        this.type = type;
        this.iconRes = iconRes;
    }

    public String getType() {
        return type;
    }

    public int getIconRes() {
        return iconRes;
    }

    public static TypeIcons getIconByType(String type) {
        for (TypeIcons value : TypeIcons.values()) {
            if (value.getType().equalsIgnoreCase(type)) {
                return value;
            }
        }
        return NONE;
    }
}
