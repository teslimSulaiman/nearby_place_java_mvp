package com.example.owner.nearplace.di.module;

import com.example.owner.nearplace.api.NearbyPlaceApiService;
import com.example.owner.nearplace.di.scope.PerActivity;
import com.example.owner.nearplace.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NearbyPlaceModule {

    @PerActivity
    @Provides
    NearbyPlaceApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(NearbyPlaceApiService.class);
    }

    private MainView mView;

    public NearbyPlaceModule(MainView view) {
        mView = view;
    }




    @PerActivity
    @Provides
    MainView provideView() {
        return mView;
    }
}
