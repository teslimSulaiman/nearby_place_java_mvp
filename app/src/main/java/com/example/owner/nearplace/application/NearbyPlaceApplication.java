package com.example.owner.nearplace.application;

import android.app.Application;

import com.example.owner.nearplace.Utility;
import com.example.owner.nearplace.di.components.ApplicationComponent;
import com.example.owner.nearplace.di.components.DaggerApplicationComponent;
import com.example.owner.nearplace.di.module.ApplicationModule;

public class NearbyPlaceApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, Utility.BASE_URL))

                .build();
    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
