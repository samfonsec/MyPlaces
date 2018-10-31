package com.samfonsec.myplaces.data.repository;

import com.samfonsec.myplaces.data.api.ApiClient;
import com.samfonsec.myplaces.data.api.ApiService;
import com.samfonsec.myplaces.model.LocationDetailEntity;
import com.samfonsec.myplaces.model.LocationEntity;
import com.samfonsec.myplaces.model.LocationListEntity;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LocationDataRepository {
    private ApiService apiInterface;

    public LocationDataRepository() {
        apiInterface = ApiClient.getClient().create(ApiService.class);
    }

    /**
     * Call API and return a list of locations
     *
     * @return discover response object
     */
    public Single<LocationListEntity> getLocations() {
        return Single.create((SingleEmitter<LocationListEntity> emitter) ->
                apiInterface.getLocations()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<LocationListEntity>() {
                            @Override
                            public void onSuccess(LocationListEntity discoverResponse) {
                                emitter.onSuccess(discoverResponse);
                            }

                            @Override
                            public void onError(Throwable e) {
                                emitter.onError(e);
                            }
                        })
        );
    }

    /**
     * Call API and return a location detail
     *
     * @param id : location id
     * @return location detail object
     */
    public Single<LocationDetailEntity> getLocationDetail(Integer id) {
        return Single.create((SingleEmitter<LocationDetailEntity> emitter) ->
                apiInterface.getLocationDetail(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<LocationDetailEntity>() {
                            @Override
                            public void onSuccess(LocationDetailEntity discoverResponse) {
                                emitter.onSuccess(discoverResponse);
                            }

                            @Override
                            public void onError(Throwable e) {
                                emitter.onError(e);
                            }
                        })
        );
    }
}
