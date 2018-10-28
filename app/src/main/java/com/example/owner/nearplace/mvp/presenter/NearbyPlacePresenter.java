package com.example.owner.nearplace.mvp.presenter;

import com.example.owner.nearplace.api.NearbyPlaceApiService;
import com.example.owner.nearplace.base.BasePresenter;
import com.example.owner.nearplace.mapper.NearbyPlaceInfoMapper;
import com.example.owner.nearplace.mvp.model.NearbyPlace;
import com.example.owner.nearplace.mvp.model.NearbyPlaceInfo;
import com.example.owner.nearplace.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

public class NearbyPlacePresenter extends BasePresenter<MainView> implements Observer<NearbyPlace> {

    @Inject
    protected NearbyPlaceApiService apiService;

    @Inject
    protected NearbyPlaceInfoMapper nearbyPlaceInfoMapper;

    @Inject
    public NearbyPlacePresenter() {

    }

    public void getNearbyPlaces(String location, String radius, String key) {
        getView().onShowDialog("loading...");
        Observable<NearbyPlace> nearbyPlaceResponseObservable = apiService.getNearbyPlaces(location, radius, key);
        subscribe(nearbyPlaceResponseObservable, this);

    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Places loading complete!");


    }


    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error loading places" + e.getMessage());
    }

    @Override
    public void onNext(NearbyPlace response) {

        List<NearbyPlaceInfo> nearbyPlaceInfoList = nearbyPlaceInfoMapper.mapNearbyPlaceInfo(response);
        getView().onShowToast("STATUS: " + response.getStatus());
        getView().onNearbyPlacesLoaded(nearbyPlaceInfoList);

    }

}
