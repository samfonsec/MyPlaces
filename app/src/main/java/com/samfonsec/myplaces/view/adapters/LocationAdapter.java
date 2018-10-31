package com.samfonsec.myplaces.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samfonsec.myplaces.R;
import com.samfonsec.myplaces.model.LocationEntity;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ItemHolder> {
    private List<LocationEntity> locations;

    public LocationAdapter(List<LocationEntity> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.location_item_adapter, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        LocationEntity location = this.locations.get(position);

    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        View view;

        ItemHolder(View view) {
            super(view);
            this.view = view;
        }
    }
}
