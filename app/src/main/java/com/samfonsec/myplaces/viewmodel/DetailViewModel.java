package com.samfonsec.myplaces.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.samfonsec.myplaces.data.repository.LocationDataRepository;
import com.samfonsec.myplaces.model.LocationDetailEntity;

import io.reactivex.observers.DisposableSingleObserver;

public class DetailViewModel {
    private LocationDataRepository locationDataRepository;
    private MutableLiveData<LocationDetailEntity> onDetailResponse;
    private MutableLiveData<String> onError;

    public DetailViewModel() {
        locationDataRepository = new LocationDataRepository();
        onDetailResponse = new MutableLiveData<>();
        onError = new MutableLiveData<>();
    }

    public MutableLiveData<LocationDetailEntity> onDetailResponse() {
        return onDetailResponse;
    }

    public MutableLiveData<String> onError() {
        return onError;
    }

    public void getLocationDetail(Integer id) {
        locationDataRepository.getLocationDetail(id)
                .subscribe(new DisposableSingleObserver<LocationDetailEntity>() {
                    @Override
                    public void onSuccess(LocationDetailEntity locationResponse) {
                        onDetailResponse.postValue(locationResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onError.postValue(e.getMessage());
                    }
                });
    }

}
