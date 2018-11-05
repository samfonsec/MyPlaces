package com.samfonsec.myplaces.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.samfonsec.myplaces.data.repository.LocationDataRepository;
import com.samfonsec.myplaces.model.LocationListEntity;

import io.reactivex.observers.DisposableSingleObserver;

public class HomeViewModel extends ViewModel {
    private LocationDataRepository locationDataRepository;
    private MutableLiveData<LocationListEntity> onLocationsResponse;
    private MutableLiveData<String> onError;

    public HomeViewModel() {
        locationDataRepository = new LocationDataRepository();
        onLocationsResponse = new MutableLiveData<>();
        onError = new MutableLiveData<>();
    }

    public MutableLiveData<LocationListEntity> onLocationsResponse() {
        return onLocationsResponse;
    }

    public MutableLiveData<String> onError() {
        return onError;
    }

    public void getLocations() {
        locationDataRepository.getLocations()
                .subscribe(new DisposableSingleObserver<LocationListEntity>() {
                    @Override
                    public void onSuccess(LocationListEntity locationResponse) {
                        onLocationsResponse.postValue(locationResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onError.postValue(e.getMessage());
                    }
                });
    }

}
