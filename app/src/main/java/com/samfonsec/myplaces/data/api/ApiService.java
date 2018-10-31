package com.samfonsec.myplaces.data.api;

import com.samfonsec.myplaces.model.LocationDetailEntity;
import com.samfonsec.myplaces.model.LocationEntity;
import com.samfonsec.myplaces.model.LocationListEntity;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    /**
     * Api url for getting the locations list
     */
    String API_URL_GET_LOCATIONS_LIST = "/locations";

    /**
     * Api url for getting a location details
     */
    String API_URL_GET_LOCATION_DETAIL = "/locations/{id}";


    @GET(API_URL_GET_LOCATIONS_LIST)
    Single<LocationListEntity> getLocations();

    @GET(API_URL_GET_LOCATION_DETAIL)
    Single<LocationDetailEntity> getLocationDetail(@Path("id") Integer id);
}
