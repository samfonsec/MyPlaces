package com.samfonsec.myplaces.view.adapters;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samfonsec.myplaces.R;
import com.samfonsec.myplaces.databinding.AdapterLocationItemBinding;
import com.samfonsec.myplaces.model.ArgsDetail;
import com.samfonsec.myplaces.model.LocationEntity;
import com.samfonsec.myplaces.utils.TypeIcons;

import java.util.List;
import java.util.Random;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ItemHolder> {
    private Context context;
    private List<LocationEntity> locations;
    private int[] colors = {R.color.duckEggBlue, R.color.creme, R.color.lightPink};
    private MutableLiveData<ArgsDetail> onItemClick = new MutableLiveData<>();

    public LocationAdapter(Context context, List<LocationEntity> locations) {
        this.context = context;
        this.locations = locations;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_location_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        LocationEntity location = locations.get(position);
        holder.binding.setLocation(location);
        holder.binding.reviewLayout.setReview(location.getReview(), false);

        int iconResId = TypeIcons.getIconByType(location.getType()).getIconRes();
        holder.binding.ivImage.setImageResource(iconResId);
        int color = ContextCompat.getColor(context, colors[new Random().nextInt(3)]);
        holder.binding.vBackground.setBackgroundColor(color);
        holder.binding.getRoot().setOnClickListener(view ->
                onItemClick.postValue(new ArgsDetail(location.getId(), color, iconResId))
        );
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public MutableLiveData<ArgsDetail> onItemClick() {
        return onItemClick;
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        AdapterLocationItemBinding binding;

        ItemHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }
}
