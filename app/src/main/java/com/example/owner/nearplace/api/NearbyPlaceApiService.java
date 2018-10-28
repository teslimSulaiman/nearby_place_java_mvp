package com.example.owner.nearplace.api;

import com.example.owner.nearplace.mvp.model.NearbyPlace;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NearbyPlaceApiService {

    @GET("/maps/api/place/nearbysearch/json")
    Observable<NearbyPlace> getNearbyPlaces(
            @Query("location") String location,
            @Query("radius") String radius,
            @Query("key") String key);
}
