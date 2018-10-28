package com.example.owner.nearplace.mvp.view;

import com.example.owner.nearplace.mvp.model.NearbyPlace;
import com.example.owner.nearplace.mvp.model.NearbyPlaceInfo;

import java.util.List;

public interface MainView extends BaseView {
    void onNearbyPlacesLoaded(List<NearbyPlaceInfo> nearbyPlaceInfoList);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();

    void onLoadMore(String message);
}
